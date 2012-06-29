@echo off
cd /d %~dp0
call setenv


:START
echo BSR_TEST_MODE=%BSR_TEST_MODE%
if NOT "%2"=="" (set BSR_TEST_MODE=true)

:GENERATE_START_TIME
call get_time
set BSR_START_DATE=%BSR_CURRENT_DATE%
set BSR_START_TIME=%BSR_CURRENT_TIME%

:GENERATE_RESULT_LOG_NAME
set BSR_RESULT_LOG=log_%BSR_START_DATE%_%BSR_START_TIME%.txt

:RUN_SCRIPTS
FOR /F "tokens=1" %%G IN (%1) DO call runscript %%G

:GENERATE_END_TIME
call get_time
set BSR_END_DATE=%BSR_CURRENT_DATE%
set BSR_END_TIME=%BSR_CURRENT_TIME%

:SECRECT
if %BSR_TEST_MODE%==true (set BSR_TO=pyin@sybase.com)

:SEND_MAIL
echo %BSR_MAIL_SERVER%
echo %BSR_SMTP_PORT%
echo %BSR_FROM%
echo %BSR_TO%
echo %BSR_SUBJECT%
echo %BSR_RESULT_LOG%
echo %BSR_START_TIME%
echo %BSR_END_TIME%

set BSR_SUP_PRODUCT=SUPXXX
set BSR_SUP_VERSION=XXXX

:Generate_result
echo Generate_result
echo "%BSR_RFT_JAVA_HOME%\java" -classpath "%BSR_RFT_PROJECT_HOME%";"%BSR_RFT_PROJECT_LIB_PATH%" com.sybase.automation.framework.core.helper.ResultLogParser %BSR_RESULT_LOG% %BSR_START_DATE% %BSR_START_TIME% %BSR_END_DATE% %BSR_END_TIME% %BSR_SUP_PRODUCT% %BSR_SUP_VERSION% > "%BSR_RESULT_DATA_FILE%"
"%BSR_RFT_JAVA_HOME%\java" -classpath "%BSR_RFT_PROJECT_HOME%";"%BSR_RFT_PROJECT_LIB_PATH%" com.sybase.automation.framework.core.helper.ResultLogParser %BSR_RESULT_LOG% %BSR_START_DATE% %BSR_START_TIME% %BSR_END_DATE% %BSR_END_TIME% %BSR_SUP_PRODUCT% %BSR_SUP_VERSION% > "%BSR_RESULT_DATA_FILE%"

:Insert_data
echo Insert_data
echo %BSR_TEST_MODE%
if %BSR_TEST_MODE%==true (
	echo keep it secrect...
	goto :Send_mail
)
"%BSR_RFT_JAVA_HOME%\java" -classpath "%BSR_RFT_PROJECT_HOME%";"%BSR_RFT_PROJECT_LIB_PATH%" com.sybase.automation.framework.common.DBUtil "%BSR_RESULT_DATA_FILE%"

:Send_mail
echo Send_mail
"%BSR_RFT_JAVA_HOME%\java" -classpath "%BSR_RFT_PROJECT_HOME%";"%BSR_RFT_PROJECT_LIB_PATH%" com.sybase.automation.framework.common.MailUtil %BSR_MAIL_SERVER% %BSR_SMTP_PORT% %BSR_FROM% %BSR_TO% %BSR_SUBJECT% %BSR_RESULT_LOG% %BSR_START_DATE% %BSR_START_TIME% %BSR_END_DATE% %BSR_END_TIME% %BSR_SUP_PRODUCT% %BSR_SUP_VERSION%

:END
set BSR_TEST_MODE=false