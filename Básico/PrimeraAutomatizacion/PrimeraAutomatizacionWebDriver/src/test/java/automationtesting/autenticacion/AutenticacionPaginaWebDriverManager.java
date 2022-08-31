package automationtesting.autenticacion;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static automationtesting.Contantes.*;
import static org.hamcrest.CoreMatchers.containsString;


public class AutenticacionPaginaWebDriverManager {
    private static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void autenticacionExitosa(){
        driver.manage().window().maximize();
        driver.get(URL_AUTOMATION_TESTING_LOGIN);
        driver.findElement(By.id(TXT_USERNAME_AT_ID)).sendKeys(USUARIO_LOGIN);
        driver.findElement(By.id(TXT_PASSWORD_AT_ID)).sendKeys(PASSWORD_LOGIN);
        driver.findElement(By.name(BTN_LOGIN_AT_NAME)).click();
        Assert.assertEquals(MENSAJE_LOGIN_EXITOSO, driver.findElement(By.xpath(LBL_SIGN_OUT_XPATH)).getText());
    }

    @Test
    public void autenticacionFallida(){
        driver.manage().window().maximize();
        driver.get(URL_AUTOMATION_TESTING_LOGIN);
        driver.findElement(By.id(TXT_USERNAME_AT_ID)).sendKeys(USUARIO_LOGIN);
        driver.findElement(By.id(TXT_PASSWORD_AT_ID)).sendKeys(PASSWORD_ERROR_LOGIN);
        driver.findElement(By.name(BTN_LOGIN_AT_NAME)).click();
        String texto_pantalla = driver.findElement(
                By.xpath("//*[@id='page-36']/div/div[1]/ul/li")).getText();
        Assert.assertTrue(MENSAJE_LOGIN_ERRONEO.contains(texto_pantalla));

    }

    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }
}
