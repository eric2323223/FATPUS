@echo off
cd /d %~dp0

set BSR_HOME=%cd%
set BSR_RFT_JAVA_HOME=C:\Program Files\IBM\SDP\jdk\bin
set BSR_RFT_PROJECT_HOME=C:\Documents and Settings\eric\IBM\rationalsdp\workspace\UEP_ET
set BSR_RFT_PROJECT_LIB_PATH=%BSR_RFT_PROJECT_HOME%\lib\*
set BSR_RFT_JRE_LIB_PATH=C:\Program Files\IBM\SDP\jdk\jre\lib\*
set BSR_RFT_PLUGIN_PATH=C:/Program Files/IBM/IBMIMShared/plugins/*
set BSR_RFT_RATIONAL_FT_JAR=C:\Program Files\IBM\SDP\FunctionalTester\bin\rational_ft.jar
set BSR_RFT_JAVA=%BSR_RFT_JAVA_HOME%\java
set BSR_RESULT_DATA_FILE=%BSR_HOME%\result_data

For /f "tokens=1-2 delims==" %%a in (mail.properties) do (IF "%%a"=="mail_server" set BSR_MAIL_SERVER=%%b)
For /f "tokens=1-2 delims==" %%a in (mail.properties) do (IF "%%a"=="smtp_port" set BSR_SMTP_PORT=%%b)
For /f "tokens=1-2 delims==" %%a in (mail.properties) do (IF "%%a"=="from" set BSR_FROM=%%b)
For /f "tokens=1-2 delims==" %%a in (mail.properties) do (IF "%%a"=="to" set BSR_TO=%%b)
For /f "tokens=1-2 delims==" %%a in (mail.properties) do (IF "%%a"=="subject" set BSR_SUBJECT=%%b)