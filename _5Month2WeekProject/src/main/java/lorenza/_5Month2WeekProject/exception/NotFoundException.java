package lorenza._5Month2WeekProject.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID uuid) {
        super("Elemento con uuid " + uuid + " non trovato!");
    }
}