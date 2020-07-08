package coneccionconbd;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
/**
 *
 * @author luisa
 */
public class JDBCPostgreSQL {
    
    public Connection conectar(String db_url, String usuario, String password)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            
        }catch(ClassNotFoundException e)
        {
            System.err.println("Error al registrar el driver de PostgreSQL: " + e);
        }
        
        Connection connection = null;
        
        try
        {
            connection = DriverManager.getConnection(db_url, usuario, password);
        }catch(SQLException ex)
        {
            System.err.println("Conexion Fallida a la Basde de Datos.");
            ex.printStackTrace();
        }
        
        return connection;
    }
    
    //Query = Consulta a ingresar en nuestra base de datos.
    
    public boolean consultar(Connection conn, String query)
    {
        Statement stmt = null;
        
        try
        {
            //c.setAutoCommit(false);
            
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            //La columna inicia en 1
            for(int i = 1;i <= columnCount; i++)
            {
                String name = rsmd.getColumnName(i);
                System.out.print(name + "\t");
            
            }
            
            System.out.print("\n");
            
            while(rs.next())
            {
                for(int i = 1;i <= columnCount ; i++)
                {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.print("\n");
            }
            
            rs.close();
            stmt.close();
        }catch(Exception e)
        {
            System.err.println(e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        
        System.out.println("Conexion con la Base de Datos Exitosa.");
        
        return true;
    }
    
    public boolean insertarVenta(Connection conn, Venta v)
    {
        String sqlInsertCliente = "insert into papeleria.venta values (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        
        int tuplas = 0;
        
        try {
            
            stmt = conn.prepareStatement(sqlInsertCliente);
            stmt.setInt(1,v.getID());
            stmt.setString(2,v.getObservaciones());
            stmt.setString(3,v.getFecha());
            stmt.setInt(4,v.getFolio());
            stmt.setInt(5,v.getId_Empleado());
            
            tuplas = stmt.executeUpdate();
            
            }catch ( Exception e ) 
            {
               System.err.println( e.getClass().getName()+": "+ e.getMessage() );
               System.exit(0);
            }
        
        System.out.println("Operation done successfully" + tuplas);
        return true;
    }
    
    public boolean insertarCliente(Connection conn, Cliente cli)
    {
        String sqlInsertCliente = "insert into papeleria.cliente values (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        
        int tuplas = 0;
        
        try {
            
            stmt = conn.prepareStatement(sqlInsertCliente);
            stmt.setInt(1,cli.getID());
            stmt.setString(2,cli.getNombre());
            stmt.setString(3,cli.getDomicilio());
            stmt.setString(4,cli.getTelefono());
            stmt.setString(5,cli.getTipo_telefono());
            
            tuplas = stmt.executeUpdate();
            
            }catch ( Exception e ) 
            {
               //System.err.println( e.getClass().getName()+": "+ e.getMessage() );
               JOptionPane.showMessageDialog(null, "Hay un dato duplicado", "Advertencia",JOptionPane.WARNING_MESSAGE);
               //System.exit(0);
            }
        
        System.out.println("Insercion de datos exitosa." + tuplas);
        return true;
    }
    
    public void eliminarCliente(Connection conn, int id)
    {
        String sqlDeletecli = "delete from papeleria.cliente where id_cliente = '" + id + "'" ;
        PreparedStatement stmt = null;
        
        int tuplas = 0;
        
        try
        {
            stmt = conn.prepareStatement(sqlDeletecli);
            tuplas = stmt.executeUpdate();
            
        }catch(SQLException ex)
        {
            
        }
    }
    
    public void actualizarCliente(Connection conn, int id, Cliente cli)
    {
        String sqlUpdateCli = "update papeleria.cliente set nombre = ?,domicilio = ?,telefono = ?,tipo_telefono = ? where id_cliente= ?";
        
        PreparedStatement stmt = null;
        int tuplas = 0;
        
        try
        {
            stmt = conn.prepareStatement(sqlUpdateCli);
            stmt.setString(1,cli.getNombre());
            stmt.setString(2,cli.getDomicilio());
            stmt.setString(3,cli.getTelefono());
            stmt.setString(4,cli.getTipo_telefono());
            stmt.setInt(5,id);
            
            tuplas = stmt.executeUpdate();
            
        }catch(SQLException ex)
        {
            
        }
    }
    
    public static void main(String[] args)
    {
        String BD_URL = "jdbc:postgresql://localhost:5432/papeleria";
        String USER = "postgres";
        String PASSWORD = "Luis1125@";
        Connection conn = null;
        
        JDBCPostgreSQL jdbc_conn = new JDBCPostgreSQL();
        conn = jdbc_conn.conectar(BD_URL, USER, PASSWORD);
        
        if(conn != null)
        {
            System.out.println("Conexion establecida con la base de datos ahora.");
        }else
        {
            System.out.println("Fallo en conectar con la base de datos.");
        }
        
        /*String consulta = "select * from papeleria.venta;";
        jdbc_conn.consultar(conn, consulta);
        
        Venta v1 = new Venta(50005," ","2017-08-20",20006,70005);
        jdbc_conn.insertarVenta(conn, v1);
        
        jdbc_conn.consultar(conn, consulta);
        
        String consulta2 = "select * from papeleria.venta natural join papeleria.empleado";
        jdbc_conn.consultar(conn, consulta2);
        
        String consulta3 = "select v.folio_v,fecha_v,c.nombre,e.nombre,descipcion " +
                           "from papeleria.cliente as c inner join papeleria.venta as v on c.id_cliente = v.id_cliente " +
                           "inner join papeleria.empleado as e on v.id_empleado = e.id_empleado " +
                           "inner join papeleria.detalle_venta as dv on v.folio_v = dv.folio_v " +
                           "inner join papeleria.producto as p on dv.codigo = p.codigo " +
                           "order by v.folio_v;";
        jdbc_conn.consultar(conn, consulta3);
        
        */
        
        String consulta = "select * from papeleria.cliente";
        jdbc_conn.consultar(conn, consulta);
        
        Cliente c1 = new Cliente(50006,"Diana Laura","And. Yogana","+529512832186","Movil");
        jdbc_conn.insertarCliente(conn, c1);
        
        jdbc_conn.consultar(conn, consulta);
        
        try
        {
            conn.close();
        }catch(SQLException e)
        { 
        }
    }
    
}
