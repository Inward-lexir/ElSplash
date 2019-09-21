package modelos;

public class Usuario {

    private int id;
    private String Usuario;
    private String Nombre;
    private String Correo;
    private String Direccion;
    private String Pass;


    public Usuario() {

    }

    //Hacer consultas
    public Usuario(int id, String usuario, String nombre, String correo, String direccion, String pass) {
        this.id = id;
        Usuario = usuario;
        Nombre = nombre;
        Correo = correo;
        Direccion = direccion;
        Pass = pass;
    }

    //Este que no tiene un id, es para agregar un usuario
    public Usuario(String usuario, String nombre, String correo, String direccion, String pass) {
        Usuario = usuario;
        Nombre = nombre;
        Correo = correo;
        Direccion = direccion;
        Pass = pass;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
