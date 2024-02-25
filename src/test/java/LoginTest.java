import edu.driver.ChooseBrowser;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import edu.practicum.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.HomePage;
import page.object.LoginPage;
import page.object.RegistrationPage;
import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

public class LoginTest {
    private WebDriver driver;
    private final User user = randomUser();
    private final UserMethods userApiMethod = new UserMethods();


    @Before
    public void setUp(){
        driver = ChooseBrowser.chooseWebDriver();
        RestAssured.baseURI = STELLAR_BURGERS_HOME_PAGE_URL;
        userApiMethod.create(user);
    }

    @Test
    @DisplayName("Вход в аккаунт по кнопке «Войти в аккаунт» на главной")
    public void inputByButtonToEnterAccountHp(){
        LoginPage login = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage
                .openHomePage()
                .clickOnButtonToEnterAccountHp();
        login
                .waitingForLoading()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth();

        Boolean homePageAssert = LoginPage.checkHomePageAfterAuth();
        Assert.assertTrue("Текст отображается", homePageAssert);
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку «Личный кабинет» на главной")
    public void inputByPersonalAccountButtonHp(){
        LoginPage login = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage
                .openHomePage()
                .clickOnPersonalAccountButtonHp();
        login
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth();
        Boolean homePageAssert = LoginPage.checkHomePageAfterAuth();
        Assert.assertTrue("Текст отображается", homePageAssert);
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    public void inputByLoginButtonInFormRegistration(){
        RegistrationPage registration = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);

        registration
                .openRegistrationPage();
        login
                .clickOnLoginButtonInForms()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword());
        Boolean homePageAssert = LoginPage.checkHomePageAfterAuth();
        Assert.assertTrue("Текст отображается", homePageAssert);
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    public void inputByLoginButtonInFormRestorePassword(){
        LoginPage login = new LoginPage(driver);

        login
                .openPasswordRestorePage()
                .clickOnLoginButtonInForms()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth();
        Boolean homePageAssert = LoginPage.checkHomePageAfterAuth();
        Assert.assertTrue("Домашняя ста", homePageAssert);
    }

    @After
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
        userApiMethod.delete(user);
        clearBrowserCookies();
    }
}
