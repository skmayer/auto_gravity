package main.java.pageObjects;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;

import java.util.List;

public class VehiclePage extends CommonUtils {
	
	public static final Logger logger=Logger.getLogger(VehiclePage.class);
    static {
        PropertyConfigurator.configure("resources/log4j.properties");
    }
	private String loginPageTitle = "AutoGravity: Choose a Car";
	
	public boolean verifyLoginPageTitle() {
		logger.info("Verifying Login Page Title");
		String title = getPageTitle();
		logger.info("Login Page Title: " + title);
		return title.contains(loginPageTitle);
    }
	
	public ModelPage selectMake(String make) {
		logger.info("Selecting a Vehicle Make: " + make);

        List<WebElement> elements = driver.findElements(By.xpath("//h5"));
        for (WebElement element : elements){
            if (element.getText().equalsIgnoreCase(make)) {
                logger.info("Clicking: " + element.getText());
                element.click();
                return new ModelPage();
            }
        }
        return null;
    }
}
