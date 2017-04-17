/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramienta_postgres;

import java.io.IOException;

/**
 *
 * @author nacho
 */
public class Backup {
   
    private final  String host = "localhost";
    private final  String puerto = "5432";
    private final  String usuario = "postgres";
    private final  String contra = "root";
    private final  String base_Datos = "BD_SITUN";
    private final  String path = "\"C:\\Users\\nacho\\Desktop\\respaldo.backup\"";
    private final  String exe = "C:/Program Files/PostgreSQL/9.5/bin\\pg_dump.exe";
    private Escritura es=new Escritura();
        public void respaldo(){

        ProcessBuilder pb = new ProcessBuilder(exe, "--verbose", "--format", "custom","--blobs", "-f", path);   
                                                                                                               
  
        Process p=null;
        pb.environment().put("PGHOST", host);
        pb.environment().put("PGPORT", puerto);
        pb.environment().put("PGUSER", usuario);
        pb.environment().put("PGPASSWORD", contra);
        pb.environment().put("PGDATABASE", base_Datos);
        pb.redirectErrorStream(true);
     
 
        try {
           p = pb.start();
           System.out.println("Proceso iniciado...");
           es.escribir(p.getInputStream());
        }
        catch (IOException e) {
            System.out.println(e);
        }
   
          
    }
    
}
