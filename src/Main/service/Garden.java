package Main.service;

import Main.model.tree.Tree;

import java.util.ArrayList;
import java.util.List;


public class Garden {
    private static Garden instance;
    private List<Tree> trees;
    private int fertilizerCount;

    private Garden() {
        this.trees = new ArrayList<>();
        this.fertilizerCount = 5; // Початкові добрива
    }

    public static synchronized Garden getInstance() {
        if (instance == null) {
            instance = new Garden();
        }
        return instance;
    }

    public void addTree(Tree tree) {
        trees.add(tree);
    }

    public void removeTree(Tree tree) {
        trees.remove(tree);
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public int getFertilizerCount() {
        return fertilizerCount;
    }

    public void addFertilizer(int amount) {
        this.fertilizerCount += amount;
    }

    public boolean useFertilizer() {
        if (fertilizerCount > 0) {
            fertilizerCount--;
            return true;
        }
        return false;
    }

    // Метод для очищення (корисний для тестів)
    public void clear() {
        trees.clear();
        fertilizerCount = 5;
    }

}
