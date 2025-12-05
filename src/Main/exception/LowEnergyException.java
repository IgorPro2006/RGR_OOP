package Main.exception;

public class LowEnergyException extends GardenException {
    public LowEnergyException(int required, int current) {
        super("Недостатньо енергії! Потрібно: " + required + ", а у вас є: " + current);
    }
}