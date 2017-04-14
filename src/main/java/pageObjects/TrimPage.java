package main.java.pageObjects;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;


import java.util.List;

public class TrimPage extends CommonUtils {
	
	public static final Logger logger=Logger.getLogger(TrimPage.class);
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	public DealerPage selectTrim(String trim) {
		logger.info("Selecting a Vehicle Trim");
        List<WebElement> trims_options = CommonUtils.getElementByPrefix(driver, "div", "class", "trimRow");
        for (WebElement option : trims_options){
            if (option.getText().contains(trim)) {
                logger.info("Clicking: " + option.getText());
                option.click();
                CommonUtils.waitForAnimation(5);
                break;
            }
        }

        List<WebElement> buttons = driver.findElements(By.xpath("//button"));
        for (WebElement button : buttons) {
            if (button.getText().equalsIgnoreCase("SELECT LOAN")) {
                logger.info("Clicking Button: " + button.getText());
                button.click();
                return new DealerPage();
            }
        }
        return null;
	}
}
