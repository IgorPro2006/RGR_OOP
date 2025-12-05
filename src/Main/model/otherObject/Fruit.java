package Main.model.otherObject;

import Main.model.enums.FruitState;

import java.util.Random;

public class Fruit {
    private FruitState state;
    private final Random random = new Random();

    public Fruit() {
        this.state = FruitState.GREEN;
    }

    public void mature() {
        if (state == FruitState.GREEN) {
            state = FruitState.RIPE;
        } else if (state == FruitState.RIPE) {
            // 30% шанс зіпсуватися кожного дня
            if (random.nextDouble() < 0.3) {
                state = FruitState.ROTTEN;
            }
        }
    }

    public FruitState getState() {
        return state;
    }
}
