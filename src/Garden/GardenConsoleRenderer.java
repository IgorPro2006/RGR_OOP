package Garden;

/**Клас, що відповідає виключно за відображення стану саду в консолі.
 Дотримується "Принципу єдиної відповідальності"**/
public class GardenConsoleRenderer {

    public Garden garden;

    public GardenConsoleRenderer(Garden garden) {
        this.garden = garden;
    }

    //Виводить поточний стан саду в консоль
    public void displayGardenState() {
        /**Тут буде реалізація виведення самого саду, тобто кількість дерев,
         їх види, вік, здоров'я, плоди на деревах та уже зібрані плоди**/
    }
}
