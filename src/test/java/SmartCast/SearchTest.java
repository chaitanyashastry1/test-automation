package SmartCast;

import com.annotation.Author;
import com.annotation.Description;
import com.base.TestBase;
import com.pages.SmartCast.generic.CommonSearchPage;
import com.utils.Constants;
import com.utils.GlobalVars;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Map;

public class SearchTest {
	private static CommonSearchPage SearchPage;
	GlobalVars globalVars;

	@BeforeSuite
	public void initialization() {
		globalVars = TestBase.setup(Constants.SMART_CAST);
	}

	@BeforeMethod
	public void beforeMethod() {
		SearchPage = CommonSearchPage.getInstance();
	}

	@Author(name = "Chaitanya Shastry")
	@Description("QAAUT-954: To verify Search functionality of HT SmartCast")
	@Test
	public void verifySearchResults(){
		boolean isStepTrue;
		Map<String, String> paramsMap=globalVars.getParamsData1("verifySearchResults");
		isStepTrue = SearchPage.getShowsSearchResults(paramsMap.get("expectedText"));
		Assert.assertTrue(isStepTrue,"Actual and Expected Searched Text for Shows are not matched");
		isStepTrue = SearchPage.getEpisodesSearchResults(paramsMap.get("expectedText"));
		Assert.assertTrue(isStepTrue, "Actual and Expected Searched Text for Episodes are not matched");
	}

	@AfterSuite
	public void closeDriver(ITestContext context) { TestBase.tearDownSuite(context); }

}
