package Garden;

public abstract class Fruit {
    //Перелік можливих станів плоду
    public enum FruitState {
        GREEN,
        RIPE,
        SPOILED
    }

    protected FruitState state;

    public FruitState getState() {
        return state;
    }

    //Змінює стан на "зелений"
    public Fruit() {
        this.state = FruitState.GREEN;
    }

    //Змінює стан на "стиглий"
    public void ripen() {
        this.state = FruitState.RIPE;
    }

    //Змінює стан на "зіпсований".
    public void spoil() {
        this.state = FruitState.SPOILED;
    }
}
