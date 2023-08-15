package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    //Надпись Вход
    private By titleEnter = By.xpath("//h2[text()='Вход']");
    //Поле Email
    private By emailField = By.xpath("//input[@name='name']");
    //Поле Пароль
    private By passwordField = By.xpath("//input[@name='Пароль']");
    //Кнопка Войти
    private By loginButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx " +
            "button_button_size_medium__3zxIa']");
    //Кнопка Зарегистироваться
    private By registerButton = By.xpath("//a[@href='/register']");
    //Кнопка Восстановить пароль
    private By recoverPasswordButton = By.xpath("//a[@href='/forgot-password']");
    //Логотип
    private By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы авторизации")
    public void waitForLoad() {
        waitForVisibility(titleEnter);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    @Step("Заполнение формы авторизации")
    public void fillLoginForm(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик на логотип")
    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    @Step("Клик на 'Восстановить пароль'")
    public void clickReset() {
        driver.findElement(recoverPasswordButton).click();
    }

    @Step("Проверка видимости надписи 'Войти'")
    public boolean isEnterLabelVisible() {
        return driver.findElement(titleEnter).isDisplayed();
    }
}
