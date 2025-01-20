package fr.treemanager.controllers;

import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.member.Member;
import fr.treemanager.entities.payment.Subscription;
import fr.treemanager.entities.tree.Tree;
import fr.treemanager.entities.visit.Visit;
import fr.treemanager.entities.visit.VisitState;
import fr.treemanager.entities.visit.Report;

import java.util.ArrayList;
import java.util.List;

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

    public void subscribe() {
        if(this.isSubscribed()) {
            throw new IllegalArgumentException("Member is already subscribed");
        }

        Subscription subscription = new Subscription(association, member);
        this.association.addPayment(subscription);
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

    public void submitReport(Visit visit, Report report) {
        visit.setReport(report);
    }

    public boolean isSubscribed() {
        return association.isSubscribed(member);
    }

    public List<Visit> getAvailableVisits() {
        List<Visit> availableVisits = new ArrayList<>();

        for(Visit visit : this.association.getVisits() ) {
            if(visit.getState() == VisitState.SCHEDULED) {
                availableVisits.add(visit);
            }
        }
        return availableVisits;
    }
}
