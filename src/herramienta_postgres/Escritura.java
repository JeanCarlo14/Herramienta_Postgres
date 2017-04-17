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

/**
 *
 * @author nacho
 */
public class Escritura {
    
       public void escribir(InputStream p) throws IOException{{
        InputStream is = p;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine()) !=null){
         System.out.println(line);
         System.out.println("\n"); 
        }
    }
    
}
       
}
