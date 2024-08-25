package Steps;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        monochrome = true,
        plugin ={ "pretty","html:target/html-cucumber"},
        features ="src/test/java/Features",
        glue = "Steps",
        tags = "@Pizzahut"
)
public class Runner
{

}
