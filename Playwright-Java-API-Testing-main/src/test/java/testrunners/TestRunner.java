package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/APIFeatures"},
		glue = {"stepDefinitions", "AppHooks"},
		plugin = {"pretty",
				"html:target/cucumber-html-report",
				"json:target/cucumber.json",
				"junit:target/cucumber.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/rerun.txt"	
		}
		
		)

public class TestRunner {

}
