package automationtesting.aperturapagina;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbrirPaginaTestFirefox {
    public static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void abrirPaginaTest(){
        driver.get("https://automationtesting.in/");
    }

    @After
    public void finalizar(){
        driver.close();
    }
}
