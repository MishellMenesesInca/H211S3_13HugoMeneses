package model;

import java.util.Date;
import java.util.GregorianCalendar;
import lombok.Data;

@Data

public class CompraDetalle {

    private int IDCOMDET;
    private int CANCOMDET = 1;
    private int IDCOM;
    private int IDPRE;
    private Double SUBCOMDET;
    private Prenda prenda = new Prenda();
    private Compra compra = new Compra();

    private String NOMPRE;
    private String TALLPRE;
    private String MARPRE;
    private Double PRECPRE;
    private String COLPRE;
    private int STOPRE;
    private int IDPRO;

}
