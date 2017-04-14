package main.java.pageObjects;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;

public class EmploymentInfo extends CommonUtils {

    public static final Logger logger = Logger.getLogger(EmploymentInfo.class);

    static {
        PropertyConfigurator.configure("resources/log4j.properties");
        logger.info("Enter Credit Application: Employment Information");
    }

    public void selectEmploymentStatus(){
        driver.findElement(By.id("agselectemployment-status")).click();
        CommonUtils.waitForAnimation(2);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/span/div/div")).click();
        CommonUtils.waitForAnimation(2);
    }

    public void enterEmployerName(String name){
        driver.findElement(By.id("employer-name")).sendKeys(name);
    }

    public void enterEmployerTitle(String title){
        driver.findElement(By.id("employee-title")).sendKeys(title);
    }

    public void enterEmployeeStartDate(String date){
        driver.findElement(By.id("employee-start-date")).sendKeys(date);
    }

    public void enterEmployePhone(String phone){
        driver.findElement(By.id("employer-phone-number")).sendKeys(phone);
    }

    public void enterMonthlyIncome(String amount){
        driver.findElement(By.id("gross-monthly-income")).sendKeys(amount);
    }

    public Identification clickSubmit(){
        WebElement button = CommonUtils.getElement(driver, "button", "type", "submit");
        button.click();
        CommonUtils.waitForAnimation(2);
        return new Identification();
    }
}
