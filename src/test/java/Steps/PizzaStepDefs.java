package Steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PizzaStepDefs
{


    int Actprice;
    int carfinalvalue;

    WebDriver driver= Hooks.driver;
    @Given("I have launched the application")
    public void i_have_launched_the_application()
    {
        driver.manage().window().maximize();
        driver.get("https://www.pizzahut.co.in/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @When("I enter the location as {string}")
    public void i_enter_the_location_as(String location)
    {
        WebElement locelement =driver.findElement(By.xpath("//input[contains(@class, 'search--hut')]"));
        locelement.click();
        locelement.sendKeys(location);
    }
    @When("I select the very first suggestion from the list")
    public void i_select_the_very_first_suggestion_from_the_list()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'suggestion-item')][1]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0,250);", firstSuggestion);
        firstSuggestion.click();
        System.out.println(firstSuggestion);


    }
    @Then("I should land on the Deals page")
    public void i_should_land_on_the_deals_page()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("/deals"));
    }
    @Then("I select the tab as {string}")
    public void i_select_the_tab_as(String selectTab)
    {

        WebElement tab = driver.findElement(By.linkText(selectTab));
        tab.click();


    }
    @Then("I add {string} to the basket")
    public void i_add_to_the_basket(String pizzaName)
    {

        WebElement pizzaItem = driver.findElement(By.xpath("//div[contains(text(), '"+pizzaName+"')]/following-sibling::div//button[contains(@class, 'button--green')]"));
        pizzaItem.click();



    }
    @Then("I note down the price displayed on the screen")
    public void i_note_down_the_price_displayed_on_the_screen()
    {
        WebElement priceElement = driver.findElement(By.xpath("//div[contains(text(), 'Schezwan Margherita')]/following-sibling::div//button/span[2]"));
        String price = priceElement.getText();
        String priceNumeric = price.replace("₹", "").trim();
         Actprice = Integer.parseInt(priceNumeric);



    }
    @Then("I should see the pizza {string} is added to the cart")
    public void i_should_see_the_pizza_is_added_to_the_cart(String pizzaName)
    {
WebElement getpizza=driver.findElement(By.xpath("//div[contains(@class, 'basket-item-product-title')]"));
Assert.assertEquals(getpizza.getText(),pizzaName);

    }
    @Then("the price is also displayed correctly")
    public void the_price_is_also_displayed_correctly()
    {
        WebElement cartPriceElement = driver.findElement(By.className("basket-item-product-price"));
        String cartPrice = cartPriceElement.getText();
       String cartvalue= cartPrice.replace("₹","").trim();
        carfinalvalue=Integer.parseInt(cartvalue);
        Assert.assertEquals(carfinalvalue,Actprice);


           }
    @Then("I click on the Checkout button")
    public void i_click_on_the_checkout_button()
    {

WebElement additem=driver.findElement(By.className("basket-upsell-carousel-product-title"));
additem.click();


        WebElement checkoutButton = driver.findElement(By.xpath("//span[text()=\"Checkout\"]"));
        checkoutButton.click();
    }
    @Then("I should be landed on the secured checkout page")
    public void i_should_be_landed_on_the_secured_checkout_page()
    {

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("checkout"));
    }
    @Then("I enters the personal details into below fields")
    public void i_enter_the_personal_details(DataTable userDatatable)
    {
Map<String,String> dataMap=userDatatable.asMap(String.class,String.class);

String name= dataMap.get("Name");
String Mobile=dataMap.get("Mobile");
String Email=dataMap.get("Email");

        WebElement inputName= driver.findElement(By.id("checkout__name"));
        inputName.sendKeys(name);
        WebElement inputMobile= driver.findElement(By.id("checkout__phone"));
        inputMobile.sendKeys(Mobile);
        WebElement inputEmail= driver.findElement(By.id("checkout__email"));
        inputEmail.sendKeys(Email);

    }
    @Then("I enter the address details")
    public void i_enter_the_address_details(DataTable AddressdataTable)
    {

        List<List<String>> data = AddressdataTable.asLists(String.class);
        String Address = data.get(0).get(0);
        String landMarks = data.get(1).get(0);

        WebElement inputAddress= driver.findElement(By.xpath("//input[@placeholder=\"House Number or Name\"]"));
        inputAddress.sendKeys(Address);

        WebElement inputlandMarks=driver.findElement(By.xpath("//input[@placeholder=\"Landmark (Optional)\"]"));
        inputlandMarks.sendKeys(landMarks);


    }

}
