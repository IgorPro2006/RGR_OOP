package model;

import patterns.GrowthStrategy;
import patterns.TimeObserver;


import java.util.List;
import java.util.ArrayList;
import java.util.Random;








public abstract class Tree implements TimeObserver {
    protected int id;
    protected int age;
    protected int health;
    protected TreeLifecycle lifecycle;
    protected List<Fruit> fruits;
    protected GrowthStrategy growthStrategy;

    private static int idCounter = 1;
    protected Random random = new Random();

    // Патерн Builder: Конструктор приймає білдер
    protected Tree(TreeBuilder builder) {
        this.id = idCounter++;
        this.age = builder.age;
        this.health = builder.health;
        this.fruits = new ArrayList<>();
        this.lifecycle = TreeLifecycle.getStageByAge(this.age);
        this.growthStrategy = builder.growthStrategy;
    }

    @Override
    public void onDayPass(Season season) {
        if (lifecycle == TreeLifecycle.DEAD) return;

        // Зміна здоров'я через стратегію
        int healthChange = growthStrategy.calculateHealthChange(season);
        this.health += healthChange;

        // Перевірка смерті від здоров'я
        if (this.health <= 0) {
            this.health = 0;
            this.lifecycle = TreeLifecycle.DEAD;
            this.fruits.clear();
            return;
        }

        // Старіння
        this.age++;
        this.lifecycle = TreeLifecycle.getStageByAge(this.age);

        // Ріст фруктів (тільки для зрілих дерев і не взимку)
        if (lifecycle == TreeLifecycle.MATURE && season != Season.WINTER) {
            produceFruits();
        }

        // Оновлення стану існуючих фруктів
        for (Fruit fruit : fruits) {
            fruit.mature();
        }
    }

    // Абстрактний метод (Template Method pattern variation)
    protected abstract void produceFruits();
    public abstract String getType();

    public void heal(int amount) {
        if (lifecycle != TreeLifecycle.DEAD) {
            this.health = Math.min(100, this.health + amount);
        }
    }

    // Getters
    public int getId() { return id; }
    public int getAge() { return age; }
    public int getHealth() { return health; }
    public TreeLifecycle getLifecycle() { return lifecycle; }
    public List<Fruit> getFruits() { return fruits; }

    // --- Inner Builder Class ---
    public static abstract class TreeBuilder<T extends TreeBuilder<T>> {
        int age = 0;
        int health = 100;
        GrowthStrategy growthStrategy;

        public T setAge(int age) {
            this.age = age;
            return self();
        }

        public T setHealth(int health) {
            this.health = health;
            return self();
        }

        public T setStrategy(GrowthStrategy strategy) {
            this.growthStrategy = strategy;
            return self();
        }

        protected abstract T self();
        public abstract Tree build();
    }
}