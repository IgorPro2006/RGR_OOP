package patterns;

import model.Season;

public interface GrowthStrategy {
    int calculateHealthChange(Season season);
}