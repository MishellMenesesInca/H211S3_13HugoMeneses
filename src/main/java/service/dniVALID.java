package service;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "dniVALID")
public class dniVALID implements Validator {

    public static boolean DNIDuplicadoCliente(String dni) {
        try {
            PreparedStatement ps1 = Conexion.conectar().prepareStatement("SELECT DNICLI FROM CLIENTE WHERE DNICLI = '" + dni + "'");
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en dni repetido" + e.getMessage());
        }
        return false;
    }

    public static boolean DNIDuplicadoVendedor(String dniE) {
        try {
            PreparedStatement ps1 = Conexion.conectar().prepareStatement("SELECT DNIUSU FROM USUARIO WHERE DNIUSU = '" + dniE + "'");
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en dni repetido" + e.getMessage());
        }
        return false;
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String numero = o.toString().trim();
        if (numero.length() != 0 && numero.length() < 8) {
            String plantilla = "^\\d\\d\\d\\d\\d\\d\\d\\d$";
            boolean val = Pattern.matches(plantilla, numero);
            if (!val) {
                throw new ValidatorException(new FacesMessage("Formato inválido ######## carácteres"));
            }
        }
        String dni = (String) o;
        if (DNIDuplicadoCliente(dni) == true) {
            throw new ValidatorException(new FacesMessage("Este DNI ya existe"));
        }
        
         String dniE = (String) o;
        if (DNIDuplicadoVendedor(dniE) == true) {
            throw new ValidatorException(new FacesMessage("Este DNI ya existe"));
        }
    }

}
