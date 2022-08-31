package automationtesting.tablas;

import automationtesting.Utilidades;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static automationtesting.Contantes.URL_WEB_TABLE;

public class TablasTest {
    private static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void validarCantidadFilasColumnas(){
        driver.manage().window().maximize();
        driver.get(URL_WEB_TABLE);

        List columnas = driver.findElements(By.xpath("//table/thead/tr/th"));
        System.out.println("La cantidad de columnas de la tabla es: " + columnas.size());

        List filas = driver.findElements(By.xpath("//table/tbody/tr"));
        System.out.println("La cantidad de filas de la tabla es: " + filas.size());
    }

    @Test
    public void consultarValorTabla() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(URL_WEB_TABLE);

        WebElement tabla = driver.findElement(By.xpath("//table[@class='dataTable']"));

        WebElement fila = tabla.findElement(By.xpath("//tbody/tr[2]"));

        WebElement especificarCelda = tabla.findElement(By.xpath("//tbody/tr[2]/td[1]"));

        System.out.println("Los datos de la fila 2 son:" + fila.getText());
        System.out.println("Los datos de la cela son:" + especificarCelda.getText());

        Utilidades.esperar(10);
    }

    @Test
    public void imprimirValoresTabla() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(URL_WEB_TABLE);

        WebElement tabla = driver.findElement(By.xpath("//table[@class='dataTable']"));

        List empresas = tabla.findElements(By.xpath("//tbody/tr/td[1]"));

        for (int i=1 ; i< empresas.size(); i++){
            System.out.println(tabla.findElement(By.xpath("//tbody/tr["+ i +"]/td[1]")).getText());
        }

    }


    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }
}
