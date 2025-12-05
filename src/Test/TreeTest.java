package Test;

import Main.model.tree.AppleTree;
import Main.model.tree.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Main.model.enums.Season;
import Main.model.enums.TreeLifecycle;
import Main.patterns.factory.AppleFactory;
import Main.service.Garden;
import Main.service.TimeManager;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    //Я використовую AppleTree для тестів загальної логіки Tree

    @BeforeEach
    void clearGarden() {
        Garden.getInstance().clear();
    }

    @Test
    @DisplayName("Життєвий цикл: Паросток -> Молоде -> Зріле -> Старе")
    void tree_LifecycleProgression() {
        // 1. Створення (Age 0)
        Tree tree = new AppleFactory().createTree();
        assertEquals(TreeLifecycle.SPROUT, tree.getLifecycle(), "Має бути SPROUT спочатку");

        // 2. Симулюю час до 10 днів (AppleTree стає YOUNG на 5-й день)
        TimeManager tm = new TimeManager();
        tm.registerObserver(tree);

        for(int i=0; i<6; i++) tm.nextDay();
        assertEquals(TreeLifecycle.YOUNG, tree.getLifecycle(), "Має стати YOUNG після 5 днів");

        // 3. Симулюю до 20 днів (AppleTree стає MATURE на 15-й день)
        for(int i=0; i<10; i++) tm.nextDay(); // 16-й день сумарно
        assertEquals(TreeLifecycle.MATURE, tree.getLifecycle(), "Має стати MATURE після 15 днів");

        // 4. Симулюю старість (AppleTree стає OLD на 60-й день)
        // Використовую Builder для прискорення
        Tree oldTree = new AppleTree.Builder().setAge(65).build();
        assertEquals(TreeLifecycle.OLD, oldTree.getLifecycle(), "Має бути OLD");
    }

    @Test
    @DisplayName("Вплив сезонів на здоров'я")
    void tree_HealthChangesBySeason() {
        Tree tree = new AppleTree.Builder().setHealth(50).build();

        // Winter: -5 HP (AppleTree strategy)
        tree.onDayPass(Season.WINTER);
        assertEquals(45, tree.getHealth(), "Зимою здоров'я має падати");

        // Spring: +2 HP
        tree.onDayPass(Season.SPRING);
        assertEquals(47, tree.getHealth(), "Весною здоров'я має рости");
    }

    @Test
    @DisplayName("Смерть дерева при HP <= 0")
    void tree_DiesWhenHealthZero() {
        Tree tree = new AppleTree.Builder().setHealth(5).build();

        // Вбиваю дерево зимою (2 дні по -5 HP = -10 HP сумарно)
        tree.onDayPass(Season.WINTER);
        tree.onDayPass(Season.WINTER);

        assertEquals(0, tree.getHealth(), "Здоров'я не може бути менше 0");
        assertEquals(TreeLifecycle.DEAD, tree.getLifecycle(), "Статус має бути DEAD");
        assertTrue(tree.getFruits().isEmpty(), "Мертве дерево скидає фрукти");
    }
}