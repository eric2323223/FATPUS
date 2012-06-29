cd /d %~dp0
call setenv

:parse_parameter
echo parse_parameter

if "%1"=="" (goto end)
set BSR_CURRENT_FEATURE=%1
cd "%cd%\..\..\..\..\..\.."
set BSR_PROJECT_HOME=%cd%

cd "%BSR_PROJECT_HOME%\testscript\%1"

if not %ERRORLEVEL%==0 (goto end)
set BSR_CURRENT_FEATURE=%1


for /f %%a in ('dir /AD /b /d') do (
	for %%b in (script Script scripts Scripts) do (
		if %%a==%%b (
			set BSR_SCRIPT_FOLDER=%%b
			goto generate_filelist
		)
	)
	
)

:generate_filelist
echo generate_filelist

cd "%BSR_PROJECT_HOME%\testscript\%BSR_CURRENT_FEATURE%"
if not %ERRORLEVEL%==0 goto end
set BSR_FILELIST=%BSR_HOME%\%BSR_CURRENT_FEATURE%_list
del "%BSR_FILELIST%"

setlocal enableextensions enabledelayedexpansion

for /R "%BSR_PROJECT_HOME%\testscript\%BSR_CURRENT_FEATURE%\%BSR_SCRIPT_FOLDER%" %%f in (*.testsuite) do (
	set BSR_CURRENT_SCRIPT=%%~nf
	setlocal EnableDelayedExpansion
	if /I not !BSR_CURRENT_SCRIPT:~-3!==all  (
		echo testscript.%BSR_CURRENT_FEATURE%.%BSR_SCRIPT_FOLDER%.%%~nf >> "%BSR_FILELIST%"
	)
	endlocal

)
 
cd %BSR_HOME%

:run_scripts
echo run scripts...
call runscripts.bat %BSR_CURRENT_FEATURE%_list test > runscripts.log


:end
echo Usage: autotest.bat {feature_name}
echo "%BSR_HOME%"
cd "%BSR_HOME%"




