package model;

import lombok.Data;

@Data

public class VentaDetalle {

    private int IDVENDET;
    private int CANVENDET = 1;
    private int IDVEN;
    private int IDPIZ;
    private Double SUBVENDET;
    private Pizza pizza = new Pizza();
    private Venta venta = new Venta();

    private int codigo;
    private String nombre;
    private String tamaño;
    private Double precio;

    public void setPREPIZ(Double precio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNOMPIZ(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTAMPIZ(String tamaño) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
