package Garden;

/**Клас, що керує плином часу в симуляції.
 Відповідає за реалізацію основної логіки симуляції**/
public class SimulationLoop {
    public Garden garden;
    public Gardener gardener; // Можливо, для автоматичних дій
    public int currentDay;

    public SimulationLoop(Garden garden, Gardener gardener) {
        this.garden = garden;
        this.gardener = gardener;
        this.currentDay = 0;
    }

    //Виконує один крок симуляції (наприклад, один "день").
    public void nextDay() {
        this.currentDay++;
        // Тут буде викликатись логіка росту дерев, дозрівання плодів тощо
    }

    //Запускає симуляцію на певну кількість днів.
    public void runSimulation(int days) {
        /**Тут буде реалізовано цикл, який буде певну(задану) кількість разів
         викликати функцію nextDay**/
    }
}
