package SmartCast;

import com.annotation.Author;
import com.annotation.Description;
import com.base.TestBase;
import com.pages.SmartCast.generic.CommonHomePage;
import com.utils.Constants;
import com.utils.GlobalVars;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;

public class HomeTest {
	private static CommonHomePage homePage;
	GlobalVars globalVars;

	@BeforeSuite
	public void initialization() {
		globalVars = TestBase.setup(Constants.SMART_CAST);
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage = CommonHomePage.getInstance();
	}

	@Author(name = "Chaitanya Shastry")
	@Description("QAAUT-873: To verify Page Title of HT Smartcast")
	@Test
	public void verifyGetHomePageTitleTest() {
		Map<String, String> paramsMap=globalVars.getParamsData1("verifyGetHomePageTitleTest");
		String title = homePage.getSmartCastTitle(paramsMap.get("expectedTitle"));
		Assert.assertEquals(title, paramsMap.get("expectedTitle"),"Actual and Expected Title not matched");
	}
	
	@Author(name = "Chaitanya Shastry")
	@Description("QAAUT-929: To verify Logo of HT Smartcast")
	@Test
	public void verifyClickSmartCastLogoImageTest() {
		boolean isDisplayed = homePage.clickSmartCastLogo();
		Assert.assertTrue(isDisplayed , "Logo is not Clickable");
	}

	@Author(name = "Chaitanya Shastry")
	@Description("QAAUT-963: To verify Hamburger Menu and its components.")
	@Test
	public void verifyHamburger() {
		boolean isStepTrue;
		isStepTrue = homePage.checkHamburger();
		Assert.assertTrue(isStepTrue, "Hamburger Options are empty");
	}

	@AfterSuite
	public void closeDriver(ITestContext context) { TestBase.tearDownSuite(context); }

}
