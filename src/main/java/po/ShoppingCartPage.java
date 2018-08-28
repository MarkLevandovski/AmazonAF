package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SeleniumUtils;

/**
 * Class that represents Shopping Cart Page
 */
public class ShoppingCartPage extends MainPage {
    public final static Logger LOGGER = LoggerFactory.getLogger(ShoppingCartPage.class);
    Double itemsSum = 0.0;
    Double subtotalDisplayedSum;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    private By itemsPrices = By.className("sc-product-price");
    private By subtotalAmountField = By.id("sc-subtotal-amount-activecart");

    /**
     * Method that prints displayed on the page Subtotal price
     * @return subtotalDisplayedSum
     */
    public Double getSubtotalAmountFromField() {
        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for subtotal amount...");
                return driver.findElement(subtotalAmountField) != null;
            }
        });

        subtotalDisplayedSum = Double.valueOf(driver.findElement(subtotalAmountField).getText().substring(1));
        LOGGER.info("Displayed amount: " + subtotalDisplayedSum);

        return subtotalDisplayedSum;
    }

    /**
     * Method that sum single item
     * @param quantity
     * @return
     */
    public Double sumItems(int quantity) {
        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for cart details table...");
                return driver.findElement(itemsPrices) != null;
            }
        });

        Double itemPrice = Double.valueOf(driver.findElements(itemsPrices).get(0).getText().substring(1));
        itemsSum = itemPrice * quantity;

        LOGGER.info("Subtotal sum: " + itemsSum);

        return itemsSum;
    }
}
