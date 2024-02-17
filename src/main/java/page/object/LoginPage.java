package page.object;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static edu.practicum.Urls.*;

public class LoginPage {

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver){this.webDriver = webDriver;}

    //Форма для ввода данных и входа зарегистрированного пользователя
    private final By formToLogin = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']");

    //Кнопка Войти в форме регистрации и восстановления пароля
    private final By loginButtonInForms = By.xpath(".//a[text() = 'Войти']");
    //Текст Email на поле для ввода email
    private final By emailText = By.xpath(".//label[text() = 'Email']");
    //Поле для ввода email
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    //Текст Пароль на поле для ввода пароля
    private final By passwordText = By.xpath(".//label[text() = 'Пароль']");
    //Поле для ввода пароля
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    //Кнопка Войти в форме авторизации
    private final By loginButtonInFormAuth = By.xpath(".//button[text() = 'Войти']");
    //Главная страница после авторизации
    private final By homePageAfterAuth = By.xpath(".//main[@class = 'App_componentContainer__2JC2W']");
    //Заголовок Вход
    private final By enterText = By.xpath(".//h2[text() = 'Вход']");

    @Step("Открытие страницы авторизации")
    public LoginPage openLoginPage(){
        webDriver.get(URL_LOGIN_PAGE);
        return this;
    }

    @Step("Ожидание загрузки формы авторизации")
    public LoginPage waitingForLoading(){
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(formToLogin)));
        return this;
    }

    @Step("Клик по кнопке Войти в форме авторизации и восстановления пароля")
    public LoginPage clickOnLoginButtonInForms(){
        webDriver.findElement(loginButtonInForms).click();
        return this;
    }

    @Step("Открытие страницы восстановления пароля")
    public LoginPage openPasswordRestorePage(){
        webDriver.get(URL_PASSWORD_RESTORE);
        return this;
    }

    @Step("Заполнение поля Email")
    public LoginPage enterEmail(String email){
        webDriver.findElement(emailText).click();
        webDriver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Заполнение поля Пароль")
    public LoginPage enterPassword(String email){
        webDriver.findElement(passwordText).click();
        webDriver.findElement(passwordField).sendKeys(email);
        return this;
    }

    @Step("Клик по кнопке Войти в форме авторизации")
    public LoginPage clickOnButtonLoginInFormAuth(){
        webDriver.findElement(loginButtonInFormAuth).click();
        return this;
    }

    @Step("Проверка успешной авторизации")
    public LoginPage checkHomePageAfterAuth(){
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(homePageAfterAuth)));
        webDriver.findElement(homePageAfterAuth).isDisplayed();
        return this;
    }

    @Step("Проверка наличия текста Вход")
    public Boolean isDisplayedEnterText(){
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(enterText)));
        Boolean displayed = webDriver.findElement(enterText).isDisplayed();
        return displayed;
    }
}
