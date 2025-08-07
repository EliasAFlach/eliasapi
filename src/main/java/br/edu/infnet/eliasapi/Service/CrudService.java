package br.edu.infnet.eliasapi.Service;

import java.util.List;

public interface CrudService <T, ID> {

    T inserir(T objeto);
    void deletar(ID objeto);
    T buscarPorId(ID objeto);
    List<T> buscarTodos();

}
