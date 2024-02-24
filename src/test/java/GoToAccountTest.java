import edu.practicum.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.HomePage;
import page.object.LoginPage;
import page.object.PersonalAccountPage;
import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class GoToAccountTest {

    User user = randomUser();
    UserMethods userApiMethod = new UserMethods();

    @Rule
    public BrowserRule rule;

    public GoToAccountTest(BrowserRule rule) {
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
        RestAssured.baseURI = STELLAR_BURGERS_HOME_PAGE_URL;
        userApiMethod.create(user);

        LoginPage login = new LoginPage(rule.getWebDriver());
        login
                .openLoginPage()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth();
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void checkGoToAccountByPersonalAccountButton(){
        PersonalAccountPage personalAccount = new PersonalAccountPage(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnPersonalAccountButtonHp();
        Assert.assertTrue("Текст отображается", personalAccount.isDisplayedProfileText());
    }

    @After
    public void tearDown(){
        userApiMethod.delete(user);
        clearBrowserCookies();
    }
}
