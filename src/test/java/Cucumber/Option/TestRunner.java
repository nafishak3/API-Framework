package Cucumber.Option;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/featureFiles/placeValidation.feature",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = {"StepDefinations"},
        tags = "@DeletePlace"
)
public class TestRunner {


}
