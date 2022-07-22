package dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import java.sql.Statement;

public class UsuarioImpl extends Conexion implements ICRUD<Usuario> {

    @Override
    public void guardar(Usuario usuario) throws Exception {
        try {
            String sql = "insert into usuario"
                    + " (NOMUSU,APEUSU,CELUSU,ESTUSU,DNIUSU,EMAUSU,DOMUSU,CODUBI)"
                    + " values (?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCelular());
            ps.setString(4, "A");
            ps.setString(5, usuario.getDni());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getDomicilio());
            ps.setString(8, usuario.getCODUBI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Ingresar Vendedor Dao {0} ", e.getMessage());
        } finally {
            this.cerrarCnx();
        }

    }

    @Override
    public void modificar(Usuario usuario) throws Exception {
        String sql = "update usuario set NOMUSU=?, APEUSU=?, CELUSU=?, ESTUSU=?, DNIUSU=?, EMAUSU=?, DOMUSU=?, CODUBI=? where IDUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCelular());
            ps.setString(4, "A");
            ps.setString(5, usuario.getDni());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getDomicilio());
            ps.setString(8, usuario.getCODUBI());
            ps.setInt(9, usuario.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Modificar Vendedor Dao {0} ", e.getMessage());
        } finally {
            this.cerrarCnx();
        }

    }

    @Override
    public void eliminar(Usuario usuario) throws Exception {
        String sql = "update USUARIO set ESTUSU='I' where IDUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usuario.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Eliminar Vendedor Dao {0} ", e.getMessage());
        } finally {
            this.cerrarCnx();
        }

    }

    @Override
    public void restaurar(Usuario usuario) throws Exception {
        String sql = "update USUARIO set ESTUSU='A' where IDUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, usuario.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Restaurar Vendedor Dao {0} " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }

    }

    @Override
    public List<Usuario> listarTodos(int tipo) throws Exception {
        List<Usuario> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "";
        switch (tipo) {
            case 1:
                sql = "SELECT * FROM vVendedor WHERE ESTUSU='A'";
                break;
            case 2:
                sql = "SELECT * FROM vVendedor WHERE ESTUSU='I'";
                break;
            case 3:
                sql = "SELECT * FROM vVendedor";
                break;
        }
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCodigo(rs.getInt("IDUSU"));
                usu.setNombre(rs.getString("NOMUSU"));
                usu.setApellidos(rs.getString("APEUSU"));
                usu.setCelular(rs.getString("CELUSU"));
                usu.setDni(rs.getString("DNIUSU"));
                usu.setEmail(rs.getString("EMAUSU"));
                usu.setDomicilio(rs.getString("DOMUSU"));
                usu.setCODUBI(rs.getString("CODUBI"));
                usu.setDISUBI(rs.getString("DISUBI"));
                usu.setPROUBI(rs.getString("PROUBI"));
                usu.setDEPUBI(rs.getString("DEPUBI"));
                lista.add(usu);
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al Listar Vendedor Dao {0} ", e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }
}
