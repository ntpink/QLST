@REM Maven Wrapper launcher for Windows
@SETLOCAL
@SET BASE_DIR=%~dp0
@SET MAVEN_PROJECTBASEDIR=%BASE_DIR:~0,-1%
@SET WRAPPER_JAR=%BASE_DIR%.mvn\wrapper\maven-wrapper.jar
@IF EXIST "%WRAPPER_JAR%" GOTO runMaven
@SET WRAPPER_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar
@powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference='SilentlyContinue'; Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%'"
@IF NOT EXIST "%WRAPPER_JAR%" (
  @ECHO Could not download Maven Wrapper. Check network access.
  @EXIT /B 1
)
:runMaven
@SET MAVEN_OPTS=%MAVEN_OPTS%
@SET JAVA_HOME=%JAVA_HOME%
@"%JAVA_HOME%\bin\java.exe" %MAVEN_OPTS% -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" -classpath "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
@ENDLOCAL
