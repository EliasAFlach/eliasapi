package br.edu.infnet.eliasapi.Service;

import java.util.List;

public interface CrudService <T, ID> {

    T inserir(T objeto);
    T atualizar(T objeto);
    T inativar(ID id);
    void deletar(ID id);
    T buscarPorId(ID id);
    List<T> buscarTodos();

}
