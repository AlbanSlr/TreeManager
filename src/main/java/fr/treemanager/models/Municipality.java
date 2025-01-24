package fr.treemanager.models;

import java.util.ArrayList;
import java.util.List;

import fr.treemanager.models.tree.Tree;

public class Municipality{
    private List<Tree> trees;

    public Municipality(List<Tree> trees) {
        this.trees = trees;
    }

    public void addTree(Tree tree) {
        this.trees.add(tree); //
    }

    public void cutDownTree(Tree tree) {
        this.trees.remove(tree);
        // TODO delete or change state
    }

    public List<Tree> getRemarkableTrees() {
        List <Tree> remarkableTrees = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.getRemarkable()) {
                remarkableTrees.add(tree);
            }
        }
        return remarkableTrees;
    }
}
