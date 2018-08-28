package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SeleniumUtils;

/**
 * Class represents Cart Page
 */
public class CartPage extends MainPage {
    public final static Logger LOGGER = LoggerFactory.getLogger(CartPage.class);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By successMessage = By.className("huc-v2-color-success");
    private By cartButton = By.id("hlb-view-cart-announce");

    /**
     * Method verifies that success message is displayed
     *
     * @return CartPage
     */
    public CartPage verifyProductAddedToCart() {
        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for adding result message...");
                return driver.findElement(successMessage) != null;
            }
        });

        return this;
    }

    /**
     * Method that goes to Shopping Cart page
     *
     * @return ShoppingCartPage
     */
    public ShoppingCartPage viewShoppingCart() {
        driver.findElement(cartButton).click();

        return new ShoppingCartPage(driver);
    }
}
