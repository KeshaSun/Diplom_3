import edu.driver.ChooseBrowser;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import edu.practicum.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.HomePage;
import page.object.LoginPage;
import page.object.PersonalAccountPage;

import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

public class TransitionFromAccountTest {
    private WebDriver driver;

    private final User user = randomUser();
    private final UserMethods userMethod = new UserMethods();


    @Before
    public void setUp(){
        driver = ChooseBrowser.chooseWebDriver();
        RestAssured.baseURI = STELLAR_BURGERS_HOME_PAGE_URL;
        userMethod.create(user);

        LoginPage login = new LoginPage(driver);
        login
                .openLoginPage()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор - по клику на «Конструктор»")
    public void transitionFromAccountByConstructorButton(){
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage
                .clickOnPersonalAccountButtonHp();
        personalAccount
                .clickOnConstructorButton();
        Assert.assertTrue("Проверка текста заголовка", homePage.checkConstructorHeaderText());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор - по клику на логотип Stellar Burgers")
    public void transitionFromAccountByLogo(){
        HomePage homePage = new HomePage(driver);

        homePage
                .clickOnPersonalAccountButtonHp()
                .clickOnLogoStellarBurgers();

        Assert.assertTrue("Проверка текста заголовка", homePage.checkConstructorHeaderText());
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void transitionFromAccountByExitButton()  {
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage
                .clickOnPersonalAccountButtonHp();
        personalAccount
                .clickOnExitButton();

        Boolean enteredTextDisplayed = LoginPage.isDisplayedEnterText();
        Assert.assertTrue("Отображается введённый текст", enteredTextDisplayed);
    }

    @After
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
        userMethod.delete(user);
        clearBrowserCookies();
    }
}