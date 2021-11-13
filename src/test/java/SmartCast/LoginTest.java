package SmartCast;

import com.annotation.Author;
import com.annotation.Description;
import com.base.TestBase;
import com.pages.SmartCast.generic.CommonLoginPage;
import com.utils.Constants;
import com.utils.GlobalVars;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest {
    private static CommonLoginPage loginPage;
    GlobalVars globalVars;

    @BeforeSuite
    public void initialization(){
        globalVars = TestBase.setup(Constants.SMART_CAST);
    }

    @BeforeMethod
    public void beforeMethod(){
        loginPage=CommonLoginPage.getInstance();
    }

    @Author(name = "Chaitanya Shastry")
    @Description("QAAUT-935: To verify Login with Email functionality of HT SmartCast")
    @Test
    public void verifyLoginWithEmailAndPassword(){
        boolean isStepTrue;
        Map<String, String> paramsMap=globalVars.getParamsData1("verifyLoginWithEmailAndPassword");
        isStepTrue=loginPage.loginWithEmailAndPassword(paramsMap.get("email"), paramsMap.get("password"), paramsMap.get("expectedUserName"));
        Assert.assertTrue(isStepTrue, "Login with Email and Password Failed");
        isStepTrue = loginPage.signOut();
        Assert.assertTrue(isStepTrue, "Logout with Email and Password Failed!!");
    }

    @Author(name = "Chaitanya Shastry")
    @Description("QAAUT-953: To verify Login with Mobile No. functionality of HT SmartCast")
    @Test
    public void verifyLoginWithMobileAndPassword(){
        boolean isStepTrue;
        Map<String, String> paramsMap=globalVars.getParamsData1("verifyLoginWithMobileAndPassword");
        isStepTrue=loginPage.loginWithEmailAndPassword(paramsMap.get("phone"), paramsMap.get("password"), paramsMap.get("expectedUserName"));
        Assert.assertTrue(isStepTrue, "Login with Mobile and Password Failed");
        isStepTrue = loginPage.signOut();
        Assert.assertTrue(isStepTrue, "Logout with Mobile and Password Failed!!");
    }

    @AfterSuite
    public void closeDriver(ITestContext context){
        TestBase.tearDownSuite(context);
    }
}
