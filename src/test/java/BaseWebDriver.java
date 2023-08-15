import Constants.Constant;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pens.PensUserAPI;
import serialization.UserSerialization;


public class BaseWebDriver {
    WebDriver driver;
    String email;
    String password;

    public void initializationWebDriver() {
/*
        //Для запуска тестов на Yandex
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Andre\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.get(Constant.BURGER_URL);
*/
        //Для запуска тестов на Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(Constant.BURGER_URL);

    }

    @After
    public void tearDown() {
        String accessToken = UserSerialization.loginUser(new PensUserAPI(email, password)).then().extract().path("accessToken");
        if (accessToken != null) {
            UserSerialization.deleteUser(accessToken);
        }
        driver.quit();
    }
}
