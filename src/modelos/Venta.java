package modelos;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Venta {
    private String nombre;
    private int unidades, precio, id;
    private double total;

    public Venta(String nombre, int unidades, int precio, double total, int id) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
        this.total = total;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Venta> obtenerVentas(){
        ArrayList<Venta> ventas = new ArrayList<Venta>();

        String consulta = "SELECT * FROM ventas";

        try (
                Connection con = MySQLConnection.connect();
                Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = st.executeQuery(consulta);
        ){

            while(rs.next()) {
                ventas.add(new Venta(
                        rs.getString("nombre"),
                        rs.getInt("unidades"),
                        rs.getInt("precio"),
                        rs.getInt("total"),
                        rs.getInt("idventa")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ventas;
    }

    public static boolean agregarVenta(Venta venta) {
        String query = "INSERT INTO ventas " +
                "(nombre, unidades, precio, total)" +
                "VALUES (?, ?, ?, ?)";
        int creados = 0;

        try(Connection conexion = MySQLConnection.connect();
            PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, venta.getNombre());
            pst.setInt(2, venta.getUnidades());
            pst.setInt(3, venta.getPrecio());
            pst.setDouble(4, venta.getTotal());

            creados = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creados > 0;
    }

    public static boolean actualizarVenta(Venta venta) {
        String query = "UPDATE ventas SET nombre = ?, unidades = ?, precio = ?, total = ? WHERE idventa = ?";

        int actualizados = 0;

        try (Connection conn = MySQLConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query);
        ){
            pst.setString(1, venta.getNombre());
            pst.setInt(2, venta.getUnidades());
            pst.setInt(3, venta.getPrecio());
            pst.setDouble(4, venta.getTotal());
            pst.setInt(5, venta.getId());

            actualizados = pst.executeUpdate();
            System.out.println(actualizados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }

    public static boolean borrarVenta(int id){
        String consulta = "DELETE FROM ventas WHERE idventa = " + id;

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

    public static boolean borrarDatosTablaVenta(){
        String consulta = "DELETE FROM ventas";

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

    public static ArrayList<Venta> obtenerRegistroVentas(){
        ArrayList<Venta> ventas = new ArrayList<Venta>();

        String consulta = "SELECT * FROM registro_ventas";

        try (
                Connection con = MySQLConnection.connect();
                Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = st.executeQuery(consulta);
        ){

            while(rs.next()) {
                ventas.add(new Venta(
                        rs.getString("nombre"),
                        rs.getInt("unidades"),
                        rs.getInt("precio"),
                        rs.getInt("total"),
                        rs.getInt("idregistroventas")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ventas;
    }

    public static boolean agregarRegistroVenta(Venta venta) {
        String query = "INSERT INTO registro_ventas " +
                "(nombre, unidades, precio, total)" +
                "VALUES (?, ?, ?, ?)";
        int creados = 0;

        try(Connection conexion = MySQLConnection.connect();
            PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, venta.getNombre());
            pst.setInt(2, venta.getUnidades());
            pst.setInt(3, venta.getPrecio());
            pst.setDouble(4, venta.getTotal());

            creados = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creados > 0;
    }

    public static boolean actualizarRegistroVenta(Venta venta) {
        String query = "UPDATE registro_ventas SET nombre = ?, unidades = ?, precio = ?, total = ? WHERE idregistroventas = ?";

        int actualizados = 0;

        try (Connection conn = MySQLConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query);
        ){
            pst.setString(1, venta.getNombre());
            pst.setInt(2, venta.getUnidades());
            pst.setInt(3, venta.getPrecio());
            pst.setDouble(4, venta.getTotal());
            pst.setInt(5, venta.getId());

            actualizados = pst.executeUpdate();
            System.out.println(actualizados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }

    public static boolean borrarRegistroVenta(int id){
        String consulta = "DELETE FROM registro_ventas WHERE idregistroventas = " + id;

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
