package model;

import java.util.Date;
import java.util.GregorianCalendar;
import lombok.Data;

@Data
public class Venta {

    private int IDVEN;
    private Date FECVEN = GregorianCalendar.getInstance().getTime();
    private int IDUSU;
    private int IDCLI;
    private Double TOTVEN;
    private Cliente cliente = new Cliente();
    private Usuario usuario = new Usuario();

}
