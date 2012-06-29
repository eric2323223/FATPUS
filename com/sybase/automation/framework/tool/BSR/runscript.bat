@echo off
cd /d %~dp0
rem set CURRENT_DIR=%~dp0

call setenv
:RUN_SCRIPT
"%BSR_RFT_JAVA_HOME%\java" -classpath "%BSR_RFT_RATIONAL_FT_JAR%";"%BSR_RFT_PROJECT_LIB_PATH%";"%BSR_RFT_PROJECT_HOME%" com.rational.test.ft.rational_ft -datastore "%BSR_RFT_PROJECT_HOME%" -playback %1

:PARSE_HTML_LOG
set BSR_LOG_FILE=%BSR_RFT_PROJECT_HOME%_logs\%1\rational_ft_logframe.html
"%BSR_RFT_JAVA_HOME%\java" -classpath "%BSR_RFT_PROJECT_LIB_PATH%";"%BSR_RFT_PROJECT_HOME%" com.sybase.automation.framework.core.helper.HTMLScriptResultParser "%BSR_LOG_FILE%" >> %BSR_RESULT_LOG%

:CLEAN_UP
"%BSR_RFT_JAVA_HOME%\java" -classpath "%BSR_RFT_RATIONAL_FT_JAR%";"%BSR_RFT_PROJECT_LIB_PATH%";"%BSR_RFT_PROJECT_HOME%" com.rational.test.ft.rational_ft -datastore "%BSR_RFT_PROJECT_HOME%" -playback testscript.Auxiliary.CleanUp

:CLOSE_IE
taskkill /f /im iexplore.exe

:EOF