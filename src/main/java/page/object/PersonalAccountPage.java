package page.object;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalAccountPage {

    WebDriver webDriver;

    public PersonalAccountPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    //Заголовок Профиль в личном кабинете
    private final By profileText = By.xpath(".//a[text() = 'Профиль']");
    //Кнопка Конструктор в личном кабинете
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    //Кнопка Выйти из личного кабинета
    private final By exitButton = By.xpath(".//button[text() = 'Выход']");

    @Step("Проверка наличия текста Профиль в личном кабинете")
    public boolean isDisplayedProfileText(){
        WebElement textInProfile = webDriver.findElement(profileText);
        return textInProfile.isDisplayed();
    }

    @Step("Клик по кнопке Конструктор")
    public PersonalAccountPage clickOnConstructorButton(){
        webDriver.findElement(constructorButton).click();
        return this;
    }

    @Step("Клик по кнопке Выйти")
    public PersonalAccountPage clickOnExitButton(){
        webDriver.findElement(exitButton).click();
        return this;
    }
}