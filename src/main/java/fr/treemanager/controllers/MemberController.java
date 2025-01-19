package fr.treemanager.controllers;

import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.member.Member;
import fr.treemanager.entities.tree.Tree;
import fr.treemanager.entities.visit.Visit;
import fr.treemanager.entities.visit.VisitState;

public class MemberController {

    private final Member member;
    private final Association association;

    public MemberController(Member member, Association association) {
        this.member = member;
        this.association = association;
    }

    public void vote(Tree tree) {
        member.vote(tree);
    }

    public void paySubscription() {

    }

    public void unsubscribe() {
        this.association.removeMember(member);
    }

    public void selectScheduledVisit(Visit visit) {
        if(visit.getState() != VisitState.SCHEDULED) {
            throw new IllegalArgumentException("Visit must be scheduled to be selected");
        }

        if(!visit.isBooked()) {
            throw new IllegalArgumentException("Visit must not be booked to be selected");
        }

        visit.setMember(member);
    }

    // TODO submit a report after a visit
}
