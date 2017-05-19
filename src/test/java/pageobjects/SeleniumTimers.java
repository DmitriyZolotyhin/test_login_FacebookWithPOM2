package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *  Created by Администратор on 18.05.2017.

 Цель этого класса - реализовать явные таймеры на локаторах селена.
 * Класс принимает драйвер, значение тайм-аута и локатор и возвращает логическое значение
 */
public class SeleniumTimers {
    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT_VALUE = 5;

    //Constructor sets the driver
    public SeleniumTimers(WebDriver driver) {
        this.driver = driver;
        System.out.println("Instantiating SeleniumTimers class");
    }

    public boolean waitForElementByLocator(By locator ,Integer timerValue){

        // Прежде всего проверьте, был ли параметр timerValue установлен в экземпляре класса.
        // Если нет, установите значение по умолчанию 5 секунд.
        System.out.println("Executing method waitForElementByLocator with timer value of:" + timerValue);
        timerValue = timerValue != null ? timerValue : DEFAULT_TIMEOUT_VALUE;

        WebDriverWait wait = new WebDriverWait(driver, timerValue);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception){
            System.out.println("about to return false because selenium.timeoutexception was thrown");
            return false;
        }
        System.out.println("about to return true");
        return true;

    }
}
