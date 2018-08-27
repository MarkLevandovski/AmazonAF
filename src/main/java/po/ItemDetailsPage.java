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

public class ItemDetailsPage extends MainPage {
    public final static Logger LOGGER= LoggerFactory.getLogger(ItemDetailsPage.class);

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    private By quantitySelect = By.id("quantity");
    private By addToCartButton = By.id("add-to-cart-button");
    private By regularBuyBox = By.id("regularBuybox");
    private By buyBox = By.id("buybox_accordion");

    public ItemDetailsPage selectQuantity(int quantity) {
        if (driver.findElements(buyBox).size() > 0) {
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

        return this;
    }

    public CartPage addToCart() {
        driver.findElement(addToCartButton).click();

        return new CartPage(driver);
    }
}
