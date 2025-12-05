package Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Main.model.otherObject.Fruit;
import Main.model.enums.FruitState;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    @DisplayName("Фрукт: Еволюція стану")
    void fruit_StateProgression() {
        Fruit fruit = new Fruit();

        // 1. Початок
        assertEquals(FruitState.GREEN, fruit.getState(), "Новий фрукт має бути зеленим");

        // 2. Дозрівання
        fruit.mature();
        assertEquals(FruitState.RIPE, fruit.getState(), "Після mature() зелений стає стиглим");

        // 3. Псування (має ймовірність, тому тестуємо можливість)
        // Ми не можемо гарантувати гниття за 1 виклик через Random(),
        // але можемо перевірити, що стан валідний (або RIPE, або ROTTEN)
        fruit.mature();
        assertTrue(fruit.getState() == FruitState.RIPE || fruit.getState() == FruitState.ROTTEN,
                "Стиглий фрукт або залишається стиглим, або гниє");
    }
}
