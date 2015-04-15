
/**
 * Write a description of class Item here.
 * 
 * @author marcelinojgm
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String descripcion;
    private float pesoKg;

    /**
     * Constructor for objects of class Item
     * @param descripcion del objeto
     * @para peso en kg del objeto
     */
    public Item(String descripcion, float peso)
    {
        this.descripcion = descripcion == null || descripcion == "" ? "objeto desconocido":descripcion;
        this.pesoKg = peso;
    }

    /**
     * retorna la descricion del objeto
     * @return descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }

    /**
     * retorna el peso del objeto en kg
     * 
     * @return pesoKg
     * 
     */
    public float getPesoKg(){
        return pesoKg;
    }
    
    /**
     * informacion del objeto
     * @return descripcion y peso del objeto
     * 
     */
    public String infoItemn(){
     return "descripcion:" + descripcion+ "   peso: "+  pesoKg  + " kg \n";
    }
}
