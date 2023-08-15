import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RecoveryPasswordPage;
import pageobject.RegistrationPage;
import pens.PensUserAPI;
import serialization.UserSerialization;


public class TestLoginWebDriver extends BaseWebDriver {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registerPage;
    private PensUserAPI pensUserAPI;

    @Before
    public void setUp() {
        initializationWebDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegistrationPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");


        //создаем нового пользователя
        String name = "Andrey";
        email = "And@yandex.ru";
        password = "12345And";
        pensUserAPI = new PensUserAPI(email, password, name);
        UserSerialization.createUser(pensUserAPI);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void testLoginOnMainPage() {
        //клик на кнопку войти
        mainPage.clickLogin();
        //заполняем форму логина
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testLoginByPersonalButton() {
        //клик на кнопку личный кабинет
        mainPage.clickPersonal();
        //заполняем форму логина
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginOnRegistrationPage() {
        mainPage.clickLogin();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //кликаем на Зарегистроваться
        loginPage.clickRegister();
        //ждем загрузки страницы регистрации
        registerPage.waitForLoad();
        //клик на кнопку Войти
        registerPage.clickLogin();
        //заполняем форму логина
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginOnResetPasswordPage() {
        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);

        mainPage.clickLogin();
        loginPage.waitForLoad();
        loginPage.clickReset();
        //Кликаем на Войти
        recoveryPasswordPage.waitForLoad();
        recoveryPasswordPage.clickLogin();
        //ждем загрузки страницы регистрации
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }
}
