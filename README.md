I. User need to update below parameters in respective testng xml file on project level as per requirement. Below are the key value pairs which can be used in order to run test scripts:

    * environment:      prod, qa, staging 
    * projectName:      LiveHindustan, HindustanTimes, HT_Epaper, LH_paper, LiveMint, LM_Epaper, BlankPaper
    * executionType:    sanity, regression, prod
    * platformName:     web_desktop, android_native, android_web, ios_native, ios_web
    * browser:          chrome,safari, firefox
    * browserMode:      private, normal
    * isBrowserStack:   true[if want to run on BrowserStack], false[if want to run on LocalSystem]
    * capabilitiesJson: iphone11_safari,

II. In case of executing via maven command as CLI, please refer example command below:
    
    mvn clean test -Dsurefire.suiteXmlFiles=./src/main/resources/TestngXML/testng_SSO_Web.xml -Denvironment=prod -DprojectName=HindustanTimes -DexecutionType=sanity -DplatformName=web_desktop -Dbrowser=chrome -DbrowserMode=normal -DisBrowserStack=true -DcapabilitiesJson=windows10_chrome_combined -DbuildNumber=123[any number]

III. Please use specific branch for each platform as mention below. No other branch should be created

IV. Below are the branch name mapping wrt platform: 

    * Desktop_Web:     web-desktop
    * Android Native:  android-native
    * IOs Native:      ios-native
    * Android Web:     android-mweb
    * IOs Web:         ios-mweb 
    * Main:            master
    * Code Merge:      dev
    * BlankPaper(CMS): blankpaper [for Jenkins job]
    * BlankPaper(CMS): blankpaper-dev [for script development]
     

V. Make sure to update or change the value of below columns to be set as either Y or N before initiating suite execution in the file testdata.xlsx (/src/main/resources/testdata.xlsx).

VI. Below are some key and value pairs which need to be specified against each test case in the testdata.xlsx file:
 
    * IsRunTrue:      Whether you want to run the selected test case or not.
    * Production:     Whether test case if applicable for production or not.
    * Sanity:         Whether test case if applicable for execution type sanity or not. 
    * Regression:     Whether test case if applicable for execution type regression or not.
    * WebDesktop:     Whether test case if applicable for platform web desktop or not.
    * WebAndroid:     Whether test case if applicable for platform web android or not.
    * WebIOS:         Whether test case if applicable for platform web ios or not.
    * AndroidAMP:     Whether test case if applicable for platform android amp or not.
    * IOSAMP:         Whether test case if applicable for platform ios amp or not.
    * AndroidNative:  Whether test case if applicable for platform android native or not.
    * IOSNative:      Whether test case if applicable for platform ios native or not.

VII. In order to debug test cases, please use testng.xml file for respective project and for proper execution please use above mention maven command. Also, comment all parameters tag in respective testng.xml file if you need to run test through maven command.