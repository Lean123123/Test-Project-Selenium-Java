package uk.co.automationtesting;


import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class AppTest extends BasePage
{
    private String userDir = System.getProperty("user.dir");

    public AppTest() throws IOException {
    }


    @Test
    public void shouldAnswerWithTrue() throws IOException {
        WebDriver driver = getDriver();
        driver.get(getUrl());

    }
}
