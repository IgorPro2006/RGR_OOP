package Main.model.tree;

import Main.model.otherObject.Fruit;
import Main.model.enums.Season;
import Main.patterns.other.GrowthStrategy;

public class AppleTree extends Tree {

    private AppleTree(Builder builder) {
        super(builder);
    }

    @Override
    protected void produceFruits() {
        // Яблуня дає плоди стабільно
        if (random.nextInt(100) < 40) { // 40% шанс
            fruits.add(new Fruit());
        }
    }

    @Override
    public String getType() { return "Apple Tree"; }

    // Стандартна стратегія для яблуні
    public static class AppleGrowthStrategy implements GrowthStrategy {
        @Override
        public int calculateHealthChange(Season season) {
            switch (season) {
                case WINTER: return -5;
                case SPRING: return 2;
                case SUMMER: return 1;
                case AUTUMN: return 0;
                default: return 0;
            }
        }
    }

    public static class Builder extends TreeBuilder<Builder> {
        @Override
        protected Builder self() { return this; }

        @Override
        public AppleTree build() {
            if (growthStrategy == null) {
                growthStrategy = new AppleGrowthStrategy();
            }
            return new AppleTree(this);
        }
    }
}