package POC;

import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@ExtendedCucumberOptions(
jsonReport = "target/cucumber.json",
retryCount = 3,
jsonUsageReport = "target/cucumber-usage.json",
outputFolder = "target")

@CucumberOptions(
plugin = { "html:target/cucumber-html-report",
        "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
        "usage:target/cucumber-usage.json",
        "junit:target/cucumber-results.xml"
        },
features = {"Feature_Repo/POC/Test001.feature"}

)

public class POC_Runner {

}
