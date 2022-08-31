package automationtesting.ventanas;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static automationtesting.Contantes.URL_AUTOMATION_TESTING_VENTANAS;

public class VentanasTest {
    private static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void openNewTabWindows() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to(URL_AUTOMATION_TESTING_VENTANAS);
        driver.findElement(By.xpath("//*[@id='Tabbed']//button")).click();

        String ventaInicial = driver.getWindowHandle();

        for(String manejadorDeVentanas: driver.getWindowHandles()){
            driver.switchTo().window(manejadorDeVentanas);
            System.out.println("Titulo de la ventana: " + driver.getTitle());
            System.out.println("Identificador ventana: " + manejadorDeVentanas);
        }

        Assert.assertEquals("Selenium automates browsers. That's it!",
                driver.findElement(By.xpath("//*[@id='td-cover-block-0']/div/div/div/div/h1")).getText());
    }

    @Test
    public void NewSepareteWindow() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to(URL_AUTOMATION_TESTING_VENTANAS);

        driver.findElement(By.xpath("//a[@href='#Seperate']")).click();
        driver.findElement(By.xpath("//*[@id='Seperate']/button")).click();

        Assert.assertEquals("Automation Demo Site",
                driver.findElement(By.xpath("//*[@id='header']//h1")).getText());

        for(String manejadorDeVentanas: driver.getWindowHandles()){
            driver.switchTo().window(manejadorDeVentanas);
        }

        Assert.assertEquals("Selenium automates browsers. That's it!",
                driver.findElement(By.xpath("//*[@id='td-cover-block-0']/div/div/div/div/h1")).getText());

    }

    @Test
    public void OpenSeparateMulWindows() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to(URL_AUTOMATION_TESTING_VENTANAS);

        driver.findElement(By.xpath("//a[@href='#Multiple']")).click();
        driver.findElement(By.xpath("//*[@id='Multiple']/button")).click();

        int i = 1;
        for(String manejadorDeVentanas: driver.getWindowHandles()){
            driver.switchTo().window(manejadorDeVentanas);
            System.out.println("La URL de la ventana " + i +" es:" + driver.getCurrentUrl());
            i ++;
        }
    }


    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }
}
