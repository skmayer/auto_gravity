package main.java.pageObjects;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;

public class CreditPage extends CommonUtils {

    public static final Logger logger = Logger.getLogger(CreditPage.class);

    static {
        PropertyConfigurator.configure("resources/log4j.properties");
    }

    public PersonalInfo clickStartFinancing() {
        logger.info("Start Financing");

        WebElement button = CommonUtils.getElement(driver, "button", "type", "submit");
        button.click();
        CommonUtils.waitForAnimation(2);
        return new PersonalInfo();
    }
}
