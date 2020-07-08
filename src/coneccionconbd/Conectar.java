package coneccionconbd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {
    Connection conectar=null;
    public Connection conexion(){
        try {
            Class.forName("org.postgresql.Driver");
            conectar=DriverManager.getConnection("jdbc:postgresql://localhost:5432/papeleria","root","root");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }
    
}
