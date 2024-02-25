import edu.driver.ChooseBrowser;
import edu.practicum.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.HomePage;
import page.object.LoginPage;
import page.object.PersonalAccountPage;

import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

public class GoToAccountTest {

    private final User user = randomUser();
    private final UserMethods userApiMethod = new UserMethods();
    protected WebDriver driver;

    @Before
    public void setUp(){
        driver = ChooseBrowser.chooseWebDriver();
        RestAssured.baseURI = STELLAR_BURGERS_HOME_PAGE_URL;
        userApiMethod.create(user);

        LoginPage login = new LoginPage(driver);
        login
                .openLoginPage()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth();
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет»")
    public void checkGoToAccountByPersonalAccountButton(){
        PersonalAccountPage personalAccount = new PersonalAccountPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage
                .clickOnPersonalAccountButtonHp();
        Assert.assertTrue("Текст отображается", personalAccount.isDisplayedProfileText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
            userApiMethod.delete(user);
            clearBrowserCookies();
        }
}
