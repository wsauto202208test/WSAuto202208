package automationtesting.aperturapagina;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbrirPaginaTest {
    public static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void abrirPaginaTest(){
        driver.manage().window().maximize();
        driver.get("https://automationtesting.in/");
    }

    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }
}
