package br.edu.infnet.eliasapi.Expections;

public class AdocaoInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AdocaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
