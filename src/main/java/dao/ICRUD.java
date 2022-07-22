package dao;

import java.util.List;

public interface ICRUD<Generic> {

    void guardar(Generic obj) throws Exception;

    void modificar(Generic obj) throws Exception;

    void eliminar(Generic obj) throws Exception;
    
    void restaurar(Generic obj) throws Exception;

    List<Generic> listarTodos(int tipo) throws Exception;
}
