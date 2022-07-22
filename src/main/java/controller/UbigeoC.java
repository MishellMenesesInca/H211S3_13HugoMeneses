package controller;

import dao.UbigeoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import model.Ubigeo;
import lombok.Data;

@Data
//Notación CDI
@Named(value = "ubigeoC")
//@Dependent
@SessionScoped
public class UbigeoC implements Serializable {

    private Ubigeo ubigeo;
    private UbigeoImpl dao;
    private List<Ubigeo> listadoUbigeo;

    public UbigeoC() {
        ubigeo = new Ubigeo();
        dao = new UbigeoImpl();
        listadoUbigeo = new ArrayList<>();
    }

    public void listar() {
        try {
            listadoUbigeo = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listar ubigeoC " + e.getMessage());
        }
    }

    @PostConstruct
    public void construir() {
        listar();
    }
    
}
