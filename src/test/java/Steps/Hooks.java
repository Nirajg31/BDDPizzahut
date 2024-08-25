package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class Hooks
{
    public static WebDriver driver;
    @Before
    public void setup()
    {
        driver=new ChromeDriver();

    }
    @After
    public void tearDown()
    {
        driver.close();
    }
}
