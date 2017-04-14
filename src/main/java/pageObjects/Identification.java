package main.java.pageObjects;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import test.utils.CommonUtils;

public class Identification extends CommonUtils {

    public static final Logger logger = Logger.getLogger(Identification.class);

    static {
        PropertyConfigurator.configure("resources/log4j.properties");
    }

    public Identification enterSSN() {
        logger.info("Enter Credit Application: Identification");

        /* TOOD: end tests here */

        CommonUtils.waitForAnimation(2);
        return new Identification();
    }
}
