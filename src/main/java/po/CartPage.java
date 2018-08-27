package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SeleniumUtils;

public class CartPage extends MainPage {
    public final static Logger LOGGER= LoggerFactory.getLogger(CartPage.class);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By cartSubtotalBox = By.id("hlb-subcart");
    private By fullPrize = By.className("hlb-price");
    private By successMessage = By.className("huc-v2-color-success");
    private By cartButton = By.id("hlb-view-cart-announce");

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

    public ShoppingCartPage viewShoppingCart() {
        driver.findElement(cartButton).click();

        return new ShoppingCartPage(driver);
    }
}
