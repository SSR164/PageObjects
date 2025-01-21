package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class TestBase {
    RegistrationPage registrationPage;
    static WebDriverConfig config;

    @BeforeAll
    static void beforeAll() {
        String env = System.getProperty("env", "local");
        System.out.println("Environment: " + env);

        // Загрузка файла из classpath
        try (InputStream input = TestBase.class.getClassLoader().getResourceAsStream(env + ".properties")) {
            if (input == null) {
                throw new RuntimeException("Could not find properties file: " + env + ".properties");
            }

            Properties props = new Properties();
            props.load(input);
            System.getProperties().putAll(props);
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file: " + env + ".properties", e);
        }

        config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());


        config = ConfigFactory.create(WebDriverConfig.class);
        System.out.println(System.getProperties());
        System.out.println("Is Remote: " + config.getIsRemote());
        System.out.println("Browser: " + config.getBrowser());
        System.out.println("Browser Version: " + config.getBrowserVersion());
        System.out.println("Remote URL: " + config.getRemoteUrl());
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.pageLoadStrategy = config.getLoadStrategy();
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getbrowserSize();

        if (config.getIsRemote()){
            Configuration.remote = config.getRemoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;

            // Логирование для Allure
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        }
        }



    @BeforeEach
    public void preparation() {
        registrationPage = new RegistrationPage();
        registrationPage.openPage().removeBanners();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
