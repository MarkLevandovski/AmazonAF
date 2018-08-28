package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import po.*;
import utils.BrowserFactory;

/**
 * Created by Marek Lewandowski on 28/08/2018.
 */
public class AddToCartStepDefinition {
    private MainPage mainPage;
    private WebDriver driver;
    private ResultsPage resultsPage;
    private ItemDetailsPage itemDetailsPage;
    private CartPage cartPage;
    private ShoppingCartPage shoppingCartPage;

    @Given("^User in on Main Page$")
    public void userInOnMainPage() throws Throwable {
        driver = BrowserFactory.getBrowser("Firefox");
        mainPage = new MainPage(driver);
        driver.manage().deleteAllCookies();
        driver.get("http://amazon.com");
    }

    @Then("^User search for productName = (.*)$")
    public void userSearchForProductNameProductName(String productName) {
        resultsPage = mainPage.searchByName(productName);
    }

    @Then("^User selects itemNumber = (.*) item from the list$")
    public void userSelectsItemNumberItemNumberItemFromTheList(int itemNumber) {
        itemDetailsPage = resultsPage.selectGivenItem(itemNumber);
    }

    @Then("^User selects quantity = (.*) of product$")
    public void userSelectsQuantityQuantityOfProduct(int quantity) {
        itemDetailsPage.selectQuantity(quantity);
    }

    @Then("^User adds item to the cart$")
    public void userAddsItemToTheCart() {
        cartPage = itemDetailsPage.addToCart();
    }

    @Then("^Verify that product is added to the cart$")
    public void verifyThatProductIsAddedToTheCart() {
        cartPage.verifyProductAddedToCart();
        shoppingCartPage = cartPage.viewShoppingCart();
    }

    @Then("^Verify that subtotal price of products with quantity = (.*) is correct$")
    public void verifyThatSubtotalPriceOfProductsWithQuantityQuantityIsCorrect(int quantity) {
        Double sumItemsMultipliedByQuantity = shoppingCartPage.sumItems(quantity);
        Double displayedTotalPrice = shoppingCartPage.getSubtotalAmountFromField();

        Assert.assertEquals(displayedTotalPrice, sumItemsMultipliedByQuantity);
    }
}
