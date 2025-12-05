package Main.exception;

public class NoRipeFruitException extends GardenException {
    public NoRipeFruitException() {
        super("У саду немає стиглих фруктів для збору. Зачекайте або пропустіть дні.");
    }
}