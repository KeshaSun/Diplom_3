import com.github.javafaker.Faker;
import edu.driver.ChooseBrowser;
import edu.practicum.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.RegistrationPage;
import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

public class RegistrationTest {
    private WebDriver driver;
    private final Faker faker = new Faker();
    private final UserMethods userApiMethod = new UserMethods();
    private final User user = randomUser();


    @Before
    public void setUp(){
        driver = ChooseBrowser.chooseWebDriver();
        RestAssured.baseURI = STELLAR_BURGERS_HOME_PAGE_URL;
        userApiMethod.create(user);
    }

    @Test
    @DisplayName("Заполнение формы и регистрация с валидными данными")
    public void fillingOutTheRegistrationForm(){
        RegistrationPage registration = new RegistrationPage(driver);

        registration
                .openRegistrationPage()
                .enterName(user.getName())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .tapOnButtonRegistration();
        Assert.assertTrue("Успешная регистрация", registration.checkRegistrationSuccess());
    }

    @Test
    @DisplayName("Заполнение формы регистрации с некорректным паролем: пароль 5 символов")
    public void fillingRegistrationFormWithIncorrectPassword(){
        RegistrationPage registration = new RegistrationPage(driver);

        registration
                .openRegistrationPage()
                .enterName(user.getName())
                .enterEmail(user.getPassword())
                .enterPassword(faker.bothify("29???"))
                .tapOnButtonRegistration();

        Assert.assertTrue("Успешная регистрация", registration.checkRegistrationSuccess());
    }

    @After
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }

        Response response = userApiMethod.login(user);
        if(response.statusCode() == 200) {userApiMethod.delete(user);}

        clearBrowserCookies();
    }
}
