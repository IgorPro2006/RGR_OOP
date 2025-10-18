package Garden;

public interface FruitTree extends Plantable, Harvestable, Removable, Fertilizable {
    /**Буде моделювати процес росту дерев **/
    void grow();


    /**Буде перевіряти в майбутньому, чи дозріли плоди на дереві.
     true, якщо є стиглі плоди, інакше false.
     **/
    boolean areFruitsRipe();

    /**Геттери для властивостей дерева**/
    int getAge();
    double getHealth();
    String getType();
}
