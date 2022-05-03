package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BasePage {

    private String url;
    private Properties prop;
    private String userDir = System.getProperty("user.dir");
    public static String screenShotDestinationPath;


    public BasePage() throws IOException {
        prop = new Properties();
        FileInputStream data = new FileInputStream(userDir + "/src/main/java/resources/config.properties");
        prop.load(data);
    }

    public  static WebDriver getDriver(){
        return WebDriverInstance.getDriver();
    }

    public String getUrl() throws IOException {
        url = prop.getProperty("url");
        return url;
    }

    public static String takeSnapShot(String name) throws IOException {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir");
        String resPath = "/target/screenshots/";
        String desFile = projectPath + resPath + timeStamp()  + ".png";
        screenShotDestinationPath = desFile;

        try {
            FileUtils.copyFile(srcFile,new File(desFile));
        } catch (IOException e){
            e.printStackTrace();
        }

        return name;
    }

    public static String timeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public static String getScreenShotDestinationPath(){
        return screenShotDestinationPath;
    }

    public static void waitForElementInvisible(WebElement element, Duration timer){
        WebDriverWait wait = new WebDriverWait(getDriver(),timer);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
