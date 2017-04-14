package test.utils;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class CommonUtils {

	public static final Logger logger=Logger.getLogger(CommonUtils.class);
	static {
        PropertyConfigurator.configure("resources/log4j.properties");
	}
	public WebDriverWait wait;
	public Actions actions;
	public Select select;	
	public static WebDriver driver;

    public void setDriver(String browserType, String appURL) {
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = initChromeDriver(appURL);

        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = initFirefoxDriver(appURL);

        } else {
            logger.info("browser : " + browserType
                    + " is invalid, Launching Chrome as browser of choice..");
            driver = initChromeDriver(appURL);
        }
    }

    public WebDriver initChromeDriver(String appURL) {
        logger.info("Launching Chrome browser..");
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--kiosk");
        driver = new ChromeDriver(chromeOptions);

        logger.info("Navigating to page: " + appURL);
        navigateToURL(appURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public WebDriver initFirefoxDriver(String appURL) {
        logger.info("Launching Firefox browser..");
        System.setProperty("webdriver.gecko.driver","geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        logger.info("Navigating to page: " + appURL);
        navigateToURL(appURL);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }
	
	public void quitDriver () {
		driver.quit();
	}
	
    public void navigateToURL(String URL) {
    	logger.info("Navigating to: " + URL);
        try {
            driver.navigate().to(URL);
        } catch (Exception e) {
        	logger.info("URL did not load: " + URL);
        	captureScreenShot(URL);
            throw new TestException("URL did not load");
        }
    }
    
    public String getPageTitle() {
        try {
        	logger.info(String.format("The title of the page is: %s\n\n", driver.getTitle()));
            return driver.getTitle();
        } catch (Exception e) {
            throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
        }
    }
    
    public WebElement getElement(By selector) {
        try {
            return driver.findElement(selector);
        } catch (Exception e) {
        	logger.info(String.format("Element %s does not exist - proceeding", selector));
        	captureScreenShot(selector.toString());
        }
        return null;
    }
    
    public List<WebElement> getElements(By selector) {
        try {
        	List<WebElement> elements = driver.findElements(selector);
        	if (elements.size() > 0)
        		return elements;
        } catch (Exception e) {
        	logger.info(String.format("Element %s does not exist - proceeding", selector));
        	captureScreenShot(selector.toString());
        }
        return null;
    }
    
    public String getElementText(By selector){
        waitForElementToBeVisible(selector);
        try{
            return StringUtils.trim(driver.findElement(selector).getText());
        }catch (Exception e){
        	logger.info(String.format("Element %s does not exist - proceeding", selector));
        	captureScreenShot(selector.toString());
        }
        return null;
    }
    
    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
        	logger.info(String.format("The following element could not be cleared: [%s]", element.getText()));
        	captureScreenShot(element.toString());
        }
    }
    
    public void click(By selector) {
        WebElement element = getElement(selector);
        waitForElementToBeClickable(selector);
        try {
            element.click();
        } catch (Exception e) {
        	captureScreenShot(selector.toString());
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }
    }

    public void waitForElementToBeClickable(By selector ) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
        	captureScreenShot(selector.toString());
            throw new TestException("The following element is not clickable: " + selector);
        }
    }
    
    public void waitForElementToBeVisible(By selector) {
        try {
            wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
        	captureScreenShot(selector.toString());
            throw new NoSuchElementException(String.format("The following element was not visible within [%s] seconds: %s ", "10".toString(), selector));
        }
    }
    
    public void waitForPageToLoad() {
    	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
    
    public void captureScreenShot(String screenShotName) {
    	try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./Screenshots/"+screenShotName+".png"));
    		logger.info("Screen Shot Taken");
    	} catch (Exception e) {
    		logger.info("Exception while taking Screen Shot " + e.getMessage());
    	}
    }

    public static List<WebElement> getByIdPrefix(WebDriver driver, String htmlTag, String prefix) {
        return getElementByPrefix(driver, htmlTag, "id", prefix);
    }

    public static List<WebElement> getElementByPrefix(WebDriver driver, String htmlTag, String attribute, String prefix) {
        String selector = StringFormatter.format("{0}[{1}^='{2}']", htmlTag, attribute, prefix);
        return driver.findElements(By.cssSelector(selector));
    }

    public static WebElement getByFirstPrefix(WebDriver driver, String htmlTag, String attribute, String prefix) {
        String selector = StringFormatter.format("{0}[{1}^='{2}']", htmlTag, attribute, prefix);
        return driver.findElement(By.cssSelector(selector));
    }

    public static WebElement getElementByPrefix(WebElement element, String htmlTag, String attribute, String prefix) {
        String selector = StringFormatter.format("{0}[{1}^='{2}']", htmlTag, attribute, prefix);
        return element.findElement(By.cssSelector(selector));
    }

    public static WebElement getById(WebDriver driver, String htmlTag, String value) {
        return getElement(driver, htmlTag, "id", value);
    }

    public static WebElement getElement(WebDriver driver, String htmlTag, String attribute, String value) {
        String selector = StringFormatter.format("{0}[{1}='{2}']", htmlTag, attribute, value);
        try {
            return driver.findElement(By.cssSelector(selector));
        } catch (java.util.NoSuchElementException nse) {

        }
        return null;
    }

    public static List<WebElement> getElements(WebDriver driver, String htmlTag, String attribute, String value) {
        String selector = StringFormatter.format("{0}[{1}='{2}']", htmlTag, attribute, value);
        try {
            return driver.findElements(By.cssSelector(selector));
        } catch (java.util.NoSuchElementException nse) {

        }
        return null;
    }

    public static void waitForAnimation(int time) {
        try {
            time *= 1000;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.warn("for some reason I failed to sleep for the entire timeout");
        }
    }

    public String getDateTimeStamp(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
