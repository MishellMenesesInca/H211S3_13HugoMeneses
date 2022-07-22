package model;

import java.util.Date;
import java.util.GregorianCalendar;
import lombok.Data;

@Data
public class Compra {

    private int IDCOM;
    private Date FECCOM = GregorianCalendar.getInstance().getTime();
    private String TIPCOM;
    private String METCOM;
    private int IDPRO;
    private Double TOTCOM;
    private Prenda prenda = new Prenda();
   private Proveedor proveedor = new Proveedor();

}