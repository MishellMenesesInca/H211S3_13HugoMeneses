package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Pizza;
import model.Venta;
import model.VentaDetalle;

public class VentaDetalleImpl extends Conexion {

// Formateo para el campo fecha 
    DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

    // Metodo para para agregar en la tabla temporal venta
    public VentaDetalle aumentarFila(int idpiz) throws SQLException, Exception {
        VentaDetalle ventadetalle = null;
        String sql = "SELECT * FROM PIZZA WHERE IDPIZ = " + idpiz;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ventadetalle = new VentaDetalle();
                Pizza pizza = new Pizza();
                ventadetalle.setIDPIZ(rs.getInt("IDPIZ"));
                pizza.setNombre(rs.getString("NOMPIZ"));
                pizza.setTamaño(rs.getString("TAMPIZ"));
                pizza.setPrecio(rs.getDouble("PREPIZ"));
                ventadetalle.setPizza(pizza);
            }
            ps.close();
            rs.close();
       } catch (Exception e) {
            System.out.println(e.getMessage() + "Error en add_D ");
        } finally {
            this.cerrarCnx();
            return ventadetalle;
        }
    }

    public void registrarVentaDetalle(List<VentaDetalle> listaVentaDetalle, int idVenta) throws Exception {
        String sql = "INSERT INTO VENTA_DETALLE (CANVENDET,IDVEN,IDPIZ,SUBVENDET) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            for (VentaDetalle ventadetalle : listaVentaDetalle) {
                ps.setInt(1, ventadetalle.getCANVENDET());
                ps.setInt(2, idVenta);
                ps.setInt(3, ventadetalle.getIDPIZ());
                ps.setDouble(4, ventadetalle.getSUBVENDET());
                ps.executeUpdate();
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrarVentaDetalle_D " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    public void registrarVenta(Venta venta) throws Exception {
        String sql = "INSERT INTO VENTA (FECVEN,IDCLI,IDUSU,TOTVEN) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, formato.format(venta.getFECVEN()));
            ps.setInt(2, venta.getCliente().getCodigo());
            ps.setInt(3, venta.getUsuario().getCodigo());
            ps.setDouble(4, venta.getTOTVEN());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrarVenta_D " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.cerrarCnx();
        }
    }

    public int ultimoIdVenta() {
        try {
            PreparedStatement ps1 = this.conectar().prepareStatement("SELECT MAX(V.IDVEN) AS IDVEN FROM VENTA V");
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return rs.getInt("IDVEN");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en ultimoIdVenta_D " + e.getMessage());
        }
        return -1;
    }

    public List<Venta> listarVenta() throws Exception {
        List<Venta> listado = new ArrayList<>();
        Cliente cliente;
        Venta venta;
        String sql = "SELECT * FROM vVenta ORDER BY IDVEN DESC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                venta = new Venta();
                venta.setIDVEN(rs.getInt("IDVEN"));
                venta.setFECVEN(rs.getDate("FECVEN"));
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("IDCLI"));
                cliente.setNombre(rs.getString("NOMCLI"));
                cliente.setDni(rs.getString("DNICLI"));
                venta.setCliente(cliente);
                listado.add(venta);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en el listarVenta_D " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }

}
