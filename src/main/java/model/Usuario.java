package model;

import lombok.Data;

@Data
public class Usuario {
    
    private Integer codigo;
    private String nombre;
    private String apellidos;
    private String celular;
    private String estado;
    private String dni;
    private String email;
    private String domicilio;
    private String CODUBI;
    private String DEPUBI;
    private String PROUBI;
    private String DISUBI;

    private Ubigeo ubigeo = new Ubigeo();
}
