package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    //Надпись Регистрация
    private By registerTitle = By.xpath("//h2[text()='Регистрация']");
    //Поле Имя
    private By nameField = By.xpath("//label[text()='Имя']/../input");
    //Поле Email
    private By emailField = By.xpath("//label[text()='Email']/../input");
    //Поле Пароль
    private By passwordField = By.xpath("//label[text()='Пароль']/../input");
    //Кнопка Зарегистрироваться
    private By registerButton = By.xpath("//button[@class='button_button__33qZ0 " +
            "button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    //Кнопка Войти
    private By loginEnterButton = By.xpath("//a[@href='/login']");
    //Надпись Некорректный пароль
    private By incorrectPasswordTitle = By.xpath("//p[@class='input__error text_type_main-default']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы Регистрации")
    public void waitForLoad() {
        waitForVisibility(registerTitle);
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Проверка видимости надписи 'Неправильный пароль'")
    public boolean isIncorrectPasswordLabelVisible() {
        return driver.findElement(incorrectPasswordTitle).isDisplayed();
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLogin() {
        driver.findElement(loginEnterButton).click();
    }
}
