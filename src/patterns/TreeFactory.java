package patterns;

import model.Tree;

/**Патерн Abstract Factory / Factory Method.**/
public interface TreeFactory {
    Tree createTree();
    Tree createOldTree();
}
