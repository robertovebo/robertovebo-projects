package modelos;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Cliente {
    private String nombre, apellido, rfc, correo;
    private int id;

    public Cliente(String nombre, String apellido, String rfc, String correo, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rfc = rfc;
        this.correo = correo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        String consulta = "SELECT * FROM clientes";

        try (
                Connection con = MySQLConnection.connect();
                Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = st.executeQuery(consulta);
        ){

            while(rs.next()) {
                clientes.add(new Cliente(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("rfc"),
                        rs.getString("correo"),
                        rs.getInt("idcliente")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public static boolean agregarCliente(Cliente cliente) {
        String query = "INSERT INTO clientes " +
                "(nombre, apellido, rfc, correo)" +
                "VALUES (?, ?, ?, ?)";
        int creados = 0;

        try(Connection conexion = MySQLConnection.connect();
            PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellido());
            pst.setString(3, cliente.getRfc());
            pst.setString(4, cliente.getCorreo());

            creados = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creados > 0;
    }

    public static boolean actualizarCliente(Cliente cliente) {
        String query = "UPDATE clientes SET nombre = ?, apellido = ?, rfc = ?, correo = ? WHERE idcliente = ?";

        int actualizados = 0;

        try (Connection conn = MySQLConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query);
        ){
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellido());
            pst.setString(3, cliente.getRfc());
            pst.setString(4, cliente.getCorreo());
            pst.setInt(5, cliente.getId());

            actualizados = pst.executeUpdate();
            System.out.println(actualizados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }

    public static boolean borrarCliente(int id){
        String consulta = "DELETE FROM clientes WHERE idcliente = " + id;

        int eliminados = 0;

        try (
                Connection con = MySQLConnection.connect();
                Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ){
            eliminados = st.executeUpdate(consulta);
            System.out.println("Eliminados: " + eliminados);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminados > 0;
    }
}
