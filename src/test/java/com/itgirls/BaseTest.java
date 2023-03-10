package com.itgirls;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    AppiumDriverLocalService service;
    AndroidDriver driver;

    @BeforeTest
    public void setup(){
        service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel Emulator");
        options.setApp("/Users/itgirls1407/Documents/Software Engineering/Automation/Demo FW/appium-testing-framework/src/test/resources/ApiDemos-debug.apk");
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void teardown(){
        driver.quit();
        service.stop();
    }

}
