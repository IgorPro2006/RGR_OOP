package Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Main.model.tree.AppleTree;
import Main.model.tree.PearTree;
import Main.model.tree.Tree;
import Main.patterns.factory.AppleFactory;
import Main.patterns.factory.PearFactory;
import Main.patterns.factory.TreeFactory;
import Main.service.Garden;

import static org.junit.jupiter.api.Assertions.*;

class ArchitectureTest {

    @Test
    @DisplayName("Singleton Garden: Тільки один екземпляр")
    void garden_IsSingleton() {
        Garden ref1 = Garden.getInstance();
        Garden ref2 = Garden.getInstance();

        assertNotNull(ref1);
        assertSame(ref1, ref2, "Посилання мають вказувати на один і той самий об'єкт");

        // Перевірка спільного стану
        ref1.clear();
        ref1.addFertilizer(10);
        assertEquals(15, ref2.getFertilizerCount(), "Зміна в ref1 має бути видна в ref2 (5 base + 10 added)");
    }

    @Test
    @DisplayName("Factory Method: Створення правильних типів")
    void factories_CreateCorrectTypes() {
        TreeFactory appleFactory = new AppleFactory();
        TreeFactory pearFactory = new PearFactory();

        Tree appleTree = appleFactory.createTree();
        Tree pearTree = pearFactory.createTree();

        assertTrue(appleTree instanceof AppleTree, "AppleFactory має створювати AppleTree");
        assertTrue(pearTree instanceof PearTree, "PearFactory має створювати PearTree");

        assertNotEquals(appleTree.getType(), pearTree.getType(), "Типи дерев мають відрізнятися");
    }
}