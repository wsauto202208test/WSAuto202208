package automationtesting.datosexcel;

import automationtesting.Utilidades;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;

import static automationtesting.Contantes.TXT_FIRST_NAME_ID;
import static automationtesting.Contantes.URL_KATALON;

public class LeerDatosExcel {
    private static WebDriver driver;

    @Before
    public void Configuracion(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void leerDatosExcel() throws IOException, InterruptedException {
        String rutaArchivo = "src/test/resources/datadriven/datos.xlsx";
        FileInputStream archivo = new FileInputStream(rutaArchivo);
        Workbook libro = new XSSFWorkbook(archivo); //Tomar el libreo del archivo
        Sheet hoja = libro.getSheetAt(0);
        Row fila = hoja.getRow(1);//Parte desde la fila 2 en el archivo de excel ya que la fila 1 son los encabezados
        int rowCount = hoja.getLastRowNum() + hoja.getFirstRowNum();

        driver.manage().window().maximize();

        for (int i = 0; i < rowCount; i++){
            fila = hoja.getRow(i+1);
            driver.navigate().to(URL_KATALON);
            driver.findElement(By.id(TXT_FIRST_NAME_ID)).sendKeys("" + fila.getCell(0));
            Utilidades.esperar(2);
        }

        archivo.close(); //Seimpre al final se debe cerra el archivo
    }
    


    @After
    public void finalizar(){
        driver.close();
        driver.quit();
    }
}
