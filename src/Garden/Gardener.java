package Garden;

import java.util.ArrayList;
import java.util.List;

/**Активний об'єкт, що виконує дії в саду. Відповідає виключно за виконання дій.
 Діє як фасад для складних операцій, делегованих контролером**/
public class Gardener {

    // Садівник потребує доступу до саду, щоб в ньому працювати.
    public Garden garden;

    public Gardener(Garden garden) {
        this.garden = garden;
    }

    //Садить нове дерево в саду.
    public void plantTree(FruitTree tree) {
        if (tree != null) {
             tree.plant();
             this.garden.addTree(tree);
        }
    }

    //Застосовує добриво до дерева.
    public void fertilizeTree(Fertilizable tree, Fertilizer fertilizer) {
        if (tree != null && fertilizer != null) {
            tree.applyFertilizer(fertilizer);
        }
    }

    /**Збирає врожай з дерева. return Список зібраних плодів.**/

    public List<Fruit> harvestFromTree(Harvestable tree) {
        if (tree != null) {
            return tree.harvest();
        }
        return new ArrayList<>();
    }

    //Видаляє дерево з саду.
    public void removeTree(Removable tree) {
        if (tree instanceof FruitTree) {
             tree.remove();
             this.garden.removeTree((FruitTree) tree);
        }
    }
}