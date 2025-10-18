package Garden;

public class Fertilizer {

    // Наприклад, "для яблунь" або "азотне"
    public String fertilizerType;
    public double healthBoost;

    public Fertilizer(String fertilizerType, double healthBoost) {
        this.fertilizerType = fertilizerType;
        this.healthBoost = healthBoost;
    }

    public String getFertilizerType() {
        return fertilizerType;
    }

    public double getHealthBoost() {
        return healthBoost;
    }
}
