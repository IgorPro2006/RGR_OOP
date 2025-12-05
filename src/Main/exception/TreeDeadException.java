package Main.exception;

public class TreeDeadException extends GardenException {
    public TreeDeadException(int id) {
        super("Дерево ID " + id + " мертве. З ним не можна взаємодіяти.");
    }
}