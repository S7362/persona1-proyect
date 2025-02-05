
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MiClase{

 public class Persona {

    public static void main(String[]args){
     
    }
    private String cedula;
    private String nombre;
    private int edad;
    private String direccion;

    public Persona(String cedula, String nombre, int edad, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;   
    }
    // Getters y setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
public class PersonaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    // CREATE
    public void crearPersona(Persona persona) {
        String sql = "INSERT INTO personas (cedula, nombre, edad, direccion) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, persona.getCedula());
            pstmt.setString(2, persona.getNombre());
            pstmt.setInt(3, persona.getEdad());
            pstmt.setString(4, persona.getDireccion());
            pstmt.executeUpdate();
            System.out.println("Persona creada exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // READ
    public List<Persona> obtenerPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM personas";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Persona persona = new Persona(
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getString("direccion")
                );
                personas.add(persona);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return personas;
    }

    // UPDATE
    public void actualizarPersona(Persona persona) {
        String sql = "UPDATE personas SET nombre = ?, edad = ?, direccion = ? WHERE cedula = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, persona.getNombre());
            pstmt.setInt(2, persona.getEdad());
            pstmt.setString(3, persona.getDireccion());
            pstmt.setString(4, persona.getCedula());
            pstmt.executeUpdate();
            System.out.println("Persona actualizada exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // DELETE
    public void eliminarPersona(String cedula) {
        String sql = "DELETE FROM personas WHERE cedula = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cedula);
            pstmt.executeUpdate();
            System.out.println("Persona eliminada exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
 }
}