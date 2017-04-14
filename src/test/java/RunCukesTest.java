package test.java;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features= "src/cucumber",
        glue={"test.java"},
        plugin={"pretty"}
)
public class RunCukesTest {

}