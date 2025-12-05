package Main.patterns.other;

import Main.model.enums.Season;

public interface GrowthStrategy {
    int calculateHealthChange(Season season);
}