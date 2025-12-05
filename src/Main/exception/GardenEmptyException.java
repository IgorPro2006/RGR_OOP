package Main.exception;

public class GardenEmptyException extends GardenException {
    public GardenEmptyException() {
        super("Сад порожній. Спочатку посадіть дерево.");
    }
}
