Command to run specific testing.xml file located at project root directory using maven command:
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
================================================== LiveMint ==========================================================
Command to run specific testing.xml file located at custom directory using maven command:
mvn clean test -Dsurefire.suiteXmlFiles=./src/main/resources/TestngXML/testng_LiveMint.xml


Command to run specific testng.xml file located at custom directory with runtime parameters using maven:
mvn clean test -Dsurefire.suiteXmlFiles=./src/main/resources/TestngXML/testng_LiveMint.xml -Denvironment=prod -DprojectName=LiveMint -DexecutionType=sanity -DplatformName=web_desktop -Dbrowser=chrome

where environment, projectName, executionType, platformName and browser are the configuration parameters to be passed at runtime with the command 
as mentioned above.

================================================== SmartCast ==========================================================
Command to run specific testing.xml file located at custom directory using maven command: mvn clean test -Dsurefire.suiteXmlFiles=./src/main/resources/TestngXML/testng_SmartCast.xml

Command to run specific testng.xml file located at custom directory with runtime parameters using maven: mvn clean test -Dsurefire.suiteXmlFiles=./src/main/resources/TestngXML/testng_SmartCast.xml -Denvironment=prod -DprojectName=SmartCast -DexecutionType=sanity -DplatformName=web_desktop -Dbrowser=chrome

where environment, projectName, executionType, platformName and browser are the configuration parameters to be passed at runtime with the command as mentioned above.