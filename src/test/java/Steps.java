package test.java;

import cucumber.api.java.en.Given;

import main.java.pageObjects.*;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;

public class Steps extends TestBaseSetup {
    VehiclePage vehiclePage;
    ModelPage modelPage;
    TrimPage trimPage;
    DealerPage dealerPage;
    CreditPage creditPage;
    PersonalInfo personalInfo;
    ResidenceInfo residenceInfo;
    EmploymentInfo employmentInfo;
    boolean completed = false;
	public static final Logger logger=Logger.getLogger(TestBaseSetup.class);
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}

    @Given("^I open the site \"([^\"]*)\"$")
    public void I_open_the_site(String url) throws Throwable {
        initializeTestBaseSetup("chrome", url);
    }

    @Given("^I select vehicle make \"([^\"]*)\"$")
    public void select_make(String make) throws Throwable {
        logger.info("Running Auto Gravity: Apply For Vehicle Financing");
        vehiclePage = new VehiclePage();
        Assert.assertTrue(vehiclePage.verifyLoginPageTitle(), "Auto Gravity Vehicle page title not as expected");
        Assert.assertNotNull(vehiclePage, "Failed to Initialize");

        modelPage = vehiclePage.selectMake(make);
        Assert.assertNotNull(modelPage, "Failed to Initialize");
    }

    @Given("^I select vehicle model \"([^\"]*)\"$")
    public void select_model(String model) throws Throwable {
        trimPage = modelPage.selectModel(model);
        Assert.assertNotNull(trimPage, "Failed to Initialize");
    }
    @Given("^I select vehicle trim \"([^\"]*)\"$")
    public void select_trim(String trim) throws Throwable {
        dealerPage = trimPage.selectTrim(trim);
        Assert.assertNotNull(dealerPage, "Failed to Initialize");
    }

    @Given("^I select zip \"([^\"]*)\" and dealership \"([^\"]*)\"$")
    public void select_dealership(String zip, String dealership) throws Throwable {
        creditPage = dealerPage.selectDealership(zip, dealership);
        Assert.assertNotNull(creditPage, "Failed to Initialize");
    }

    @Then("^I enter my Personal Information$")
    public void enter_personal_information() throws Throwable {
        personalInfo = creditPage.clickStartFinancing();
        Assert.assertNotNull(personalInfo, "Failed to Initialize");
    }

    @Then("^I enter my Residence Information$")
    public void enter_residence_information() throws Throwable {
        residenceInfo = personalInfo.enterPersonlInformation();
        Assert.assertNotNull(residenceInfo, "Failed to Initialize");
        residenceInfo.selectHouseInfo();
        residenceInfo.enterAddress("123 Fake St");
        residenceInfo.enterCity("Irvine");
        residenceInfo.selectState();
        residenceInfo.enterZip("90630");
        residenceInfo.enterMoveInDate("052010");
        residenceInfo.enterPayment("1200");
        employmentInfo = residenceInfo.clickSubmit();
        Assert.assertNotNull(employmentInfo, "Failed to Initialize");
    }

    @Then("^I enter my Employment Information$")
    public void enter_employment_information() throws Throwable {
        employmentInfo.selectEmploymentStatus();
        employmentInfo.enterEmployerName("NASA");
        employmentInfo.enterEmployerTitle("Engineer");
        employmentInfo.enterEmployePhone("5621001234");
        employmentInfo.enterEmployeeStartDate("012001");
        employmentInfo.enterMonthlyIncome("5200");
        Identification identification = employmentInfo.clickSubmit();
        Assert.assertNotNull(identification, "Failed to Initialize");
	}

    @Then("^application should be closed$")
    public void application_should_be_closed() throws Throwable {
        tearDown();
        completed = true;
    }

    @After
    public void cleanUp(){
	    logger.info("Closing Driver");
	    if (completed == false){
	        logger.info("FAIL: Tests weren't completed");
            driver.quit();
	        Assert.fail("FAIL: Marking Test as failed");
        }
        driver.quit();
    }
}