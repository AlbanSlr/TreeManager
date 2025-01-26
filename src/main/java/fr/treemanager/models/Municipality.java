package fr.treemanager.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.tree.Tree;

public class Municipality{
    private List<Tree> trees;

    public Municipality(@JsonProperty("trees")List<Tree> trees) {
        this.trees = trees;
    }

    public void addTree(Tree tree) {
        this.trees.add(tree); //
    }

    public void cutDownTree(Tree tree) {
        this.trees.remove(tree);
        // TODO delete or change state
    }

    @JsonIgnore
    public List<Tree> getRemarkableTrees() {
        List <Tree> remarkableTrees = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.getRemarkable()) {
                remarkableTrees.add(tree);
            }
        }
        return remarkableTrees;
    }

    @JsonIgnore
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

    public List<Tree> getTrees() {
        return trees;
    }
}
