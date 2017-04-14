package main.java.pageObjects;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResidenceInfo extends CommonUtils {

    public static final Logger logger = Logger.getLogger(ResidenceInfo.class);

    static {
        PropertyConfigurator.configure("resources/log4j.properties");
        logger.info("Enter Credit Application: Residence Information");
    }

    public void enterAddress(String address){
        driver.findElement(By.id("aginputaddress")).sendKeys(address);
    }

    public void enterCity(String city){
        driver.findElement(By.id("aginputcity")).sendKeys(city);
    }

    public void enterZip(String zip){
        driver.findElement(By.id("aginputzip")).sendKeys(zip);
    }

    public void enterMoveInDate(String date){
        driver.findElement(By.id("aginputmoveindate")).sendKeys(date);
    }

    public void enterPayment(String payment){
        driver.findElement(By.id("aginputpayment")).sendKeys(payment);
    }

    public void selectHouseInfo(){
        driver.findElement(By.id("agselecthouseinfo")).click();
        CommonUtils.waitForAnimation(2);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/span/div/div/div")).click();
        CommonUtils.waitForAnimation(2);
    }

    public void selectState(){
        driver.findElement(By.id("agselectstate")).click();
        CommonUtils.waitForAnimation(2);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[5]/span")).click();
        CommonUtils.waitForAnimation(2);
    }

    public String getDateTimeStamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    public EmploymentInfo clickSubmit(){
        WebElement button = CommonUtils.getElement(driver, "button", "type", "submit");
        button.click();
        CommonUtils.waitForAnimation(2);
        return new EmploymentInfo();
    }
}
