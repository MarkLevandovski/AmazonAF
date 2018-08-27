package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.SeleniumUtils;

public class ShoppingCartPage extends MainPage {
    public final static Logger LOGGER= LoggerFactory.getLogger(ShoppingCartPage.class);
    Double itemsSum;
    Double totalSum;
    Double subtotalDisplayedSum;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    private By itemsPrices = By.className("sc-product-price");
    private By subtotalAmountField = By.id("sc-subtotal-amount-activecart");

    public void sumItems(int quantity) {
        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for cart details table...");
                return driver.findElement(itemsPrices) != null;
            }
        });

//        for (int i = 0; i < driver.findElements(itemsPrices).size(); i++) {
//            itemsSum = itemsSum + Double.valueOf(driver.findElements(itemsPrices).get(i).getText().substring(1));
//        }

        itemsSum = Double.valueOf(driver.findElements(itemsPrices).get(0).getText().substring(1));
        LOGGER.info("Item price: " + itemsSum);

        totalSum = itemsSum * quantity;

        LOGGER.info("Subtotal sum: " + totalSum);

        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for subtotal amount...");
                return driver.findElement(subtotalAmountField) != null;
            }
        });

        subtotalDisplayedSum = Double.valueOf(driver.findElement(subtotalAmountField).getText().substring(1));
        LOGGER.info("Displayed amount: " + subtotalDisplayedSum);
        Assert.assertEquals(subtotalDisplayedSum, totalSum);
    }
}
