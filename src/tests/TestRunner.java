package tests;

import model.*;
import patterns.*;
import service.*;

/**Клас для запуску тестів.Емулює роботу JUnit**/
public class TestRunner {

    private int testsPassed = 0;
    private int testsFailed = 0;

    public void runAllTests() {
        System.out.println("\n=== ЗАПУСК UNIT ТЕСТІВ ===");

        runTest("Singleton Garden Test", this::testGardenSingleton);
        runTest("Tree Factory Test", this::testTreeFactory);
        runTest("Tree Growth Logic Test", this::testTreeGrowth);
        runTest("Gardener Energy Test", this::testGardenerEnergy);
        runTest("Fruit Ripening Test", this::testFruitRipening);

        System.out.println("\n=== РЕЗУЛЬТАТИ ТЕСТУВАННЯ ===");
        System.out.println("Пройдено: " + testsPassed);
        System.out.println("Провалено: " + testsFailed);
        if (testsFailed == 0) System.out.println("Всі системи працюють стабільно.");
        else System.out.println("Знайдено помилки в логіці!");
    }

    private void runTest(String testName, Runnable test) {
        System.out.print("TEST: " + testName + "... ");
        try {
            // Очистка стану перед кожним тестом
            Garden.getInstance().clear();
            test.run();
            System.out.println("OK");
            testsPassed++;
        } catch (RuntimeException e) {
            System.out.println("FAILED (" + e.getMessage() + ")");
            testsFailed++;
        } catch (Exception e) {
            System.out.println("ERROR (" + e.getMessage() + ")");
            testsFailed++;
        }
    }

    // --- ТЕСТОВІ СЦЕНАРІЇ ---

    private void testGardenSingleton() {
        Garden g1 = Garden.getInstance();
        Garden g2 = Garden.getInstance();
        if (g1 != g2) throw new RuntimeException("Garden не є Singleton!");

        g1.addFertilizer(5);
        if (g2.getFertilizerCount() != 10) throw new RuntimeException("Стан Singleton не синхронізований!");
    }

    private void testTreeFactory() {
        TreeFactory appleFactory = new AppleFactory();
        Tree appleTree = appleFactory.createTree();

        if (!(appleTree instanceof AppleTree)) throw new RuntimeException("Фабрика створила невірний тип об'єкта");
        if (appleTree.getAge() != 0) throw new RuntimeException("Нове дерево має мати вік 0");
    }

    private void testTreeGrowth() {
        // Використовуємо Builder напряму для тесту
        Tree tree = new AppleTree.Builder().setAge(0).setHealth(100).build();
        TimeManager tm = new TimeManager();
        tm.registerObserver(tree);

        // Симулюємо 10 днів весни
        for (int i = 0; i < 10; i++) {
            tm.nextDay();
        }

        if (tree.getAge() != 10) throw new RuntimeException("Дерево не старіє коректно. Очікувано 10, є " + tree.getAge());
        if (tree.getLifecycle() != TreeLifecycle.YOUNG) throw new RuntimeException("Дерево має перейти в стадію YOUNG");
    }

    private void testGardenerEnergy() {
        Gardener g = new Gardener();
        TimeManager tm = new TimeManager();

        // Посадка дерева коштує 20
        g.plantTree(new AppleFactory(), tm);
        if (g.getEnergy() != 80) throw new RuntimeException("Енергія не списалась. Очікувано 80, є " + g.getEnergy());

        // Саджаємо до виснаження
        g.plantTree(new AppleFactory(), tm);
        g.plantTree(new AppleFactory(), tm);
        g.plantTree(new AppleFactory(), tm);
        g.plantTree(new AppleFactory(), tm); // Тут має бути 0 енергії

        // Спроба посадити без енергії
        int treesBefore = Garden.getInstance().getTrees().size();
        g.plantTree(new AppleFactory(), tm);
        int treesAfter = Garden.getInstance().getTrees().size();

        if (treesBefore != treesAfter) throw new RuntimeException("Садівник працює без енергії!");
    }

    private void testFruitRipening() {
        // Створюємо зріле дерево
        Tree tree = new AppleTree.Builder().setAge(25).build();
        TimeManager tm = new TimeManager();
        tm.registerObserver(tree);

        // Прокручуємо час літом, щоб з'явилися фрукти
        for (int i = 0; i < 20; i++) {
            tm.nextDay();
        }

        if (tree.getFruits().isEmpty()) {
            // Це рандом, але за 20 днів яблуня точно має дати плід (шанс 40%)
            // Якщо не дала - можливо, помилка в логіці росту
            throw new RuntimeException("Зріле дерево не дало плодів за 20 днів літа.");
        }

        Fruit f = tree.getFruits().get(0);
        if (f.getState() == FruitState.GREEN) {
            f.mature();
            if (f.getState() != FruitState.RIPE) throw new RuntimeException("Фрукт не дозрів.");
        }
    }
}