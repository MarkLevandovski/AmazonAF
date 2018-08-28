import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import po.MainPage;
import utils.BrowserFactory;

public class JustTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void before() {
        driver = BrowserFactory.getBrowser("Firefox");
        mainPage = new MainPage(driver);
    }

    @After
    public void after() {
        driver.quit();
    }

    @Test
    public void justTest() {
        mainPage.searchByName("Best sellers in Digital Cameras")
                .selectGivenItem(5)
                .selectQuantity(3)
                .addToCart()
                .verifyProductAddedToCart()
                .viewShoppingCart()
                .sumItems(3);
    }
}