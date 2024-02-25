import edu.driver.ChooseBrowser;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.HomePage;

import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;


public class ConstructorTest {
    private WebDriver driver;


    @Before
    public void setUp(){
        driver = ChooseBrowser.chooseWebDriver();
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Переход к разделу конструктора Соусы")
    public void goToSaucesSection(){
        HomePage homePage = new HomePage(driver);

        homePage
                .clickOnSaucesButton();
        Assert.assertTrue("Предыдущие и следующие кнопки не выбраны", homePage.previousAndNextButtonsAreNotSelected());
    }

    @Test
    @DisplayName("Переход к разделу конструктора Начинки")
    public void goToFillingsSection(){
        HomePage homePage = new HomePage(driver);

        homePage
                .clickOnFillingsButton();
        Assert.assertTrue("Предыдущие кнопки не выбраны", homePage.previousTwoButtonsAreNotSelected());
    }

    @Test
    @DisplayName("Переход к разделу конструктора Булки")
    public void goToBunsSection(){
        HomePage homePage = new HomePage(driver);

        homePage
                .clickOnFillingsButton()
                .clickOnBunsButton();
        Assert.assertTrue("Следующие кнопки не выбраны", homePage.nextTwoButtonsAreNotSelected());
    }

    @After
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
        clearBrowserCookies();
    }
}
