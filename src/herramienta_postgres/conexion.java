
package herramienta_postgres;

    import java.sql.*;

   public  class conexion{
       
 public void conectar(){
   
String driver = "org.postgresql.Driver";
String connectString = "jdbc:postgresql://localhost:5432/BD_SITUN";
String user = "postgres";
String password = "root";
try{
Class.forName(driver);
Connection con = DriverManager.getConnection(connectString, user , password);
Statement stmt = con.createStatement();
stmt.executeQuery("Drop table ta");
stmt.executeQuery("Drop table te");
stmt.executeQuery("Drop table tc");
stmt.executeQuery("Drop table tu");
stmt.executeQuery("Drop table tp");
stmt.executeQuery("drop FUNCTION Enlaces(x integer)");
stmt.executeQuery("drop FUNCTION final(x integer)");
stmt.executeQuery("drop FUNCTION pre(xn integer)");
stmt.executeQuery("drop FUNCTION post(xn integer)");
stmt.close();
con.close();

}

catch ( Exception e ){
System.out.println(e.getMessage());
}
}
   }