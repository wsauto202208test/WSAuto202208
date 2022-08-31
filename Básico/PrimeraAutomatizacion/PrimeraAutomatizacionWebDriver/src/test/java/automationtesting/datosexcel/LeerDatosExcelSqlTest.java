package automationtesting.datosexcel;

import automationtesting.ExcelManager;
import automationtesting.Utilidades;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static automationtesting.Contantes.*;

public class LeerDatosExcelSqlTest {
    private static WebDriver driver;
    private ExcelManager excelManager;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        excelManager = new ExcelManager();
    }

    @Test
    public void LeerDatosDesdeExcel() throws FilloException, InterruptedException {
        driver.manage().window().maximize();
        excelManager.strRutaArchivo("src/test/resources/datadriven/datos.xlsx"); //Conexión al excel
        String strQsl = "SELECT * FROM Hoja1"; //Consulta para los datos a leer en la hoja
        Recordset objRecord = excelManager.leerExcel(strQsl);
        System.out.println("La cantidad de registro en el archivo son: "+ objRecord.getCount());

        while (objRecord.next()) {
            driver.navigate().to(URL_KATALON);
            driver.findElement(By.id(TXT_FIRST_NAME_ID)).sendKeys(objRecord.getField("firstname"));
            driver.findElement(By.id(TXT_LAST_NAME_ID)).sendKeys(objRecord.getField("lastname"));
            driver.findElement(By.id(TXT_DB_ID)).sendKeys(objRecord.getField("dateb"));
            driver.findElement(By.id(TXT_ADDRESS_ID)).click();
            driver.findElement(By.id(TXT_ADDRESS_ID)).sendKeys(objRecord.getField("address"));
            Utilidades.esperar(2);
        }
    }

    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }
}
