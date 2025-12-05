package Main.exception;

public class TreeNotFoundException extends GardenException {
    public TreeNotFoundException(int id) {
        super("Дерево з ID " + id + " не знайдено в саду.");
    }
}