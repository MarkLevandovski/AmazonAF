package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SeleniumUtils;

public class MainPage {
    public final static Logger LOGGER = LoggerFactory.getLogger(MainPage.class);

    protected WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.name("site-search");

    public ResultsPage searchByName(String productName) {
        SeleniumUtils.defaultWait(driver).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                LOGGER.info("Waiting for Main Page....");
                return driver.findElement(searchBox) != null;
            }
        });

        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).submit();

        return new ResultsPage(driver);
    }
}
