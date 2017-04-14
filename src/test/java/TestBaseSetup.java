package test.java;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import test.utils.CommonUtils;

public class TestBaseSetup extends CommonUtils {
	public static final Logger logger=Logger.getLogger(TestBaseSetup.class);
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}

	@BeforeClass()
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			logger.info("Setting browser: " + browserType);
            logger.info("URL: " + appURL);
			setDriver(browserType, appURL);
		} catch (Exception e) {
			logger.info("Error setting browser/appurl: " + e.getStackTrace());
		}
	}

	@AfterClass()
	public void tearDown() {
		logger.info("shutting down driver");
        quitDriver();
	}

}