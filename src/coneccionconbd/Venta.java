package coneccionconbd;

public class Venta {
    
    private int id_Cliente;
    private String observaciones;
    private String fecha_v;
    private int folio_v;
    private int id_empleado;
    
    public Venta(int id_Cliente, String observaciones, String fecha_v, int folio_v, int id_empleado)
    {
        this.id_Cliente = id_Cliente;
        this.observaciones = observaciones;
        this.fecha_v = fecha_v;
        this.folio_v = folio_v;
        this.id_empleado = id_empleado;
    }
    
    public void setID(int id)
    {
        this.id_Cliente = id;
    }
    
    public int getID()
    {
        return id_Cliente;
    }
    
    public void setObservaciones(String obs)
    {
        this.observaciones = obs;        
    }
    
    public String getObservaciones()
    {
        return observaciones;        
    }
    
    public void setFecha(String fecha)
    {
        this.fecha_v = fecha;        
    }
    
    public String getFecha()
    {
        return fecha_v;        
    }
    
    public void setFolio(int f)
    {
        this.folio_v = f;        
    }
    
    public int getFolio()
    {
        return folio_v;        
    }
    
    public void setId_Empleado(int id_emp)
    {
        this.id_empleado = id_emp;
    }
    
    public int getId_Empleado()
    {
        return id_empleado;        
    }
    
}
