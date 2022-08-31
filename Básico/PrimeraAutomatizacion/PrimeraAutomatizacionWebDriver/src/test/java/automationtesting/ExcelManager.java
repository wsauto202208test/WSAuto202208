package automationtesting;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Clase para manejar un archivo de excel por medio de sentencias SQL como
 * SELECT, INSERT, UPDATE.
 *
 * @author david.c.gonzalez
 */
public class ExcelManager {

    private String strRutaArchivo;

    private Connection objConnection;
    private Fillo objFillo;
    private Recordset objRecordset;

    public ExcelManager() {
        strRutaArchivo = "";
    }

    /**
     * Metodo para cambiar la ruta del archivo de excel con el que se quiere
     * interactuar.
     *
     * @param strRutaArchivo:
     *            Define la ruta del archivo excel con el que se quiera manejar
     */
    public void strRutaArchivo(String strRutaArchivo) {
        this.strRutaArchivo = strRutaArchivo;
    }

    /**
     * Metodo que busca registros en una hoja de excel. De acuerdo a una sentencia
     * SELECT.
     *
     * @param strQuery
     *            Define la sentencia select que se quiere lanzar.
     *
     * @return
     */
    public Recordset leerExcel(String strQuery) {
        try {
            Fillo _objExcelMange = new Fillo();
            objConnection = _objExcelMange.getConnection(strRutaArchivo);
            objRecordset = objConnection.executeQuery(strQuery);
            objConnection.close();
            objConnection = null;
            return objRecordset;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("query " + strQuery + " No valido");
        }
    }

    /**
     * Metodo que escribe nuevos registros en una hoja de excel. De acuerdo a una
     * sentencia INSERT.
     *
     * @param strQuery
     *            Define la sentencia insert que se quiere lanzar.
     * @return
     */
    public boolean ExcribirExcel(String strQuery) {
        objFillo = new Fillo();
        boolean result = false;
        try {
            objConnection = objFillo.getConnection(strRutaArchivo);
            objConnection.executeUpdate(strQuery);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("query " + strQuery + " No valido");
        } finally {
            objConnection.close();
            objConnection = null;
        }
        return result;
    }

    /**
     * Metodo que modifica los registros en una hoja de excel. De acuerdo a una
     * sentencia UPDATE.
     *
     * @param strQuery:
     *            Define la sentencia update que se quiere lanzar.
     */
    public void ModificarRegistrosExcel(String strQuery) {
        objFillo = new Fillo();
        try {
            objConnection = objFillo.getConnection(strRutaArchivo);
            objConnection.executeUpdate(strQuery);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("query " + strQuery + " No valido");
        } finally {
            objConnection.close();
            objConnection = null;
        }

    }

    /**
     * Metodo que crea la copia de algun archivo.
     *
     * @param strRutaOrigen:
     *            Define la ruta del archivo que se queire copiar.
     * @param strRutaDestino:
     *            Define la ruta donde se quiere guardar el archivo.
     * @param strNombreArchivo:
     *            Define el nuevo nombre del archivo copiado.
     * @return String: Devuelve la ruta completa del archivo.
     */
    public String crearCopiaExcel(String strRutaOrigen, String strRutaDestino, String strNombreArchivo) {
        FileSystem objSistema = FileSystems.getDefault();
        Path pathArchivo = objSistema.getPath(strRutaOrigen);
        String strRutaArchivoCopia = strRutaDestino + strNombreArchivo;
        Path objRutaArchivoCopia = objSistema.getPath(strRutaArchivoCopia);
        try {
            File file = new File(strRutaArchivoCopia);
            file.delete();
            Files.copy(pathArchivo, objRutaArchivoCopia, StandardCopyOption.REPLACE_EXISTING);
            return strRutaArchivoCopia;
        } catch (IOException ex) {
            System.out.println("ERROR en: " + ex);
            return null;
        }

    }

}