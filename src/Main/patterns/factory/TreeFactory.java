package Main.patterns.factory;

import Main.model.tree.Tree;

/**Патерн Abstract Factory / Factory Method.**/
public interface TreeFactory {
    Tree createTree();
    Tree createOldTree();
}
