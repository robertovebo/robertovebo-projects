package modelos;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Producto {
    private String nombre, marca;
    private int precio, cantidad, id;

    public Producto(int id, String nombre, String marca, int precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", id=" + id +
                '}';
    }

    public static ArrayList<Producto> obtenerProducto(){
        ArrayList<Producto> productos = new ArrayList<Producto>();

        String consulta = "SELECT * FROM productos";

        try (
                Connection con = MySQLConnection.connect();
                Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet rs = st.executeQuery(consulta);
        ){

            while(rs.next()) {
                productos.add(new Producto(
                        rs.getInt("idproducto"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getInt("precio"),
                        rs.getInt("cantidad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public static boolean agregarProducto(Producto producto) {
        String query = "INSERT INTO productos " +
                "(idproducto, nombre, marca, precio, cantidad)" +
                "VALUES (?, ?, ?, ?, ?)";
        int creados = 0;

        try(Connection conexion = MySQLConnection.connect();
            PreparedStatement pst = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            pst.setInt(1, producto.getId());
            pst.setString(2, producto.getNombre());
            pst.setString(3, producto.getMarca());
            pst.setInt(4, producto.getPrecio());
            pst.setInt(5, producto.getCantidad());

            creados = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creados > 0;
    }

    public static boolean actualizarProducto(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, marca = ?, precio = ?, cantidad = ? WHERE idproducto = ?";

        int actualizados = 0;

        try (Connection conn = MySQLConnection.connect();
             PreparedStatement pst = conn.prepareStatement(query);
        ){
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getMarca());
            pst.setInt(3, producto.getPrecio());
            pst.setInt(4, producto.getCantidad());
            pst.setInt(5, producto.getId());

            actualizados = pst.executeUpdate();
            System.out.println(actualizados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }

    public static boolean borrarProducto(int id){
        String consulta = "DELETE FROM productos WHERE idproducto = " + id;

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
