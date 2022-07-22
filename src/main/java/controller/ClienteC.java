package controller;

import dao.ClienteImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import model.Cliente;
import model.Ubigeo;
import lombok.Data;
import service.Reportes;

@Data
@Named(value = "clienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cliente;
    ClienteImpl dao;
    private Ubigeo ubigeo;
    private List<Cliente> lstCliente;
    private int tipo = 1;

    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
        lstCliente = new ArrayList<>();
        ubigeo = new Ubigeo();
    }

    public void registrar() throws Exception {
        try {
            dao.guardar(cliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar ClienteC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(cliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar ClienteC/modificar: " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(cliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar ClienteC/eliminar: " + e.getMessage());
        }
    }

    public void restaurar() throws Exception {
        try {
            dao.restaurar(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Restaurado", "Restaurado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurar ClienteC: " + e.getMessage());
        }
    }

    public void limpiar() {
        cliente = new Cliente();
    }

    public void listar() {
        try {
            lstCliente = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en listar cliente/listar_C " + e.getMessage());
        }
    }

    public void reporteCli() throws Exception {
        try {
            Reportes reporte = new Reportes();
            FacesContext facescontext = FacesContext.getCurrentInstance();
            ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
            String root = servletcontext.getRealPath("reportes/Client.jasper");
            reporte.ReportePdf(root);
            FacesContext.getCurrentInstance().responseComplete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "REPORTE GENERADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "AL GENERAR REPORTE"));
            System.out.println("Error en ClienteC " + e.getMessage());
        }
    }

    @PostConstruct
    public void construir() {
        listar();
    }

}
