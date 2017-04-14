package main.java.pageObjects;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import test.utils.CommonUtils;

import java.util.List;

public class ModelPage extends CommonUtils {
	
	public static final Logger logger=Logger.getLogger(ModelPage.class);
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	public TrimPage selectModel(String model) {
		logger.info("Selecting a Vehicle Model: " + model);

        List<WebElement> elements = CommonUtils.getElementByPrefix(driver, "div", "class", "model");
        for (WebElement element : elements){
            if (element.getText().contains(model)) {
                logger.info("Clicking: " + element.getText());
                element.click();
                return new TrimPage();
            }
        }
		return null;
	}

}
