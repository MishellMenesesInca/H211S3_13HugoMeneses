package model;

import lombok.Data;

@Data

public class Prenda {
    private int codigo;
    private String nombre;
    private String talla;
    private Double precio;
    private int stock;
    private int categoria;
    private int IDCAT;
    private String NOMCAT;
    private String SETCAT;
    private String estado;
    private String color;
    private String marca;
    private int IDPRO;
    private String NOMPRO;
    private String NCOPRO;
}
