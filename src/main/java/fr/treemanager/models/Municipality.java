package fr.treemanager.models;

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
}
