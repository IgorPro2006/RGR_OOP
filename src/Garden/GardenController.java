package Garden;

/**Клас, що реалізує шаблон "Контролер" (Controller).
 Приймає вхідні дані від користувача та делегує дії Садівнику**/
public class GardenController {

    // Контролер делегує роботу виконавцю (Gardener) [cite: 41]
    public Gardener gardener;
    public TreeFactory treeFactory;

    public GardenController(Gardener gardener, TreeFactory treeFactory) {
        this.gardener = gardener;
        this.treeFactory = treeFactory;
    }

    /**Обробляє команду, отриману від користувача (наприклад, з консолі).
     Команда буде подана у вигляді рядка (наприклад, "plant apple").**/
    public void processUserInput(String command) {
        /** Тут буде логіка обробки та проведення отриманого запиту з консолі.
         * Запити будуть відповідати назвам деяких методів або функцій, наприклад addTree, removeTree**/
    }
}