package automationtesting.alertas;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static automationtesting.Contantes.MENSAJE_EN_ALERTA;
import static automationtesting.Contantes.URL_AUTOMATION_TESTING_ALERTS;
import static automationtesting.Utilidades.esperar;

public class AlertasTest {
    public static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void gestionarAlertWithOk() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(URL_AUTOMATION_TESTING_ALERTS);
        driver.findElement(By.xpath("//*[@id='OKTab']/button")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        esperar(3);
    }

    @Test
    public void gestionarAlertWithOkCancel() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(URL_AUTOMATION_TESTING_ALERTS);
        //Paso 2. dar clic en la opción de "Alert with OK & Cancel"
        driver.findElement(By.xpath("//a[@href='#CancelTab']")).click();
        //Paso 3. Dar clic en el botón de las alertas
        driver.findElement(By.xpath("//*[@id='CancelTab']/button")).click();
        esperar(2);
        //Paso 4. Dar clic en el boton ACEPTAR de la alerta
        Alert alert = driver.switchTo().alert();
        alert.accept();
        //Paso 5. Verificar el mensaje "You pressed Ok"
        Assert.assertEquals("You pressed Ok",driver.findElement(By.id("demo")).getText());
        esperar(2);
        //Paso 6. Dar clic en el boton CANCELAR de la alerta
        driver.findElement(By.xpath("//*[@id='CancelTab']/button")).click();
        esperar(2);
        alert.dismiss();
        //Paso 5. Verificar el mensaje "You Pressed Cancel"
        Assert.assertEquals("You Pressed Cancel",driver.findElement(By.id("demo")).getText());
        esperar(3);
    }

    @Test
    public void gestionarAlertWithTextBox() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(URL_AUTOMATION_TESTING_ALERTS);
        //Paso 2. dar clic en la opción de "Alert with OK & Cancel"
        driver.findElement(By.xpath("//a[@href='#Textbox']")).click();
        //Paso 3. Dar clic en el botón de las alertas
        driver.findElement(By.xpath("//*[@id='Textbox']/button")).click();
        esperar(2);
        //Paso 4. Escribir en el Textbox de la alerta y Dar clic en el boton ACEPTAR de la alerta
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(MENSAJE_EN_ALERTA);
        alert.accept();
        //Paso 5. Verificar el mensaje "Hello Automation Testing user How are you today"
        Assert.assertEquals("Hello " + MENSAJE_EN_ALERTA + " How are you today",
                driver.findElement(By.id("demo1")).getText());
        esperar(3);
    }



    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }
}
