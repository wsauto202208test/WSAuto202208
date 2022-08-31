package automationtesting.aperturapagina;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbrirPagina {

    public static void main(String[] args) throws InterruptedException {
        // Paso 1. Configurar el driver google Chrome
        System.setProperty("webdriver.chrome.driver","D:\\WS 202208\\Básico\\PrimeraAutomatizacion\\PrimeraAutomatizacionWebDriver\\src\\test\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Paso 2. Maximizar la Pagina
        driver.manage().window().maximize();

        //Paso 3. Abrir la URL
        driver.get("https://automationtesting.in/");

        //Paso 4. Clic en un link
        driver.findElement(By.linkText("Demo Site")).click();

        //Paso 5. Iframe
        //driver.switchTo().defaultContent();
        driver.switchTo().frame("aswift_4");
        driver.switchTo().frame("ad_iframe");
        driver.findElement(By.id("dismiss-button")).click();


        //Paso MALA PRACTICA - Espera
        Thread.sleep(5000);

        //Paso Final. Cerrar diver
        driver.close();
        driver.quit();
    }
}
