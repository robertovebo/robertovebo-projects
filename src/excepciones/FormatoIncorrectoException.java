package excepciones;

public class FormatoIncorrectoException extends RuntimeException {
    public FormatoIncorrectoException(String texto) {
        super(texto);
    }
}
