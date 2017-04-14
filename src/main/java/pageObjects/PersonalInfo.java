package main.java.pageObjects;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;

public class PersonalInfo extends CommonUtils {

    public static final Logger logger = Logger.getLogger(PersonalInfo.class);

    static {
        PropertyConfigurator.configure("resources/log4j.properties");
    }

    public ResidenceInfo enterPersonlInformation() {
        logger.info("Enter Credit Application: Personal Information");

        driver.findElement(By.id("firstNameTextField")).sendKeys("Test");
        driver.findElement(By.id("lastNameTextField")).sendKeys("Automation");
        driver.findElement(By.id("dobTextField")).sendKeys("10301980");
        driver.findElement(By.id("mobilePhoneTextField")).sendKeys("9491231234");
        driver.findElement(By.id("homePhoneTextField")).sendKeys("3101231234");
        driver.findElement(By.id("emailTextField")).sendKeys("test."+getDateTimeStamp()+"@test.com");
        driver.findElement(By.id("passwordTextField")).sendKeys("Password1!");
        driver.findElement(By.id("confirmPasswordTextField")).sendKeys("Password1!");

        WebElement button = CommonUtils.getElement(driver, "button", "type", "submit");
        button.click();
        CommonUtils.waitForAnimation(2);
        return new ResidenceInfo();
    }
}
