package model;

/**Пори року для симуляції**/
public enum Season {
    WINTER, SPRING, SUMMER, AUTUMN;

    public Season next() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
