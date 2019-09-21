package modelos;

public class Store {

    private int id;
    private String nombre;
    private String direccion;
    private double latitude;
    private double longuitud;
    private String descripcion;

    public Store() {
    }

    public Store(int id, String nombre, String direccion, double latitude, double longuitud, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitude = latitude;
        this.longuitud = longuitud;
        this.descripcion = descripcion;
    }

    public Store(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(double longuitud) {
        this.longuitud = longuitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
