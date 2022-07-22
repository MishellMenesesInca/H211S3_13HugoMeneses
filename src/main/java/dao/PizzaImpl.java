package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pizza;

public class PizzaImpl extends Conexion implements ICRUD<Pizza> {

    @Override
    public void guardar(Pizza pizza) throws Exception {
        try {
            String sql = "insert into pizza"
                    + " (NOMPIZ,TAMPIZ,PREPIZ)"
                    + " values (?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pizza.getNombre());
            ps.setString(2, pizza.getTamaño());
            ps.setDouble(3, pizza.getPrecio());          
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar Pizza Dao {0} ", e.getMessage());
        }finally {
            this.cerrarCnx();
        }

    }

    @Override
    public void modificar(Pizza pizza) throws Exception {
        try {
            String sql = "update prenda set NOMPIZ=?, TAMPIZ=?, PREPIZ=?  where IDPIZ=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pizza.getNombre());
            ps.setString(2, pizza.getTamaño());
            ps.setDouble(3, pizza.getPrecio());
            ps.setInt(4, pizza.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Modificar Pizza Dao {0} ", e.getMessage());
        }finally {
            this.cerrarCnx();
        }

    }

    
  

    public List<Pizza> listarTodos(int tipo) throws Exception {
        List<Pizza> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM vPizza ORDER BY IDPIZ DESC";
        
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                Pizza piz = new Pizza();
                piz.setCodigo(rs.getInt("IDPIZ"));
                piz.setNombre(rs.getString("NOMPIZ"));
                piz.setTamaño(rs.getString("TAMPIZ"));
                piz.setPrecio(rs.getDouble("PREPIZ"));              
                lista.add(piz);
          
            }
        } catch (Exception e) {
            System.out.println("Error en PizzaD/listar: " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }

    @Override
    public void eliminar(Pizza obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void restaurar(Pizza obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
