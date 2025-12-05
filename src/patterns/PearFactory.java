package patterns;

import model.PearTree;
import model.Tree;

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