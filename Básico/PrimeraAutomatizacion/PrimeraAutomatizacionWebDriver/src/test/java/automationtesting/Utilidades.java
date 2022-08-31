package automationtesting;

public class Utilidades {
    public static void esperar(long tiempo) throws InterruptedException {
        tiempo = tiempo * 1000;
        Thread.sleep(tiempo);
    }

}
