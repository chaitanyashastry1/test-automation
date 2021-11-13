package LiveMint;

import com.annotation.Author;
import com.base.TestBase;
import com.pages.LiveMint.generic.CommonLoginPage;
import com.utils.Constants;
import com.utils.GlobalVars;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class LoginTest{
    private static CommonLoginPage loginPage;
    GlobalVars globalVars;

    @BeforeSuite
    public void initialization(){
        globalVars = TestBase.setup(Constants.LIVE_MINT);
    }

    @BeforeMethod
    public void beforeMethod(){
        loginPage=CommonLoginPage.getInstance();
    }

    @Author(name = "Ataur Rahman")
    @Test
    public void verifyLoginWithEmailAndPassword(){
        boolean isStepTrue;
        String[] params = globalVars.getParamsData("verifyLoginWithEmailAndPassword");
        String email = params[0];
        String password = params[1];
        String expectedUserName=params[2];
        isStepTrue = loginPage.loginWithPassword(email, password, expectedUserName);
        Assert.assertTrue(isStepTrue, "Login with Email and Password Failed");
        isStepTrue = loginPage.logout();
        Assert.assertTrue(isStepTrue, "Logout with Email and Password Failed!!");
    }

    @Author(name = "Ataur Rahman")
    @Test
    public void verifyLoginWithOTP(){
        boolean isStepTrue;
        String[] params = globalVars.getParamsData("verifyLoginWithOTP");
        String email = params[0];
        String password = params[1];
        String expectedUserName=params[2];
        isStepTrue = loginPage.loginWithOTP(email, password, "Verification OTP", expectedUserName);
        Assert.assertTrue(isStepTrue, "Login with OTP failed");
        isStepTrue = loginPage.logout();
        Assert.assertTrue(isStepTrue, "Logout with OTP failed!!");
    }

    @Author(name = "Ataur Rahman")
    @Test
    public void verifyLoginWithMobileAndPassword(){
        boolean isStepTrue;
        String[] params = globalVars.getParamsData("verifyLoginWithMobileAndPassword");
        String phone = params[0];
        String password = params[1];
        String expectedUserName=params[2];
        isStepTrue = loginPage.loginWithPassword(phone, password, expectedUserName);
        Assert.assertTrue(isStepTrue, "Login with Mobile and Password Failed");
        isStepTrue = loginPage.logout();
        Assert.assertTrue(isStepTrue, "Logout with Mobile and Password Failed!!");
    }

    @Author(name = "Ataur Rahman")
    @Test
    public void verifyLoginWithFacebook() {
        boolean isStepTrue;
        String[] params=globalVars.getParamsData("verifyLoginWithFacebook");
        String email=params[0];
        String password=params[1];
        String expectedUserName=params[2];
        isStepTrue=loginPage.loginWithFacebook(email, password, expectedUserName);
        Assert.assertTrue(isStepTrue, "Login with Facebook account failed");
        isStepTrue=loginPage.logout();
        Assert.assertTrue(isStepTrue, "Logout from Facebook failed!!");
    }

    @Author(name = "Ataur Rahman")
    @Test
    public void verifyLoginWithGoogle() {
        boolean isStepTrue;
        String[] params=globalVars.getParamsData("verifyLoginWithFacebook");
        String email=params[0];
        String password=params[1];
        String expectedUserName=params[2];
        isStepTrue=loginPage.loginWithGoogle(email, password, expectedUserName);
        Assert.assertTrue(isStepTrue, "Login with Google account failed");
        isStepTrue=loginPage.logout();
        Assert.assertTrue(isStepTrue, "Logout from Google failed!!");
    }

    @AfterSuite
    public void closeDriver(ITestContext context){
        TestBase.tearDownSuite(context);
    }
}
