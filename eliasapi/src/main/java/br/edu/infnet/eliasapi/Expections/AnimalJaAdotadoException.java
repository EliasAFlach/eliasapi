package br.edu.infnet.eliasapi.Expections;

public class AnimalJaAdotadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AnimalJaAdotadoException(String mensagem) {
        super(mensagem);
    }
}
