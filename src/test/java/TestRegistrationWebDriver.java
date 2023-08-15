import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;


public class TestRegistrationWebDriver extends BaseWebDriver {
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registerPage;

    @Before
    public void setUp() {
        initializationWebDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegistrationPage(driver);

        //кликаем "Войти в аккаунт"
        mainPage.clickLogin();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //кликаем на Зарегистроваться
        loginPage.clickRegister();
        //ждем загрузки страницы регистрации
        registerPage.waitForLoad();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void testRegisterNewUser() {
        //заполняем форму регистрации
        String name = "Andrey";
        email = "And@yandex.ru";
        password = "12345And";
        registerPage.fillRegistrationForm(name, email, password);
        //клик на кнопку Зарегистрироваться
        registerPage.clickRegisterButton();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //заполняем форму логина
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //ждем загрузки главной страницы
        mainPage.waitForLoad();
        //проверяем что появилась кнопка заказа
        Assert.assertTrue("Регистрация не произошла", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного пароля. Минимальный пароль — шесть символов")
    public void testErrorWrongPassword() {
        //заполняем форму регистрации
        String name = "Andrey";
        email = "And@yandex.ru";
        password = "1234";
        registerPage.fillRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
        //проверяем что появилась надпись о некорректном пароле
        Assert.assertTrue("Ошибка о некорректном пароле не появилась",
                registerPage.isIncorrectPasswordLabelVisible());
    }
}