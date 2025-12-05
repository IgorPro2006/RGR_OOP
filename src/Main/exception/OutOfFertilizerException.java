package Main.exception;

public class OutOfFertilizerException extends GardenException {
    public OutOfFertilizerException() {
        super("Закінчилися добрива! Зберіть більше фруктів, щоб отримати нові.");
    }
}