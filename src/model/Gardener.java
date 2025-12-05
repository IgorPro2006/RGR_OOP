package model;


import patterns.TreeFactory;
import service.Garden;
import service.TimeManager;

import java.util.Iterator;

public class Gardener {
    private int energy;
    private int collectedFruits;
    private Garden garden;

    public Gardener() {
        this.energy = 100;
        this.collectedFruits = 0;
        this.garden = Garden.getInstance();
    }

    public void plantTree(TreeFactory factory, TimeManager timeManager) {
        if (energy >= 20) {
            Tree tree = factory.createTree();
            garden.addTree(tree);
            timeManager.registerObserver(tree); // Підписуємо дерево на час
            energy -= 20;
            System.out.println("Садівник посадив  + tree.getType() +  (ID: " + tree.getId() + ")");
        } else {
            System.out.println("Недостатньо енергії для посадки!");
        }
    }

    /**Пересаджує існуюче дерево.
     * Дерево отримує стрес (-10 HP), садівник втрачає 30 енергії**/
    public void transplantTree(Tree tree, TimeManager timeManager) {
        // Пересадка: старе дерево видаляється, нове (того ж типу) саджається, але воно трохи "хворіє"
        if (energy >= 30) {
            System.out.println("Пересадка дерева ID: " + tree.getId());
            tree.heal(-10);
            energy -= 30;
            System.out.println("Дерево успішно пересаджено (але трохи похворіло від стресу).");
        } else {
            System.out.println("Недостатньо енергії для пересадки.");
        }
    }

    /**Удобрює дерево. Вартість: 10 енергії + 1 мішок добрив**/
    public void fertilizeTree(Tree tree) {
        if (energy >= 10 && garden.getFertilizerCount() > 0) {
            if (garden.useFertilizer()) {
                tree.heal(20);
                energy -= 10;
                System.out.println("Дерево " + tree.getId() + " удобрено.");
            }
        } else {
            System.out.println("Немає добрив або енергії.");
        }
    }

    /**Збирає врожай. Вартість: 25 енергії (тільки якщо є що збирати)**/
    public void harvest() {
        if (energy < 25) {
            System.out.println("Недостатньо енергії для збору врожаю.");
            return;
        }

        // Садівник оглядає сад. Якщо немає стиглих фруктів - він не працює і не витрачає енергію.
        boolean hasRipeFruits = false;

        if (garden.getTrees().isEmpty()) {
            System.out.println("Сад порожній. Нічого збирати.");
            return;
        }

        // Шукаємо хоча б один стиглий фрукт
        for (Tree tree : garden.getTrees()) {
            // Пропускаємо мертві дерева або ті, що не мають фруктів
            if (tree.getLifecycle() == TreeLifecycle.DEAD || tree.getFruits().isEmpty()) {
                continue;
            }

            for (Fruit fruit : tree.getFruits()) {
                if (fruit.getState() == FruitState.RIPE) {
                    hasRipeFruits = true;
                    break;
                }
            }
            if (hasRipeFruits) break;
        }

        if (!hasRipeFruits) {
            System.out.println("Огляд завершено: У саду немає стиглих фруктів. Енергія збережена.");
            return;
        }


        int collectedNow = 0;

        // Використання Ітератора (Pattern Iterator) для безпечного видалення елементів зі списку
        for (Tree tree : garden.getTrees()) {
            if (tree.getLifecycle() == TreeLifecycle.DEAD) continue;

            Iterator<Fruit> fruitIterator = tree.getFruits().iterator();
            while (fruitIterator.hasNext()) {
                Fruit fruit = fruitIterator.next();
                if (fruit.getState() == FruitState.RIPE) {
                    collectedNow++;
                    fruitIterator.remove(); // Зриваємо фрукт
                }
            }
        }

        this.collectedFruits += collectedNow;
        energy -= 25;
        System.out.println("Садівник зібрав " + collectedNow + " фруктів.");

        // Бонус: обмін фруктів на добрива
        if (collectedNow >= 5) {
            garden.addFertilizer(1);
            System.out.println("Бонус: Отримано мішок добрив за гарний врожай!");
        }
    }

    public void rest() {
        rest(true);
    }

    public void rest(boolean showMessage) {
        this.energy = 100;
        if (showMessage) {
            System.out.println("Садівник відпочив. Енергія відновлена.");
        }
    }

    public int getEnergy() { return energy; }
    public int getCollectedFruits() { return collectedFruits; }
}
