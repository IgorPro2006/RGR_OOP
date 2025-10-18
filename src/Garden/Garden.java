package Garden;

import java.util.List;
import java.util.ArrayList;

/**Головний контейнер, що керує деревами.Відповідає за управління колекцією дерев.
 Є "Інформаційним експертом" (Information Expert) щодо кількості дерев**/
public class Garden {

    // Сад залежить від абстракції FruitTree, а не від конкретних класів
    public List<FruitTree> trees;

    public Garden() {
        this.trees = new ArrayList<>();
    }

    //Додає дерево до саду.
    public void addTree(FruitTree tree) {
        this.trees.add(tree);
    }

    //Видаляє дерево з саду.
    public void removeTree(FruitTree tree) {
        this.trees.remove(tree);
    }

    //Метод "Інформаційного експерта"
    public int getTreeCount() {
        return this.trees.size();
    }

    //Повертає список дерев (наприклад, для рендерингу).
    public List<FruitTree> getTrees() {
        return this.trees;
    }

    //Симулює крок часу для всього саду (ріст всіх дерев)
    public void simulateTimeStep() {
        for (FruitTree tree : trees) {
            tree.grow();
        }
    }
}
