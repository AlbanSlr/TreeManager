package fr.treemanager.controllers.association;

import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.member.Member;
import fr.treemanager.entities.payment.VisitDefrayal;
import fr.treemanager.entities.visit.Visit;
import fr.treemanager.entities.payment.Bill;
import fr.treemanager.entities.member.Donator;
import fr.treemanager.entities.visit.VisitState;

import java.util.Date;
import java.util.List;

public class AssociationController {

    private final Association association;

    public AssociationController(Association association) {
        this.association = association;
    }

    public void addMember(Member member) {
        association.addMember(member);
    }

    public void removeMember(Member member) {
        association.removeMember(member);
    }

    public List<Member> getMembers() {
        return association.getMembers();
    }

    public void defrayVisit(Visit visit) {

        if(visit.getState() != VisitState.DONE) {
            throw new IllegalArgumentException("Visit must be done to be defrayed");
        }

        VisitDefrayal visitDefrayal = new VisitDefrayal(visit, association);
        this.association.addPayment(visitDefrayal);

    }

    public void addDonator(Donator donator) {
        association.addDonator(donator);
    }

    public void removeDonator(Donator donator) {
        association.removeDonator(donator);
    }

    public void addScheduledVisit(Visit visit) {
        if(visit.getDate().after(new Date())) {
            throw new IllegalArgumentException("The scheduler visit must be in the future");
        }
        association.addScheduledVisit(visit);
    }

    public List<Visit> getVisits() {
        return association.getVisits();
    }

    //TODO reveiw compte rendu visite (report)
}
