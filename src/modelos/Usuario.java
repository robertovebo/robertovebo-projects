package modelos;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Usuario {
    private String nombreUsuario, contrasena;
    private int id;

    public Usuario(String nombreUsuario, String contrasena, int id) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Usuario> obtenerUsuario(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        String consulta = "SELECT * FROM usuarios";

        try (
                Connection con = MySQLConnection.connect();
                Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = st.executeQuery(consulta);
        ){

            while(rs.next()) {
                usuarios.add(new Usuario(
                        rs.getString("usuario"),
                        rs.getString("contrasena"),
                        rs.getInt("idusuario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public static boolean agregarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios " +
                "(usuario, contrasena)" +
                "VALUES (?, ?)";
        int creados = 0;

        try(Connection conexion = MySQLConnection.connect();
            PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, usuario.getNombreUsuario());
            pst.setString(2, usuario.getContrasena());

            creados = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creados > 0;
    }

    public static boolean actualizarUsuario(Usuario usuario) {
        String query = "UPDATE usuarios SET usuario = ?, contrasena = ? WHERE idusuario = ?";

        int actualizados = 0;

        try (Connection conn = MySQLConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query);
        ){
            pst.setString(1, usuario.getNombreUsuario());
            pst.setString(2, usuario.getContrasena());
            pst.setInt(3, usuario.getId());

            actualizados = pst.executeUpdate();
            System.out.println(actualizados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }

    public static boolean borrarUsuario(int id){
        String consulta = "DELETE FROM usuarios WHERE idusuario = " + id;

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
