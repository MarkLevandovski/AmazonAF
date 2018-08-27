package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SeleniumUtils;

public class ResultsPage extends MainPage {
    public final static Logger LOGGER= LoggerFactory.getLogger(ResultsPage.class);

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    private By resultsCount = By.id("s-result-count");
    private By items = By.className("s-access-detail-page");

    public ItemDetailsPage selectGivenItem(int itemNumber) {
        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for Result count field...");
                return driver.findElement(resultsCount) !=  null;
            }
        });

        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for Items list....");
                return driver.findElements(items).size() > 0;
            }
        });

        if (driver.findElements(items).size() >= itemNumber) {
            driver.findElements(items).get(itemNumber).click();
        } else {
            LOGGER.info("Less items than expected");
        }

        return new ItemDetailsPage(driver);
    }
}
