package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage extends BasePage {

    //Надпись Восстановление пароля
    private By TitleRecoveryPassword = By.xpath("//h2[text()='Восстановление пароля']");
    //Кнопка Войти
    private By buttonEnter = By.xpath("//a[@href='/login']");

    public RecoveryPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на 'Войти'")
    public void clickLogin() {
        driver.findElement(buttonEnter).click();
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForLoad() {
        waitForVisibility(TitleRecoveryPassword);
    }
}