package pruebas;

import java.sql.*;
 
public class Conexion {
 
    public void connectDatabase() {
        try 
        {
            try 
            { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
           
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/papeleria",
                    "postgres", "Luis1125@");
 
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            
        } catch (SQLException sqle) 
        {
            System.out.println("Error: " + sqle);
        }
    }
    
    public void consultaDatos()
    {
        
    }
    
   public static void main(String[] args) {
        Conexion javaPostgreSQLBasic = new Conexion();
        javaPostgreSQLBasic.connectDatabase(); 
    }
}