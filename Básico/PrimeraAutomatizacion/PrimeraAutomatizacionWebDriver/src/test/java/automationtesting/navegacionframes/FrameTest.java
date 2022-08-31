package automationtesting.navegacionframes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static automationtesting.Contantes.PRIMER_FRAME;
import static automationtesting.Contantes.URL_AUTOMATION_TESTING_FRAMES;
import static automationtesting.Utilidades.*;

public class FrameTest {
    private static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void ingresarAUnFrame() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to(URL_AUTOMATION_TESTING_FRAMES);
        driver.switchTo().frame(PRIMER_FRAME);
        esperar(2);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Frame 1");
        esperar(2);
    }

    @Test
    public void IframewithinanIframe() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to(URL_AUTOMATION_TESTING_FRAMES);
        driver.findElement(By.xpath("//a[@href='#Multiple']")).click();
        esperar(2);
        WebElement frame1 = driver.findElement(By.xpath("//*[@id='Multiple']/iframe"));
        driver.switchTo().frame(frame1);
        WebElement frame2 = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
        driver.switchTo().frame(frame2);
        esperar(2);
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Frame 2");
        esperar(2);
        driver.switchTo().defaultContent();
        Assert.assertEquals("Automation Demo Site",driver.findElement(By.xpath("//*[@id='header']//h1")).getText());
        esperar(2);
    }

    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }

}
