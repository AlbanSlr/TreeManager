package fr.treemanager.entities;

import java.util.List;

import fr.treemanager.entities.tree.Tree;

public class Municipality {
    private List<Tree> trees;

    public Municipality(List<Tree> trees) {
        this.trees = trees;
    }
}
