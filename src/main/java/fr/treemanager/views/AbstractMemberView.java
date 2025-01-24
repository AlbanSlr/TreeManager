package fr.treemanager.views;

import fr.treemanager.controllers.member.MemberController;

public abstract class AbstractMemberView {
    protected MemberController controller;

    public AbstractMemberView(MemberController controller) {
        this.controller = controller;
    }
}
