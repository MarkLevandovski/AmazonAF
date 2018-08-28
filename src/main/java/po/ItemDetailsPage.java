package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SeleniumUtils;

import java.util.List;

/**
 * Class represents Item Details Page
 */
public class ItemDetailsPage extends MainPage {
    public final static Logger LOGGER = LoggerFactory.getLogger(ItemDetailsPage.class);

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    private By addToCartButton = By.id("add-to-cart-button");
    private By seeBuyingOptionsButton = By.id("buybox-see-all-buying-choices-announce");
    private By regularBuyBox = By.id("regularBuybox");
    private By unqualifiedBuyBox = By.id("unqualifiedBuyBox");
    private By qualifiedBuyBox = By.id("qualifiedBuybox");
    private By quantitySelect = By.id("quantity");
    private By addToCartFromOption = By.name("submit.addToCart");

    /**
     * Method that selects given quantity
     *
     * @param quantity
     * @return ItemDetailsPage
     */
    public ItemDetailsPage selectQuantity(int quantity) {
        // in case when regular box is not displayed quantity has to be selected on shopping cart
        if (driver.findElements(unqualifiedBuyBox).size() > 0) {
            driver.findElement(seeBuyingOptionsButton).click();
            SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    LOGGER.info("Waiting for buying options ... quantity has to be selected inside the Cart");
                    return driver.findElement(addToCartFromOption) != null;
                }
            });
        } else {
            // in case that there is special offer and regular box is not displayed
            if (driver.findElements(regularBuyBox).size() > 0) {
                driver.findElement(regularBuyBox).click();
            }

            SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    LOGGER.info("Waiting for regular buy box....");
                    return driver.findElement(addToCartButton) != null;
                }
            });

            Select select = new Select(driver.findElement(quantitySelect));
            List<WebElement> quantities = select.getOptions();
            LOGGER.info("Available quantities for product: " + quantities.size());

            if (quantities.size() >= quantity) {
                LOGGER.info("Quantity available! " + String.valueOf(quantity));
                select.selectByVisibleText(String.valueOf(quantity));
            } else {
                LOGGER.info("Quantity not available!");
            }
        }

        return this;
    }

    /**
     * Method that adds selected items to the cart
     *
     * @return CartPage
     */
    public CartPage addToCart() {
        if (driver.findElements(addToCartFromOption).size() > 0) {
            SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    LOGGER.info("Waiting for buying options ...");
                    return driver.findElement(addToCartFromOption) != null;
                }
            });
            driver.findElements(addToCartFromOption).get(0).click();
        } else {
            driver.findElement(addToCartButton).click();
        }

        return new CartPage(driver);
    }
}
