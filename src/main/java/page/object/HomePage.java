package page.object;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static edu.practicum.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
public class HomePage {
    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //кнопка Войти в аккаунт на главной странице
    private final By buttonToEnterAccountHp = By.xpath(".//button[text() = 'Войти в аккаунт']");
    //Кнопка Войти в личный кабинет на главной странице
    private final By personalAccountButtonHp = By.xpath(".//p[text() = 'Личный Кабинет']");
    //Заголовок конструктора - "Соберите бургер"
    private final By constructorHeaderText = By.xpath(".//h1[text() = 'Соберите бургер']");
    //Логотип Stellar Burgers
    private final By logoStellarBurgers = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    //Кнопка Соусы в конструкторе
    private final By saucesButton = By.xpath(".//span[text() = 'Соусы']");
    //Кнопка Начинки в конструкторе
    private final By fillingsButton = By.xpath(".//span[text() = 'Начинки']");
    //Кнопка Булки в конструкторе
    private final By bunsButton = By.xpath(".//span[text() = 'Булки']");
    //Следующая кнопка1 конструктора не выбрана
    private final By followingButton1 = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/following-sibling::div[1]");
    //Следующая кнопка2 конструктора не выбрана
    private final By followingButton2 = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/following-sibling::div[2]");
    //Предыдущая кнопка1 конструктора не выбрана
    private final By precedingButton1 = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/preceding-sibling::div[1]");
    //Предыдущая кнопка2 конструктора не выбрана
    private final By precedingButton2 = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/preceding-sibling::div[1]");

    @Step("Открытие главной страницы")
    public HomePage openHomePage(){
        webDriver.get(STELLAR_BURGERS_HOME_PAGE_URL);
        return this;
    }

    @Step("Клик по кнопке Личный кабинет на главной странице")
    public HomePage clickOnPersonalAccountButtonHp(){
        webDriver.findElement(personalAccountButtonHp).click();
        return this;
    }

    @Step("Клик по кнопке Войти в аккаунт на главной странице")
    public HomePage clickOnButtonToEnterAccountHp(){
        webDriver.findElement(buttonToEnterAccountHp).click();
        return this;
    }

    @Step("Проверка наличия заголовка конструктора на главной странице")
    public HomePage checkConstructorHeaderText(){
        webDriver.findElement(constructorHeaderText).isDisplayed();
        return this;
    }

    @Step("Клик по логотипу Stellar Burgers")
    public HomePage clickOnLogoStellarBurgers(){
        webDriver.findElement(logoStellarBurgers).click();
        return this;
    }

    @Step("Клик по кнопке Соусы в конструкторе")
    public HomePage clickOnSaucesButton(){
        webDriver.findElement(saucesButton).click();
        return this;
    }

    @Step("Клик по кнопке Начинки в конструкторе")
    public HomePage clickOnFillingsButton(){
        webDriver.findElement(fillingsButton).click();
        return this;
    }

    @Step("Клик по кнопке Булки в конструкторе")
    public HomePage clickOnBunsButton(){
        webDriver.findElement(bunsButton).click();
        return this;
    }

    @Step("При выбранной кнопке Булки следующие две кнопки конструктора не выбраны")
    public HomePage nextTwoButtonsAreNotSelected(){
        webDriver.findElement(followingButton1);
        webDriver.findElement(followingButton2);
        return this;
    }

    @Step("При выбранной кнопке Соусы предыдущая и следующая кнопка конструктора не выбраны")
    public HomePage previousAndNextButtonsAreNotSelected(){
        webDriver.findElement(precedingButton1);
        webDriver.findElement(followingButton1);
        return this;
    }

    @Step("При выбранной кнопке Начинки две предыдущие кнопки конструктора не выбраны")
    public HomePage previousTwoButtonsAreNotSelected(){
        webDriver.findElement(precedingButton1);
        webDriver.findElement(precedingButton2);
        return this;
    }
}