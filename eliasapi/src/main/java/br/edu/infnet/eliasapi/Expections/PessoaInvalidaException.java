package br.edu.infnet.eliasapi.Expections;

public class PessoaInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PessoaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
