package tests;

/**
 *  Created by Администратор on 18.05.2017.
 * This code is copied from Dave Haeffners boot camp day 5 example to set up SauceLabs integration.
 */
public class Config {
    public static final String host           = System.getProperty("host", "localhost");
    public static final String browser        = System.getProperty("browser", "internetexplorer");
    //Выбрать на каком браузере запускать тест: firefox   htmlunit  internetexplorer chrome
    public static final String platform       = System.getProperty("platform", "Windows 7");
    public static final String sauceUser      = System.getenv("SAUCE_USERNAME");
    public static final String sauceKey       = System.getenv("SAUCE_ACCESS_KEY");
}
