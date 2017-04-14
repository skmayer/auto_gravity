package main.java.pageObjects;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;


import java.util.List;

public class DealerPage extends CommonUtils {

    public static final Logger logger = Logger.getLogger(DealerPage.class);

    static {
        PropertyConfigurator.configure("resources/log4j.properties");
    }

    public CreditPage selectDealership(String zip, String dealership) {
        logger.info("Selecting a Dealership");
        enterZipCode(zip);
        CommonUtils.waitForAnimation(5);

        List<WebElement> elements = CommonUtils.getElementByPrefix(driver, "div", "class", "dealerListItem");
        for (WebElement element : elements) {
            if (element.getText().contains(dealership)) {
                logger.info("Clicking Dealership: " + element.getText());
                element.click();
                CommonUtils.waitForAnimation(2);
                break;
            }
        }

        WebElement button = CommonUtils.getElement(driver, "button", "class", "btn btn-primary");
        button.click();
        return new CreditPage();
    }

    public void enterZipCode(String zip) {
        logger.info("Entering Zip: " + zip);

        WebElement element = CommonUtils.getElement(driver, "input", "type", "text");
        element.sendKeys(zip);
        element.sendKeys(Keys.RETURN);
    }
}
