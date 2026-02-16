@echo off

echo =========================================
echo Starting Absoloop Retail QE Engine Build
echo =========================================

echo Cleaning and executing TestNG suite...

mvn clean test -DsuiteXmlFile=master.xml

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo ******** BUILD FAILED ********
    exit /b %ERRORLEVEL%
)

echo.
echo ******** BUILD SUCCESSFUL ********
exit /b 0
