import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalPage;
import pageobject.RegistrationPage;
import pens.PensUserAPI;
import serialization.UserSerialization;


public class TestPersonalWebDriver extends BaseWebDriver {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registerPage;
    private PersonalPage personalPage;
    private PensUserAPI pensUserAPI;

    @Before
    public void setUp() {
        initializationWebDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegistrationPage(driver);
        personalPage = new PersonalPage(driver);

        //создаем нового пользователя
        String name = "Andrey";
        email = "And@yandex.ru";
        password = "12345And";
        pensUserAPI = new PensUserAPI(email, password, name);
        UserSerialization.createUser(pensUserAPI);

        //кликаем "Войти в аккаунт"
        mainPage.clickLogin();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //заполняем форму логина
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет».")
    public void testEnterPersonalAccount() {
        //клик на личный кабинет
        mainPage.clickPersonal();
        //проверяем видимость кнопки профиль
        personalPage.waitForLoad();
        Assert.assertTrue("Вход в личный кабинет не выполнен", personalPage.isProfileButtonVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void testEnterConstructorByConstructorButton() {
        //клик на личный кабинет
        mainPage.clickPersonal();
        personalPage.waitForLoad();
        //кликаем на Конструктор
        personalPage.clickConstructor();
        //Проверяем видимость кнопки оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Переход в конструктор не произошел", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void testEnterConstructorByLogo() {
        //клик на личный кабинет
        mainPage.clickPersonal();
        personalPage.waitForLoad();
        //кликаем на лого
        personalPage.clickLogo();
        //Проверяем видимость кнопки оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Переход в конструктор не произошел", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка выхода по кнопке Выйти")
    public void testLogoutByButtonLogout() {
        //клик на личный кабинет
        mainPage.clickPersonal();
        personalPage.waitForLoad();
        //клик на Выйти
        personalPage.clickLogout();
        //проверяем что перешли на страницу Логина
        loginPage.waitForLoad();
        Assert.assertTrue("Выход не произошел", loginPage.isEnterLabelVisible());
    }
}
