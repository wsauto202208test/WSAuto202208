package automationtesting.diligenciarformulario;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static automationtesting.Contantes.*;
import static automationtesting.Utilidades.esperar;

public class FormularioKatalon {
    private static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void diligenciarFormularioKatalon() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(URL_KATALON);
        esperar(2);
        driver.findElement(By.id(TXT_FIRST_NAME_ID)).sendKeys("Yeison");
        driver.findElement(By.id(TXT_LAST_NAME_ID)).sendKeys("Arias");
        driver.findElement(By.xpath(RDB_MALE_XPATH)).click();
        driver.findElement(By.id(TXT_DB_ID)).sendKeys("02/05/1981");
        driver.findElement(By.id(TXT_ADDRESS_ID)).sendKeys("CALLE 2 # 3-1");
        driver.findElement(By.id(TXT_EMAIL_ID)).sendKeys("correo@correo.com");
        driver.findElement(By.id(TXT_PASSWORD_ID)).sendKeys("PassWord123+");
        driver.findElement(By.id(TXT_COMPANY_ID)).sendKeys("Solutions Automation");
        Select Role = new Select(driver.findElement(By.id(CMB_ROL_ID)));
        Role.selectByVisibleText("Manager");
        Select expectation = new Select(driver.findElement(By.id(LST_JE_ID)));
        expectation.selectByVisibleText("High salary");
        expectation.selectByIndex(4);
        driver.findElement(By.cssSelector(CHK_TOC_CSS)).click();
        driver.findElement(By.id(TXT_COMMENT_ID)).sendKeys("Comentario");
        driver.findElement(By.id(BTN_SUMMIT_ID)).click();
        Assert.assertEquals(TEXTO_MENSAJE_EXITOSO,driver.findElement(By.id(TXT_MENSAJE_EXITOSO_ID)).getText());
        esperar(5);
    }

    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }

}
