package controller;

import dao.PizzaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import lombok.Data;
import model.Pizza;
import service.Reportes;

@Data
@Named(value ="pizzaC")
@SessionScoped
public class PizzaC implements Serializable{
    private Pizza pizza;
    PizzaImpl dao;
    private List<Pizza> lstPizza;
      private int tipo = 1;
    
    public PizzaC() {
        pizza = new Pizza();
        dao = new PizzaImpl();
        lstPizza = new ArrayList<>();
    }
    
    public void registrar() throws Exception {
        try {
            dao.guardar(pizza);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar PizzaC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(pizza);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Modificado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar PrendaC/modificar: " + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(pizza);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar PizzaC/modificar: " + e.getMessage());
        }
    }
    
    public void restaurar() throws Exception {
        try {
            dao.restaurar(pizza);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Restaurado", "Restaurado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurar PizzaC: " + e.getMessage());
        }
    }
    
    public void limpiar() {
        pizza = new Pizza();
    }

    public void listar() {
        try {
            lstPizza = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en listar PizzaC/listar_C " + e.getMessage());
        }
    }
    
    public void reporteVale() throws Exception {
        try {
            Reportes reporte = new Reportes();
            FacesContext facescontext = FacesContext.getCurrentInstance();
            ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
            String root = servletcontext.getRealPath("reportes/Prenda.jasper");
            reporte.ReportePdf(root);
            FacesContext.getCurrentInstance().responseComplete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "REPORTE GENERADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "AL GENERAR REPORTE"));
            System.out.println("Error en PrendaC " + e.getMessage());
        }
    }
    
    @PostConstruct
    public void construir() {
        listar();
    }
    
}
