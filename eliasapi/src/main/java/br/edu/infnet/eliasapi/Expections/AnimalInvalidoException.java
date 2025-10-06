package br.edu.infnet.eliasapi.Expections;

public class AnimalInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AnimalInvalidoException(String mensagem) {
        super(mensagem);
    }
}
