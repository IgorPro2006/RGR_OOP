package Main.patterns.factory;


import Main.model.tree.AppleTree;
import Main.model.tree.Tree;

public class AppleFactory implements TreeFactory {
    @Override
    public Tree createTree() {
        return new AppleTree.Builder().build();
    }

    @Override
    public Tree createOldTree() {
        return new AppleTree.Builder().setAge(25).build();
    }
}