/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramienta_postgres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class Recovery {
    
    private final  String host = "localhost";
    private final  String puerto = "5432";
    private final  String usuario = "postgres";
    private final  String contra = "root";
    private final  String base_Datos = "BD_SITUN";
    private   String path;
    private final  String exe = "C:/Program Files/PostgreSQL/9.5/bin/pg_restore.exe";
    private Escritura es=new Escritura();
    
      public Recovery(){
      this.path = "";
    }
    
     public Recovery(String ruta){
      this.path = ruta;
    }
    
     public void setRuta(String ruta){
      this.path = ruta;
    }
    
    
    public void pgRestore() throws IOException {
  
      Runtime r = Runtime.getRuntime();
Process p=null;
ProcessBuilder pb;
r = Runtime.getRuntime();
pb = new ProcessBuilder( 
    "C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_restore.exe",
    "--host", host,
    "--port", puerto,
    "--username", usuario,
    "--dbname", base_Datos,
    "--role", "postgres",
    "--no-password",
    "--verbose",
   path);
pb.environment().put("PGPASSWORD", "root");
pb.redirectErrorStream(true);
      try {
          p = pb.start();
          } catch (IOException ex) {
          Logger.getLogger(Recovery.class.getName()).log(Level.SEVERE, null, ex);
      }

 }
    
}
