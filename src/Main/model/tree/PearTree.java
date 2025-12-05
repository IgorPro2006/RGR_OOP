package Main.model.tree;

import Main.model.otherObject.Fruit;
import Main.model.enums.Season;
import Main.patterns.other.GrowthStrategy;

public class PearTree extends Tree {

    private PearTree(Builder builder) {
        super(builder);
    }

    @Override
    protected void produceFruits() {
        // Груша більш вибаглива
        if (random.nextInt(100) < 25) {
            fruits.add(new Fruit());
        }
    }

    @Override
    public String getType() { return "Pear Tree"; }

    public static class PearGrowthStrategy implements GrowthStrategy {
        @Override
        public int calculateHealthChange(Season season) {
            switch (season) {
                case WINTER: return -8; // Більш чутлива до холоду
                case SPRING: return 3;
                case SUMMER: return 2;
                case AUTUMN: return -1;
                default: return 0;
            }
        }
    }

    public static class Builder extends TreeBuilder<Builder> {
        @Override
        protected Builder self() { return this; }

        @Override
        public PearTree build() {
            if (growthStrategy == null) {
                growthStrategy = new PearGrowthStrategy();
            }
            return new PearTree(this);
        }
    }
}