package pageobjects;
/**
 * Created by Администратор on 18.05.2017.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import pageobjects.SeleniumTimers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Login {

    private WebDriver driver;

    By loginFormLocator = By.id("email");
    By passwordLocator  = By.id("pass");
    By submitButton     = By.xpath(".//*[@id='loginbutton']");
    By successLocator = By.xpath(".//*[@id='navItem_100016796564791']/a/div");
    By failureMessageLocator = By.xpath(".//*[@id='globalContainer']/div[3]/div/div/div");
    By formForEnter           = By.xpath(".//*[@id='content']/div/div/div[1]/span");
    SeleniumTimers wait;
    //Constructor
    public Login(WebDriver driver) {
        this.driver = driver;
        wait = new SeleniumTimers(driver);
        driver.get("http://www.facebook.com");
        try {
            Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void with(String email, String password) {


        driver.findElement(loginFormLocator).clear();
    driver.findElement(loginFormLocator).sendKeys(email);//usernameLocator
    driver.findElement(passwordLocator).sendKeys(password);
    driver.findElement(submitButton).click();

    }

    public Boolean successLogin() {


       // Обратите внимание, что путем рефакторинга ожидания (сверху)
        // в его собственный класс
        // мы упростим тестовый код далее
        // Объект wait должен быть создан в конструкторе, чтобы методы
        // выставлен для использования в остальной части этого pageObjec
        return wait.waitForElementByLocator(successLocator,3);
    }

    public Boolean failureMessagePresent() {
        WebElement nextPage = (new WebDriverWait(driver,5)).until(ExpectedConditions.presenceOfElementLocated(failureMessageLocator));
        return driver.findElement(failureMessageLocator).isDisplayed();
    }
    public Boolean formForEnter() {
        WebElement nextPage2 = (new WebDriverWait(driver,5)).until(ExpectedConditions.presenceOfElementLocated(formForEnter));
        return driver.findElement(formForEnter).isDisplayed();
    }

 //   public void Clear() {
   //     email.Clear();

   // }
}