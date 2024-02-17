package page.object;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import static edu.practicum.Urls.URL_REGISTRATION;

public class RegistrationPage {

    private WebDriver webDriver;

    public RegistrationPage(WebDriver webDriver){ this.webDriver = webDriver; }

    //Текст Имя на поле для ввода имени
    private final By nameText = By.xpath(".//label[text() = 'Имя']");
    //Поле ввода имени
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    //Текст Email на поле для ввода email
    private final By emailText = By.xpath(".//label[text() = 'Email']");
    //Поле для ввода email
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    //Текст Пароль на поле для ввода пароля
    private final By passwordText = By.xpath(".//label[text() = 'Пароль']");
    //Поле для ввода пароля
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    //Текст Некорректный пароль
    private final By incorrectPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");
    //Форма для ввода данных и входа зарегистрированного пользователя
    private final By formToLogin = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']");
    //Кнопка зарегистрироваться
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");

    @Step("Открытие страницы регистрации")
    public RegistrationPage openRegistrationPage(){
        webDriver.get(URL_REGISTRATION);
        return this;
    }

    @Step("Заполнение поля Имя")
    public RegistrationPage enterName(String name){
        webDriver.findElement(nameText).click();
        webDriver.findElement(nameField).sendKeys(name);
        return this;
    }

    @Step("Заполнение поля Email")
    public RegistrationPage enterEmail(String email){
        webDriver.findElement(emailText).click();
        webDriver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Заполнение поля Пароль")
    public RegistrationPage enterPassword(String email){
        webDriver.findElement(passwordText).click();
        webDriver.findElement(passwordField).sendKeys(email);
        return this;
    }

    @Step("Проверка неправильного ввода пароля")
    public RegistrationPage checkIncorrectPassword(){
        webDriver.findElement(incorrectPasswordText);
        return this;
    }

    @Step("Проверка успешной регистрации")
    public boolean checkRegistrationSuccess(){
        return webDriver.findElement(formToLogin).isDisplayed();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public RegistrationPage tapOnButtonRegistration(){
        webDriver.findElement(registrationButton).click();
        return this;
    }

}
