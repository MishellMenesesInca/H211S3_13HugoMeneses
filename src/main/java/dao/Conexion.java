package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

@Data

public class Conexion {

    private static Connection cnx = null;
    
    public static Connection conectar() throws Exception {
        try {
            String user = "sa";
            String pwd = "@abc123@";
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=bdPizzaHut";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.out.println("Error de Conexión/Conectar " + e.getMessage());
        }
        return cnx;
    }

   


    public void cerrarCnx() throws Exception {
        if (cnx != null) {
            cnx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        try {
            if (cnx != null) {
                System.out.println("CONEXIÓN EXITOSA");
                JOptionPane.showMessageDialog(null, "CONEXIÓN EXITOSA", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("SIN CONEXIÓN REVISA");
                JOptionPane.showMessageDialog(null, "SIN CONEXIÓN REVISA", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error en " + e.getMessage());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

   

}
