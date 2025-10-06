package br.edu.infnet.eliasapi.Expections;

public class FuncionarioInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FuncionarioInvalidoException(String mensagem) {
        super(mensagem);
    }
}
