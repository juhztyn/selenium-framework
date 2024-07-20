package runners;//package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true,
        tags = "@DefaultTag"
)
public class TestNGCucumberRunnerTest extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    static {
        // Set the tags from system property if provided
        String tags = System.getProperty("cucumber.filter.tags");
        if (tags != null && !tags.isEmpty()) {
            System.setProperty("cucumber.filter.tags", tags);
        }
    }
}