package Main.Run;

import Main.model.tree.Tree;
import Main.model.otherObject.Gardener;
import Main.patterns.factory.AppleFactory;
import Main.patterns.factory.PearFactory;
import Main.patterns.factory.TreeFactory;
import Main.service.Garden;
import Main.service.TimeManager;


import java.util.Scanner;


public class Run {
    public static void main(String[] args) {
        System.out.println("\n-------------------------------------------");
        System.out.println("Запуск симуляції...");
        System.out.println("-------------------------------------------\n");


        Garden garden = Garden.getInstance();

        TimeManager timeManager = new TimeManager();
        Gardener gardener = new Gardener();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        System.out.println("Вітаємо у Саду! Введіть 'help' для списку команд.");

        while (running) {
            // Відображення статусу
            printHUD(gardener, timeManager, garden);

            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "help":
                    printHelp();
                    break;

                case "next":
                    timeManager.nextDay();
                    gardener.rest();
                    break;

                case "plant":
                    System.out.println("Яке дерево? (1 - Яблуня, 2 - Груша): ");
                    String type = scanner.nextLine();
                    TreeFactory factory = null;
                    if (type.equals("1")) factory = new AppleFactory();
                    else if (type.equals("2")) factory = new PearFactory();

                    if (factory != null) {
                        gardener.plantTree(factory, timeManager);
                    } else {
                        System.out.println("Невірний вибір.");
                    }
                    break;

                case "harvest":
                    gardener.harvest();
                    break;

                case "fertilize":
                    System.out.print("Введіть ID дерева: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        Tree target = null;
                        for (Tree t : garden.getTrees()) {
                            if (t.getId() == id) target = t;
                        }
                        if (target != null) gardener.fertilizeTree(target);
                        else System.out.println("Дерево не знайдено.");
                    } catch (NumberFormatException e) {
                        System.out.println("Некоректний ID.");
                    }
                    break;

                case "trees":
                    printTrees(garden);
                    break;

                case "exit":
                case "stop":
                    running = false;
                    break;

                default:
                    // Підтримка команди пропуску днів (наприклад "skip 5")
                    if (input.startsWith("skip ")) {
                        try {
                            int days = Integer.parseInt(input.split(" ")[1]);
                            for (int i = 0; i < days; i++) {
                                timeManager.nextDay();
                                gardener.rest(false);
                            }
                            System.out.println("Готово! Пройшло днів: " + days + ". Садівник відпочив.");
                        } catch (Exception e) {
                            System.out.println("Помилка команди skip.");
                        }
                    } else {
                        System.out.println("Невідома команда.");
                    }
            }
        }

        System.out.println("Симуляція завершена. Дякую!");
    }

    private static void printHUD(Gardener g, TimeManager tm, Garden garden) {
        System.out.println("\n[ День: " + tm.getDay() + " | Сезон: " + tm.getSeason() + " ]");
        System.out.println("[ Садівник Енергія: " + g.getEnergy() + "/100 | Зібрано: " + g.getCollectedFruits() + " ]");
        System.out.println("[ Дерев у саду: " + garden.getTrees().size() + " | Добрива: " + garden.getFertilizerCount() + " ]");
    }

    private static void printTrees(Garden garden) {
        if (garden.getTrees().isEmpty()) {
            System.out.println("Сад порожній.");
            return;
        }
        System.out.printf("%-4s %-15s %-15s %-5s %-5s %-10s%n", "ID", "Тип", "Етап", "Вік", "HP", "Фрукти");
        for (Tree t : garden.getTrees()) {
            System.out.printf("%-4d %-15s %-15s %-5d %-5d %-10d%n",
                    t.getId(), t.getType(), t.getLifecycle(), t.getAge(), t.getHealth(), t.getFruits().size());
        }
    }

    private static void printHelp() {
        System.out.println("Команди:");
        System.out.println("  plant     - Посадити дерево");
        System.out.println("  harvest   - Зібрати стиглі фрукти");
        System.out.println("  fertilize - Удобрити дерево (потрібен ID)");
        System.out.println("  trees     - Список дерев");
        System.out.println("  next      - Наступний день");
        System.out.println("  skip N    - Пропустити N днів");
        System.out.println("  stop      - Вихід");
    }
}