package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By firstLoginButton =
            By.xpath("//div[@class='app']//button[@class='nav-btn-outline']");

    By emailField =
            By.xpath("//div[@class='input-group']//label[contains(text(),'Email Address')]/following-sibling::input");

    By passwordField =
            By.xpath("//div[@class='input-group']//label[text()='Password']/following-sibling::*/input");

    By loginButton =
            By.xpath("//button[@class='login-button']");

    By forgetPassword =
            By.xpath("//div[@class='login-secondary-actions']//button[@type='button']");

    By loginOverlay =
            By.xpath("//div[contains(@class,'login-modal-overlay')]");

    // Login actions

    public void clickFirstLogin() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(firstLoginButton)
        );

        driver.findElement(firstLoginButton).click();
    }

    public void enterEmail(String email) {

        driver.findElement(emailField).clear();

        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {

        driver.findElement(passwordField).clear();

        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {

        driver.findElement(loginButton).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        loginOverlay
                )
        );
    }

    public void clickForgetPassword() {

        driver.findElement(forgetPassword).click();
    }

    // Reusable Login Method

    public void login(String email, String password) {

        clickFirstLogin();

        enterEmail(email);

        enterPassword(password);

        clickLogin();
    }
}