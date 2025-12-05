package Main.model.enums;

public enum TreeLifecycle {
    SPROUT(0, 5, "Паросток"),
    YOUNG(5, 20, "Молоде"),
    MATURE(20, 60, "Плодоносне"),
    OLD(60, 100, "Старе"),
    DEAD(100, 999, "Мертве");

    private final int minAge;
    private final int maxAge;
    private final String description;

    TreeLifecycle(int minAge, int maxAge, String description) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.description = description;
    }

    public static TreeLifecycle getStageByAge(int age) {
        for (TreeLifecycle stage : values()) {
            if (age >= stage.minAge && age < stage.maxAge) {
                return stage;
            }
        }
        return DEAD;
    }

    @Override
    public String toString() {
        return description;
    }
}
