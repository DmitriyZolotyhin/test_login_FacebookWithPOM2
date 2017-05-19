package tests;

/**
 * Created by Администратор on 18.05.2017.
 */

import pageobjects.Login;
import org.junit.Test;
import org.junit.Before;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;


import pageobjects.Login;
import pageobjects.SeleniumTimers;


// Теперь мы добавили и запустили базовый класс, мы можем выполнять все связанные с глобальным тестированием функции.
// Это делает тестовый класс более читаемым, так как он сейчас
// содержит только  тестовые примеры
public class TestLogin extends Base{

    private Login login;
    private SeleniumTimers wait;

    @Before
    public void setUp() {
        login = new Login(driver);
    }

    @Test
    public void voidEnter() {
login.with("", "");
        assertTrue("Вход на Facebook", login.formForEnter());

    }

    @Test
    public void invalidPassword() {

        login.with("zolotoy18.12.1988@gmail.com", "123");
        assertTrue("Вы ввели неверный пароль", login.failureMessagePresent());

    }
    @Test
    public void successLogin() {


        login.with("zolotoy18.12.1988@gmail.com", "samara");
        assertTrue("", login.successLogin());
    }
}
