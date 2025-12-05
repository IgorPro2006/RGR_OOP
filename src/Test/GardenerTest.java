package Test;

import Main.model.otherObject.Fruit;
import Main.model.otherObject.Gardener;
import Main.model.tree.AppleTree;
import Main.model.tree.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Main.patterns.factory.AppleFactory;
import Main.service.Garden;
import Main.service.TimeManager;

import static org.junit.jupiter.api.Assertions.*;

class GardenerTest {

    private Gardener gardener;
    private Garden garden;
    private TimeManager timeManager;

    @BeforeEach
    void setUp() {
        // Очищую Singleton перед кожним тестом, щоб мати чистий стан
        garden = Garden.getInstance();
        garden.clear();

        gardener = new Gardener();
        timeManager = new TimeManager();
    }

    @Test
    @DisplayName("Посадка дерева: Успіх (є енергія)")
    void plantTree_WithEnoughEnergy_ShouldAddTreeAndReduceEnergy() {
        int initialEnergy = gardener.getEnergy(); // 100
        int expectedEnergy = initialEnergy - 20;

        gardener.plantTree(new AppleFactory(), timeManager);

        assertEquals(expectedEnergy, gardener.getEnergy(), "Енергія має зменшитися на 20");
        assertEquals(1, garden.getTrees().size(), "В саду має з'явитися 1 дерево");
        assertNotNull(garden.getTrees().get(0), "Дерево не може бути null");
    }

    @Test
    @DisplayName("Посадка дерева: Провал (мало енергії)")
    void plantTree_WithLowEnergy_ShouldNotPlant() {
        for(int i=0; i<5; i++) gardener.plantTree(new AppleFactory(), timeManager);

        int energyBefore = gardener.getEnergy();

        gardener.plantTree(new AppleFactory(), timeManager);

        assertEquals(energyBefore, gardener.getEnergy(), "Енергія не повинна змінитись");
        assertEquals(5, garden.getTrees().size(), "Кількість дерев не повинна зрости");
    }

    @Test
    @DisplayName("Збір врожаю: Успіх (є стиглі фрукти)")
    void harvest_WithRipeFruits_ShouldCollectAndReduceEnergy() {
        Tree tree = new AppleTree.Builder().setAge(25).setHealth(100).build();
        garden.addTree(tree);

        Fruit fruit = new Fruit();
        fruit.mature();
        tree.getFruits().add(fruit);

        int initialEnergy = gardener.getEnergy();

        gardener.harvest();

        assertEquals(initialEnergy - 25, gardener.getEnergy(), "Енергія має зменшитися на 25");
        assertEquals(1, gardener.getCollectedFruits(), "Має бути зібрано 1 фрукт");
        assertTrue(tree.getFruits().isEmpty(), "Фрукт має зникнути з дерева");
    }

    @Test
    @DisplayName("Збір врожаю: Scan Phase (немає стиглих фруктів)")
    void harvest_NoRipeFruits_ShouldNotReduceEnergy() {
        Tree tree = new AppleTree.Builder().setAge(5).build(); // Молоде дерево без фруктів
        garden.addTree(tree);

        int initialEnergy = gardener.getEnergy();

        gardener.harvest();

        assertEquals(initialEnergy, gardener.getEnergy(), "Енергія НЕ має зменшитися, бо збору не було");
        assertEquals(0, gardener.getCollectedFruits(), "Кошик має бути порожнім");
    }

    @Test
    @DisplayName("Збір врожаю: Провал (мало енергії)")
    void harvest_LowEnergy_ShouldFail() {
        gardener.plantTree(new AppleFactory(), timeManager);
        gardener.plantTree(new AppleFactory(), timeManager);
        gardener.plantTree(new AppleFactory(), timeManager);
        gardener.plantTree(new AppleFactory(), timeManager);

        gardener.harvest();

        assertEquals(20, gardener.getEnergy());
    }

    @Test
    @DisplayName("Удобрення: Успіх")
    void fertilizeTree_Success() {
        Tree tree = new AppleTree.Builder().setHealth(50).build();
        garden.addTree(tree);

        int initialFertilizer = garden.getFertilizerCount();
        int initialEnergy = gardener.getEnergy();

        gardener.fertilizeTree(tree);

        assertEquals(70, tree.getHealth(), "Здоров'я дерева має зрости на 20");
        assertEquals(initialFertilizer - 1, garden.getFertilizerCount(), "Добрива мають списатись");
        assertEquals(initialEnergy - 10, gardener.getEnergy(), "Енергія має списатись (-10)");
    }

    @Test
    @DisplayName("Відпочинок: Відновлення енергії")
    void rest_ShouldRestoreEnergyTo100() {
        gardener.plantTree(new AppleFactory(), timeManager); // energy = 80

        gardener.rest();

        assertEquals(100, gardener.getEnergy());
    }
}
