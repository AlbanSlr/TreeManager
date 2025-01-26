package fr.treemanager.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<Tree> getNonRemarkableTrees() {
        List <Tree> nonRemarkableTrees = new ArrayList<>();
        for (Tree tree : trees) {
            if (!tree.getRemarkable()) {
                nonRemarkableTrees.add(tree);
            }
        }
        return nonRemarkableTrees;
    }

    public Tree getTree(UUID id) {
        for (Tree tree : trees) {
            if (tree.getId().equals(id)) {
                return tree;
            }
        }
        return null;
    }
}
