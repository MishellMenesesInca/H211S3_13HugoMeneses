package controller;

import dao.UsuarioImpl;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import model.Usuario;
import org.primefaces.PrimeFaces;
import model.Ubigeo;
import lombok.Data;
import service.Reportes;

@Data
@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable {

    private Usuario usuario;
    private UsuarioImpl dao;
    private List<Usuario> lstUsuario;
    private Ubigeo ubigeo;
    private int tipo = 1;

    public UsuarioC() {
        usuario = new Usuario();
        dao = new UsuarioImpl();
        ubigeo = new Ubigeo();
        lstUsuario = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(usuario);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar UsuarioC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(usuario);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar UsuarioC/modificar: " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminar UsuarioC: " + e.getMessage());
        }
    }
    
    public void restaurar() throws Exception {
        try {
            dao.restaurar(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Restaurado", "Restaurado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurar UsuarioC: " + e.getMessage());
        }
    }

    public void limpiar() {
        usuario = new Usuario();
    }
    
    public void listar() {
        try {
            lstUsuario = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en listar UsuarioC/listar_C " + e.getMessage());
        }
    }
    
    public void reporteVen() throws Exception {
        try {
            Reportes reporte = new Reportes();
            FacesContext facescontext = FacesContext.getCurrentInstance();
            ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
            String root = servletcontext.getRealPath("reportes/Vendedor.jasper");
            reporte.ReportePdf(root);
            FacesContext.getCurrentInstance().responseComplete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "REPORTE GENERADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "AL GENERAR REPORTE"));
            System.out.println("Error en UsusarioC " + e.getMessage());
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
    }

}
