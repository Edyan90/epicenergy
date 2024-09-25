package Epicode.epicenergy.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("La risorsa con id " + id + " non e' stata trovata!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}

