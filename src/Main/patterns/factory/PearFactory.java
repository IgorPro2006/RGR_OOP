package Main.patterns.factory;

import Main.model.tree.PearTree;
import Main.model.tree.Tree;

public class PearFactory implements TreeFactory {
    @Override
    public Tree createTree() {
        return new PearTree.Builder().build();
    }

    @Override
    public Tree createOldTree() {
        return new PearTree.Builder().setAge(30).build();
    }
}