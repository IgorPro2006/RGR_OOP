package Garden;

import java.util.List;
import java.util.ArrayList;

/** Абстрактний базовий клас для всіх дерев. Реалізує спільну логіку, дотримуючись принципу DRY**/
public abstract class AbstractTree {

    /**Властивості, спільні для всіх дерев**/
    protected int age;
    protected double health;
    protected String type;
    protected List<Fruit> fruits;
    protected boolean isPlanted;

    /**Конструктор для базових властивостей**/
    public AbstractTree(String type) {
        this.type = type;
        this.age = 0;
        this.health = 100.0;
        this.fruits = new ArrayList<>();
        this.isPlanted = false;
    }

    /** Створює плід, специфічний для цього типу дерева (Creator).Цей метод має бути реалізований нащадками.**/
    protected abstract Fruit createFruit();

    public void grow() {
        //Спільна логіка росту для кожного дерева
    }


    public boolean areFruitsRipe() {
        //Спільна логіка перевірки плодів у списку this.fruits
        return false;
    }


    public void applyFertilizer(Fertilizer fertilizer) {
        //Спільна логіка покращення здоров'я за допомогою добрив
    }


    public List<Fruit> harvest() {
        //Спільна логіка збору стиглих плодів зі списку this.fruits
        return new ArrayList<>();
    }


    public void remove() {
        //Спільна логіка "смерті" або видалення дерева
    }

    //Реалізація геттерів

    public int getAge() {
        return this.age;
    }


    public double getHealth() {
        return this.health;
    }


    public String getType() {
        return this.type;
    }
}
