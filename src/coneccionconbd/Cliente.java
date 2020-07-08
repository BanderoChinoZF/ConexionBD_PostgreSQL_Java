package coneccionconbd;

public class Cliente {
    
    private int id_Cliente;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String tipo_telefono;
    
    public Cliente(int id_Cliente, String nombre, String domicilio, String telefono, String tipo_telefono)
    {
        this.id_Cliente = id_Cliente;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.tipo_telefono = tipo_telefono;
    }
    
    public void setID(int id)
    {
        this.id_Cliente = id;
    }
    
    public int getID()
    {
        return id_Cliente;
    }
    
    public void setNombre(String nom)
    {
        this.nombre = nom;        
    }
    
    public String getNombre()
    {
        return nombre;        
    }
    
    public void setDomicilio(String dom)
    {
        this.domicilio = dom;        
    }
    
    public String getDomicilio()
    {
        return domicilio;        
    }
    
    public void setTelefono(String tel)
    {
        this.telefono = tel;        
    }
    
    public String getTelefono()
    {
        return telefono;        
    }
    
    public void setTipo_telefono(String tipo_tel)
    {
        this.tipo_telefono = tipo_tel;
    }
    
    public String getTipo_telefono()
    {
        return tipo_telefono;        
    }
    
}