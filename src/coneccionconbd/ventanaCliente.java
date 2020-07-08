package coneccionconbd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ventanaCliente extends JFrame implements ActionListener {
    
    JLabel lbl_Cliente, lbl_nombre, lbl_domi, lbl_tel, lbl_tipoT;
    JTextField txt_cliente, txt_nombre, txt_domi, txt_tel, txt_tipoT, txt_eliminar;
    JButton btn_eliminar, btn_guardar,  btn_consultar, btn_conectar, btn_nuevo, btn_agregar;
    JTable tableCli;
    JPanel panel_1 , panel_2;
    
    String BD_URL = "jdbc:postgresql://localhost:5432/papeleria";
    String USER = "postgres";
    String PASSWORD = "Luis1125@";
    Connection conn = null;
    
    public ventanaCliente()
    {
        initComponents();
    }
    
    public void initComponents()
    {
        this.setTitle("Clientes Guardados");
        this.setSize(470,600);
        
//-------------------------- INICIALIZAMOS COMPONENTES -------------------------
        //----ETIQUETAS-------
        Dimension lbl_Size = new Dimension (130,25);
        
        lbl_Cliente = new JLabel("Cliente :");
        lbl_nombre = new JLabel("Nombre :");
        lbl_domi = new JLabel("Domiilio :");
        lbl_tel = new JLabel("Telefono :");
        lbl_tipoT = new JLabel("Tipo telefono :");
        
        //----CUADROS DE INGRESO DE TEXTO-----
         Dimension txt_Size = new Dimension (250,25);
        
        txt_cliente = new JTextField();
        txt_nombre = new JTextField();
        txt_domi = new JTextField();
        txt_tel = new JTextField();
        txt_tipoT = new JTextField();
        txt_eliminar = new JTextField();
        
        //---------BOTONES-----------
        Dimension btn_Size = new Dimension(120,30);
        
        btn_eliminar = new JButton("Eliminar");
        btn_guardar = new JButton("Actualizar");
        btn_consultar = new JButton("Nuevo Cliente");
        btn_agregar = new JButton("Agregar cliente");
        btn_conectar = new JButton("Nuevo");
        btn_nuevo = new JButton("Modificar");
        
        //----CREAMOS NESTRO PANEL DE REGISTRO
        panel_1 = new JPanel();
        panel_1.setLayout(null);
        
            //ETIQUETAS CONFIG.        
            lbl_Cliente.setSize(lbl_Size);
            lbl_Cliente.setLocation(25, 25);
            lbl_Cliente.setBorder(BorderFactory.createLineBorder(Color.black));
            
            lbl_nombre.setSize(lbl_Size);
            lbl_nombre.setLocation(25,60);
            lbl_nombre.setBorder(BorderFactory.createLineBorder(Color.black));
            
            lbl_domi.setSize(lbl_Size);
            lbl_domi.setLocation(25,95);
            lbl_domi.setBorder(BorderFactory.createLineBorder(Color.black));
            
            lbl_tel.setSize(lbl_Size);
            lbl_tel.setLocation(25,130);
            lbl_tel.setBorder(BorderFactory.createLineBorder(Color.black));
            
            lbl_tipoT.setSize(lbl_Size);
            lbl_tipoT.setLocation(25,165);
            lbl_tipoT.setBorder(BorderFactory.createLineBorder(Color.black));
            
            //CUADROS DE INGESO DE TEXTO ---> CONFIG.
            txt_cliente.setSize(txt_Size);
            txt_cliente.setLocation(165,25);
            txt_cliente.setEnabled(false);
            
            txt_nombre.setSize(txt_Size);
            txt_nombre.setLocation(165,60);
            txt_nombre.setEnabled(false);
            
            txt_domi.setSize(txt_Size);
            txt_domi.setLocation(165,95);
            txt_domi.setEnabled(false);
            
            txt_tel.setSize(txt_Size);
            txt_tel.setLocation(165,130);
            txt_tel.setEnabled(false);
            
            txt_tipoT.setSize(txt_Size);
            txt_tipoT.setLocation(165,165);
            txt_tipoT.setEnabled(false);
            
            txt_eliminar.setSize(txt_Size);
            txt_eliminar.setLocation(25, 260);
            
            //-----BOTONES DE ACCION------
            btn_eliminar.setSize(btn_Size);
            btn_eliminar.setLocation(25,210);
            btn_eliminar.addActionListener(this);
            
            btn_guardar.setSize(btn_Size);
            btn_guardar.setLocation(155,210 );
            btn_guardar.addActionListener(this);
            btn_guardar.setVisible(false);
            
            btn_consultar.setSize(btn_Size);
            btn_consultar.setLocation(285,210);
            btn_consultar.addActionListener(this);
            btn_consultar.setVisible(false);
            
            btn_agregar.setSize(btn_Size);
            btn_agregar.setLocation(285,210);
            btn_agregar.addActionListener(this);
            
            btn_nuevo.setSize(btn_Size);
            btn_nuevo.setLocation(155,210);
            btn_nuevo.addActionListener(this);
            //btn_nuevo.setVisible(false);
            
            //-------TABLA DONDE SE MOSTRARAN LOS DATOS Y CONFIG.-------------
            tableCli = new JTable();
            mostrarDatos();
            
            
        panel_1.add(lbl_Cliente);
        panel_1.add(lbl_nombre);
        panel_1.add(lbl_domi);
        panel_1.add(lbl_tel);
        panel_1.add(lbl_tipoT);
        
        panel_1.add(txt_cliente);
        panel_1.add(txt_nombre);
        panel_1.add(txt_domi);
        panel_1.add(txt_tel);
        panel_1.add(txt_tipoT);
        panel_1.add(txt_eliminar);
        
        panel_1.add(btn_eliminar);
        panel_1.add(btn_guardar);
        panel_1.add(btn_nuevo);
        panel_1.add(btn_consultar);
        panel_1.add(btn_agregar);
        
        panel_1.add(tableCli);
        
        //AGREGAMOS LOS PANELES A UESTRO JFRAME
        this.add(panel_1);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void mostrarDatos()
    {
        tableCli.setLocation(25,300);
        tableCli.setSize(400,200);
        
        DefaultTableModel modelo = new DefaultTableModel()
        {
            public boolean isCellEditable(int rowIndex,int columnIndex)
            {
                return false;
            }
        };
        modelo.addColumn("ID CLIENTE");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DOMICILIO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("TIPO TELEFONO");

        tableCli.setModel(modelo);

        JDBCPostgreSQL conexion = new JDBCPostgreSQL();
        conn = conexion.conectar(BD_URL, USER, PASSWORD);
        
        String query = "select * from papeleria.cliente";
        String[] datos = new String[5];

        try
        {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                modelo.addRow(datos);
            }

        tableCli.setModel(modelo);
        
        tableCli.addMouseListener(new MyClickListener());

        }catch(SQLException ex)
        {

        }
    }
    
    private class MyClickListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent event)
        {
            int filaSeleccionada;
            
            try{
                
                filaSeleccionada = tableCli.getSelectedRow();
                
                txt_cliente.setText(tableCli.getValueAt(filaSeleccionada,0).toString());
                txt_nombre.setText(tableCli.getValueAt(filaSeleccionada,1).toString());
                txt_domi.setText(tableCli.getValueAt(filaSeleccionada,2).toString());
                txt_tel.setText(tableCli.getValueAt(filaSeleccionada,3).toString());
                txt_tipoT.setText(tableCli.getValueAt(filaSeleccionada,4).toString());
                
                if(filaSeleccionada == -1)
                {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
                }
                else if(filaSeleccionada != -1)
                {
                }
            }catch(Exception e)
            {
            }
        }   
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn_eliminar)
        {
            if(!txt_eliminar.getText().isEmpty())
            {
                int idCli = Integer.parseInt(txt_eliminar.getText());
                
                JDBCPostgreSQL jdbc_conn = new JDBCPostgreSQL();
                conn = jdbc_conn.conectar(BD_URL, USER, PASSWORD);
                
                jdbc_conn.eliminarCliente(conn, idCli);
                
                mostrarDatos();
            }else
            {
                JOptionPane.showMessageDialog(null, "Para eliminar un cliente ingrese un ID valido", 
                                                    "Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            
        }else if(e.getSource() == btn_nuevo)
        {
            btn_nuevo.setVisible(false);
            btn_guardar.setVisible(true);
            
            /*txt_cliente.setText("");
            txt_nombre.setText("");
            txt_domi.setText("");
            txt_tel.setText("");
            txt_tipoT.setText("");*/
            
            txt_cliente.setEnabled(true);
            txt_nombre.setEnabled(true);
            txt_domi.setEnabled(true);
            txt_tel.setEnabled(true);
            txt_tipoT.setEnabled(true);
            
        }else if(e.getSource() == btn_guardar)
        {
            btn_nuevo.setVisible(false);
            btn_guardar.setVisible(true);
            
            if(!txt_cliente.getText().isEmpty() && !txt_nombre.getText().isEmpty() && !txt_domi.getText().isEmpty() &&
               !txt_tel.getText().isEmpty() && !txt_tipoT.getText().isEmpty())
            {
                int cliente = Integer.parseInt(txt_cliente.getText());
                String nombre =  txt_nombre.getText();
                String domi = txt_domi.getText();
                String tel = txt_tel.getText();
                String tipoT = txt_tipoT.getText();
                
                Cliente cli = new Cliente(cliente,nombre,domi,tel,tipoT);
                
                JDBCPostgreSQL jdbc_conn = new JDBCPostgreSQL();
                conn = jdbc_conn.conectar(BD_URL, USER, PASSWORD);
                
                jdbc_conn.actualizarCliente(conn,cliente,cli);
                mostrarDatos();
            }else
            {
                JOptionPane.showMessageDialog(null, "Falta algun dato por favor ingresa todos los datos", 
                                                    "Advertencia",JOptionPane.WARNING_MESSAGE);
            }
            
            txt_cliente.setText("");
            txt_nombre.setText("");
            txt_domi.setText("");
            txt_tel.setText("");
            txt_tipoT.setText("");
            
            txt_cliente.setEnabled(false);
            txt_nombre.setEnabled(false);
            txt_domi.setEnabled(false);
            txt_tel.setEnabled(false);
            txt_tipoT.setEnabled(false);
            
            btn_nuevo.setVisible(true);
            btn_guardar.setVisible(false);
        }else if(e.getSource() == btn_agregar)
        {            
            btn_agregar.setVisible(false);
            btn_consultar.setVisible(true);
            
            txt_cliente.setEnabled(true);
            txt_nombre.setEnabled(true);
            txt_domi.setEnabled(true);
            txt_tel.setEnabled(true);
            txt_tipoT.setEnabled(true);
            
        }else if(e.getSource() == btn_consultar)
        {            
            if(!txt_cliente.getText().isEmpty() && !txt_nombre.getText().isEmpty() && !txt_domi.getText().isEmpty() &&
               !txt_tel.getText().isEmpty() && !txt_tipoT.getText().isEmpty())
            {
                int cliente = Integer.parseInt(txt_cliente.getText());
                String nombre =  txt_nombre.getText();
                String domi = txt_domi.getText();
                String tel = txt_tel.getText();
                String tipoT = txt_tipoT.getText();
                
                Cliente cli = new Cliente(cliente,nombre,domi,tel,tipoT);
                
                JDBCPostgreSQL jdbc_conn = new JDBCPostgreSQL();
                conn = jdbc_conn.conectar(BD_URL, USER, PASSWORD);
                
                jdbc_conn.insertarCliente(conn, cli);
                mostrarDatos();
            }else
            {
                JOptionPane.showMessageDialog(null, "Falta algun dato por favor ingresa todos los datos", 
                                                    "Advertencia",JOptionPane.WARNING_MESSAGE);
            }
             
            txt_cliente.setText("");
            txt_nombre.setText("");
            txt_domi.setText("");
            txt_tel.setText("");
            txt_tipoT.setText("");
            
            txt_cliente.setEnabled(false);
            txt_nombre.setEnabled(false);
            txt_domi.setEnabled(false);
            txt_tel.setEnabled(false);
            txt_tipoT.setEnabled(false);
            
            btn_agregar.setVisible(true);
            btn_consultar.setVisible(false);
        }
        
    }   
    
    public static void main(String []args)
    {
        new ventanaCliente();
    } 
}