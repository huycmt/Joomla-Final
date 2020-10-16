package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static helpers.DriverHelper.getDriver;

public class LoginPage extends BasePage {
    //Locators
    private By _usernameTb = By.cssSelector("input[name='username']");
    private By _passwordTb = By.cssSelector("input[name='passwd']");
    private By _loginBtn = By.cssSelector("button");

    //Elements
    private WebElement username() {
        return getDriver().findElement(_usernameTb);
    }

    private WebElement password() {
        return getDriver().findElement(_passwordTb);
    }

    private WebElement loginBtn() {
        return getDriver().findElement(_loginBtn);
    }

    //Method
    public void login(String username, String password) {
        enterData(username(), username);
        enterData(password(), password);
        clickElement(loginBtn());
    }
}
