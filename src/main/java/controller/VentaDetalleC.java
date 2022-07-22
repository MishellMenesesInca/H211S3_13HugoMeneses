package controller;

import dao.VentaDetalleImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import model.Venta;
import model.Pizza;
import model.VentaDetalle;
import lombok.Data;
import service.Reportes;

@Data

@Named(value = "ventadetalleC")
@SessionScoped
public class VentaDetalleC implements Serializable {

    private Venta venta;
    private VentaDetalle ventadetalle;
    private Pizza pizza;
    private VentaDetalleImpl dao;
    private List<Venta> listaVenta;
    private List<VentaDetalle> listadoVentaDetalle;
    private List<Pizza> listadoPizza;

    public VentaDetalleC() {
        venta = new Venta();
        pizza = new Pizza();
        ventadetalle = new VentaDetalle();
        dao = new VentaDetalleImpl();
        listaVenta = new ArrayList<>();
        listadoVentaDetalle = new ArrayList<>();
        listadoPizza = new ArrayList<>();
    }

    public void aumentarFila() {
        try {
            VentaDetalle ventadet = dao.aumentarFila(ventadetalle.getPizza().getCodigo());
            ventadet.setIDPIZ(this.ventadetalle.getPizza().getCodigo());
            ventadet.setCANVENDET(this.ventadetalle.getCANVENDET());
            ventadet.setPREPIZ(ventadet.getPizza().getPrecio());  //extra
            ventadet.setSUBVENDET(ventadet.getPizza().getPrecio() * this.ventadetalle.getCANVENDET());
            ventadet.setNOMPIZ(ventadet.getPizza().getNombre());
            ventadet.setTAMPIZ(ventadet.getPizza().getTamaño());   
            this.listadoVentaDetalle.add(ventadet);
            ventadetalle = new VentaDetalle();
            for (VentaDetalle ventaDetalle : listadoVentaDetalle) {
                System.out.println(ventaDetalle);
//                System.out.println("ID PRODUC = " + ventaDetalle.getPrenda().getCodigo());
//                    System.out.println("ID PRODUC = " + ventaDetalle.getCANVENDET());
//                        System.out.println("ID PRODUC = " + ventaDetalle.getSUBVENDET());
            }
            sumar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Prenda Agregada"));
        } catch (Exception e) {
            System.out.println("Error en addFila_C " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void eliminarFila(VentaDetalle ventadetalle) {
        try {
            listadoVentaDetalle.remove(ventadetalle);
            sumar();
        } catch (Exception e) {
            System.out.println("Error en eliminarFila_C " + e.getMessage());
        }
    }

    public void registrarVenta() {
        try {
            dao.registrarVenta(venta);
            int idven = dao.ultimoIdVenta();
            dao.registrarVentaDetalle(listadoVentaDetalle, idven);
            listadoVentaDetalle.clear();
            listarVenta();
            venta = new Venta();
        } catch (Exception e) {
            System.out.println("Error en registrarVenta_C " + e.getMessage());
        }
    }

    public void limpiar() {
        this.ventadetalle = new VentaDetalle();
        this.venta = new Venta();
    }

    public void anular() throws Exception {
        limpiar();
        listadoVentaDetalle.clear();
    }

    //LISTA DE VENTAS
    public void listarVenta() {
        try {
            listaVenta = dao.listarVenta();
        } catch (Exception e) {
            System.out.println("Error en listarVenta_C " + e.getMessage());
        }
    }

    public void sumar() {
        double precioTotal = 0.0;
        for (VentaDetalle ventaDetalle : listadoVentaDetalle) {
            precioTotal += ventaDetalle.getSUBVENDET();
        }
        System.out.println(precioTotal);
        venta.setTOTVEN(precioTotal);
    }
    
    public void reporteVenta() throws Exception {
        try {
            Reportes reporte = new Reportes();
            FacesContext facescontext = FacesContext.getCurrentInstance();
            ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
            String root = servletcontext.getRealPath("reportes/Venta.jasper");
            reporte.ReportePdf(root);
            FacesContext.getCurrentInstance().responseComplete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "REPORTE GENERADO"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "AL GENERAR REPORTE"));
            System.out.println("Error en VentaC " + e.getMessage());
        }
    }
    

    @PostConstruct
    public void construir() {
        listarVenta();
    }

}
