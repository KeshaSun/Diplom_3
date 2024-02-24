import com.github.javafaker.Faker;
import edu.practicum.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.object.RegistrationPage;
import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static edu.practicum.UserGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class RegistrationTest {

    Faker faker = new Faker();
    UserMethods userApiMethod = new UserMethods();
    User user = randomUser();

    @Rule
    public BrowserRule rule;

    public RegistrationTest(BrowserRule rule) {
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
    }

    @Test
    @DisplayName("Заполнение формы и регистрация с валидными данными")
    public void fillingOutTheRegistrationForm(){
        RegistrationPage registration = new RegistrationPage(rule.getWebDriver());

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
        RegistrationPage registration = new RegistrationPage(rule.getWebDriver());

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

        Response response = userApiMethod.login(user);
        if(response.statusCode() == 200) {userApiMethod.delete(user);}

        clearBrowserCookies();
    }
}
