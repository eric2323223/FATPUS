@echo off

For /f "tokens=1-3 delims=/ " %%a in ('date /t') do (set BSR_CURRENT_DATE=%%c-%%a-%%b)
For /f "tokens=1-3 delims=/:" %%a in ("%TIME%") do (set BSR_CURRENT_DOT_TIME=%%a-%%b-%%c)
For /f "tokens=1 delims=." %%a in ("%BSR_CURRENT_DOT_TIME%") do (set BSR_CURRENT_TIME=%%a)
set BSR_CURRENT_TIME=%BSR_CURRENT_TIME: =%
rem set BSR_RESULT_LOG=log_%BSR_CURRENT_DATE%_%BSR_CURRENT_TIME%.txt