package tests;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import tests.Config;

import java.io.IOException;
import java.net.URL;


import static tests.Config.*;
/**
 * Created by Администратор on 18.05.2017.
 Это базовый тестовый класс. Цель создания класса состоит в том, чтобы извлечь из класса теста
 запуск определенного браузера.

 *
 */
public class Base {

    protected WebDriver driver;

    @Rule
    public ExternalResource resource = new ExternalResource() {


        @Override

        protected void before() throws Throwable {
            //Проверка соеденения ,установленно или нет,если не установленно то тесты не запускаются
            TestInternet.testInet();
            if ( //Если запуск тестов в облаках (Sauce Labs)
                    host.equals("saucelabs")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browser);
                capabilities.setCapability("platform", platform);
                String sauceUrl = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub",
                       sauceUser, sauceKey);
               driver = new RemoteWebDriver(new URL(sauceUrl), capabilities);
            } else if  //Иначе на локальном ПК
                    (host.equals("localhost")) {
                switch (browser.toLowerCase()){
                    case "firefox":
                        // Обратите внимание, что использование драйвера gecko требуется при использовании версий Firefox позже 47.
                        // драйвер не находится в настоящее время в пакете maven, поэтому его необходимо загрузить вручную из
                        // https://github.com/mozilla/geckodriver/releases и положить в указанную папку
                       System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
                       driver = new FirefoxDriver();
                        break;
                    case "ie":
                    case "internetexplorer":



                        Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
                        System.setProperty("webdriver.ie.driver", "C:\\Driver\\IEDriverServer.exe" );


                        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

                        capabilities.setCapability("requireWindowFocus", true);
                        driver = new InternetExplorerDriver(capabilities);


                        break;
                    case "chrome":
                        //

                        System.setProperty("webdriver.chrome.driver", "C:\\Selenium_driver\\chromedriver.exe");
                        driver = new ChromeDriver();


                    case "headless":
                    case "htmlunit":
                    case "html-unit":

                       // Это самый быстрый тестовый драйвер,без визуального воспроизведения действий пользователя

                        driver = new HtmlUnitDriver();
                        break;
                    default:
                        System.out.println("unrecognised browser type configured:" + browser.toLowerCase());
                        break;
                }
                System.out.println("***********Launching test with configured browser set to: " + browser);
            }
        }

        @Override
        protected void after() {
            driver.quit();
        }
    };
}
