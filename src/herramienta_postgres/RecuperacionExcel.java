/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramienta_postgres;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Fabiola
 */
public class RecuperacionExcel extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    DefaultListModel  dlm ;
    DefaultListModel  dlm1 ;
    Iterator<Sheet> sheetIt;
    Connection con;
    
    public RecuperacionExcel() {
        initComponents();
        ruta = "";
        dlm =  new DefaultListModel();
        dlm1 =  new DefaultListModel();
        this.lstHojasCargadas.setModel(dlm);
        this.jList1.setModel(dlm1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    new Menu().setVisible(true);
                    setVisible(false);
                    //System.exit(0);//cierra aplicacion
                }
            });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblArchivoSeleccionado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstHojasCargadas = new javax.swing.JList<>();
        btnSiguiente = new javax.swing.JButton();
        btnSaltar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnProcesarTodos = new javax.swing.JButton();
        btnTerminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Iniciar Trabajo");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Seleccionar Archivo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lstHojasCargadas);

        btnSiguiente.setEnabled(false);
        btnSiguiente.setLabel("Procesar  Libro");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnSaltar.setEnabled(false);
        btnSaltar.setLabel("Saltar Libro");
        btnSaltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaltarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        btnProcesarTodos.setText("Procesar Todos");
        btnProcesarTodos.setEnabled(false);
        btnProcesarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarTodosActionPerformed(evt);
            }
        });

        btnTerminar.setText("Terminar");
        btnTerminar.setEnabled(false);
        btnTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lblArchivoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnProcesarTodos)
                                            .addComponent(btnSaltar)
                                            .addComponent(btnTerminar)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblArchivoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(31, 31, 31)
                        .addComponent(btnSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProcesarTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTerminar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                        .addGap(49, 49, 49))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/BD_SITUN"; 
            con = DriverManager.getConnection(url, "postgres","SITUN");
            leerLibro(con);
            this.btnSiguiente.setEnabled(true);
            this.btnSaltar.setEnabled(true);
            this.btnProcesarTodos.setEnabled(true);
            this.btnTerminar.setEnabled(true);
            this.jButton1.setEnabled(false);
            this.jButton2.setEnabled(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecuperacionExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecuperacionExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecuperacionExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecuperacionExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecuperacionExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecuperacionExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperacionExcel().setVisible(true);
            }
        });
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser jfc;
        jfc = new JFileChooser();     
        File f = new File(System.getProperty("user.dir"));
        jfc.setCurrentDirectory(f);
        //jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //jfc.setFileFilter(new FileNameExtensionFilter("Archivos XLS","xls"));
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Archivos XLS","xls"));
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Archivos XLSX","xlsx"));
        jfc.showOpenDialog(this);
        File selFile = jfc.getSelectedFile();
        if(jfc.getSelectedFile() != null)
        {
            ruta = jfc.getSelectedFile().toString();        
        this.lblArchivoSeleccionado.setText(jfc.getSelectedFile().getName());
        
        this.jButton1.setEnabled(true);
        
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        siguiente();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void siguiente()
    {
        Sheet sheet = sheetIt.next();                
        
        leerHoja(sheet,con);
        dlm.addElement(sheet.getSheetName() + " --> finalizado");
        System.out.println("---------"+sheet.getSheetName()+"------------------");
        this.jList1.setSelectedIndex(this.jList1.getSelectedIndex()+1);
        validar();
    }
    private void btnSaltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaltarActionPerformed
        sheetIt.next();
        this.jList1.setSelectedIndex(this.jList1.getSelectedIndex()+1);
        validar();
    }//GEN-LAST:event_btnSaltarActionPerformed

    private void btnTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarActionPerformed
/*        this.btnSiguiente.setEnabled(false);
        this.btnSaltar.setEnabled(false);
        this.jButton2.setEnabled(true); 
        this.jButton1.setEnabled(true);
        this.btnProcesarTodos.setEnabled(false);
        this.btnTerminar.setEnabled(false);
        this.jButton1.setEnabled(false);
        dlm.removeAllElements();
        dlm1.removeAllElements();
*/

    new Menu().setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_btnTerminarActionPerformed

    private void btnProcesarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarTodosActionPerformed
        while(sheetIt.hasNext())
        {
            siguiente();
        }
    }//GEN-LAST:event_btnProcesarTodosActionPerformed

    private void validar()
    {
        if(!sheetIt.hasNext())
        {
            this.btnSiguiente.setEnabled(false);
            this.btnSaltar.setEnabled(false);
            this.btnProcesarTodos.setEnabled(false);
            this.jButton2.setEnabled(true);            
        }
    }
    private void leerLibro(Connection c)
    {
         FileInputStream file = null;
         XSSFWorkbook workbook = null;
        try {
            file = new FileInputStream(new File(ruta));
            // Crear el objeto que tendra el libro de Excel
            workbook = new XSSFWorkbook(file);
            sheetIt = workbook.iterator();
            cargarLibros();
            sheetIt = workbook.iterator();
            workbook.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecuperacionExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecuperacionExcel.class.getName()).log(Level.SEVERE, null, ex);
        }finally {            
         try {
             file.close();
         } catch (IOException ex) {
             Logger.getLogger(RecuperacionExcel.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }
    
    private void cargarLibros()
    {
        
        while(sheetIt.hasNext())
        {
            Sheet sheet = sheetIt.next();
                
                dlm1.addElement(sheet.getSheetName());
        }
        
        this.jList1.setSelectedIndex(0);
    }
            
            
    private void leerHoja(Sheet hoja,Connection conn)
    {
        Iterator<Row> rowIterator = hoja.iterator();
            rowIterator.next();
            Row row;
            boolean continuar = true;
            while (rowIterator.hasNext() && continuar){
                row = rowIterator.next();                
                boolean reg = leerRegistro(row,conn);
                continuar = reg;
            }
    }
    private boolean leerRegistro(Row row,Connection c)
    {
        if(row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().length()<=0)
            return false;
        String fechaR = "",
                        oficio="",
                        fechaO,destinatario,remitente,copia, asunto,recibido,estado;
        fechaR = row.getCell(0).getStringCellValue();
        oficio = row.getCell(1).getStringCellValue();
        fechaO = row.getCell(2).getStringCellValue();
        destinatario = row.getCell(3).getStringCellValue();
        remitente = row.getCell(4).getStringCellValue();
        copia = row.getCell(5).getStringCellValue();
        asunto = row.getCell(6).getStringCellValue();
        recibido = String.valueOf((int)row.getCell(7).getNumericCellValue()); //row.getCell(7).getStringCellValue();
        
        System.out.println("El recibido es: " + (int)row.getCell(7).getNumericCellValue());
        estado = row.getCell(8).getStringCellValue();
        System.out.print(" Ingresando " + oficio +" Por "+recibido);
        insertarRegistro(fechaR,oficio,fechaO, destinatario,remitente,copia, asunto,recibido,estado,c);
        //System.out.println(crearFecha(fechaR));
        return true;
    }
    
    private void insertarRegistro(String fechaR, String oficio, String fechaO, String destinatario,
            String remitente, String copia, String asunto, String recibido, String estado,Connection conn)
    {
        try {
            Statement stmt = conn.createStatement();
            
            String sql = "insert into TC (TC_2, TC_3, TC_4, TC_5, TC_6, TC_7, TC_8, TC_9, TC_10, TC_11, TC_12) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(crearFecha(fechaR)));
            ps.setString(2, oficio);
            ps.setDate(3, Date.valueOf(crearFecha(fechaO)));
            ps.setString(4, destinatario);
            ps.setString(5, copia);
            ps.setString(6, remitente);
            ps.setString(7, asunto);
            ps.setString(8, recibido);
            ps.setString(9, estado);
            ps.setString(10, "");
            ps.setString(11, "");
            ps.executeUpdate();
            System.out.println(" ==> Ingresado");
        } catch (SQLException ex) {
            Logger.getLogger(RecuperacionExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    private String crearFecha(String txt)
    {
      //  System.out.println(txt);
        String date = "";
        String []k = txt.split(" ");
        date+=k[4];
        date+="-";
        date+=getMonth(k[2]);
        date+="-";
        date+=k[0];
        return date;
    }
    
    private String getMonth(String mon)
    {
        switch(mon.toLowerCase()){
            case "enero":
                return "01";
            case "febrero":
                return "02";
            case "marzo":
                return "03";
            case "abril":
                return "04";
            case "mayo":
                return "05";
            case "junio":
                return "06";
            case "julio":
                return "07";
            case "agosto":
                return "08";
            case "setiembre":
            case "septiembre":
                return "09";
            case "octubre":
                return "10";
            case "noviembre":
                return "11";
            case "diciembre":
                return "12";
        }
        System.out.println("Error de mes " + mon);
        return "00";
    }
    private String ruta;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcesarTodos;
    private javax.swing.JButton btnSaltar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnTerminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblArchivoSeleccionado;
    private javax.swing.JList<String> lstHojasCargadas;
    // End of variables declaration//GEN-END:variables
}