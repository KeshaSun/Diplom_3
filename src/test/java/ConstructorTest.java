import io.qameta.allure.junit4.DisplayName;
import edu.practicum.BrowserRule;
import edu.practicum.ChromeRule;
import edu.practicum.YandexRule;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.HomePage;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class ConstructorTest {

    @Rule
    public BrowserRule rule;

    public ConstructorTest(BrowserRule rule) {
        this.rule = rule;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                { new YandexRule() },
                { new ChromeRule() }
        };
    }

    @Before
    public void setUp(){
        HomePage homePage = new HomePage(rule.getWebDriver());
        homePage
                .openHomePage();
    }

    @Test
    @DisplayName("Переход к разделу конструктора Соусы")
    public void goToSaucesSection(){
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnSaucesButton();
        Assert.assertTrue("Предыдущие и следующие кнопки не выбраны", homePage.previousAndNextButtonsAreNotSelected());
    }

    @Test
    @DisplayName("Переход к разделу конструктора Начинки")
    public void goToFillingsSection(){
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnFillingsButton();
        Assert.assertTrue("Предыдущие кнопки не выбраны", homePage.previousTwoButtonsAreNotSelected());
    }

    @Test
    @DisplayName("Переход к разделу конструктора Булки")
    public void goToBunsSection(){
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnFillingsButton()
                .clickOnBunsButton();
        Assert.assertTrue("Следующие кнопки не выбраны", homePage.nextTwoButtonsAreNotSelected());
    }

    @After
    public void tearDown(){
        clearBrowserCookies();
    }
}
