package ru.geekbrains;

import org.junit.Test;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestHW {
    private String PathToChromeDriver = "src\\resources\\chromedriver.exe";
    private ChromeDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", PathToChromeDriver);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testGeekbrainsRuCareer() throws Exception {
        driver.get("https://geekbrains.ru/login");//Let as execute test without authentication
        driver.findElement(By.cssSelector("svg.svg-icon.icon-career")).click();
        assertEquals("Карьера | GeekBrains - образовательный портал", driver.getTitle());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Разработчик C# в игровой проект'])[1]/following::span[2]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Тестовое задание'])[9]/following::span[1]")).click();
    }

    @Test
    public void testMailRuAuthentication() throws Exception {
        driver.get("https://mail.ru/");
        driver.findElement(By.id("mailbox:login")).click();
        driver.findElement(By.id("mailbox:login")).clear();
        driver.findElement(By.id("mailbox:login")).sendKeys("geektest05");
        driver.findElement(By.id("mailbox:password")).clear();
        driver.findElement(By.id("mailbox:password")).sendKeys("Sample05");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Войти'])[1]/input[1]")).click();
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}