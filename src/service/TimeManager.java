package service;

import model.Season;
import patterns.TimeObserver;

import java.util.ArrayList;
import java.util.List;

public class TimeManager {
    private int currentDay;
    private Season currentSeason;
    private List<TimeObserver> observers; // Підписники (в даному випадку - дерева)

    public TimeManager() {
        this.currentDay = 1;
        this.currentSeason = Season.SPRING;
        this.observers = new ArrayList<>();
    }

    public void registerObserver(TimeObserver o) {
        observers.add(o);
    }

    public void removeObserver(TimeObserver o) {
        observers.remove(o);
    }

    public void nextDay() {
        currentDay++;

        // Зміна сезону кожні 30 днів
        if (currentDay % 30 == 0) {
            currentSeason = currentSeason.next();
            System.out.println(">>> Зміна сезону! Тепер: " + currentSeason);
        }

        // Сповіщення всіх дерев
        // Використовуємо копію списку, щоб уникнути ConcurrentModificationException,
        // якщо дерево помре і видалиться під час ітерації (хоча видалення йде з Garden, але безпека важлива)
        List<TimeObserver> snapshot = new ArrayList<>(observers);
        for (TimeObserver observer : snapshot) {
            observer.onDayPass(currentSeason);
        }
    }

    public int getDay() { return currentDay; }
    public Season getSeason() { return currentSeason; }
}
