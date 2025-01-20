package fr.treemanager.entities.payment;

import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.member.Member;
import fr.treemanager.entities.visit.Visit;

public class VisitDefrayal extends Payment {
    private final static double VISIT_DEFRAYAL_AMOUNT = 69;
    private final Visit visit;

    public VisitDefrayal(Visit visit, Association association, Member member) {
        super(association, member, "Defrayal of" + member, VISIT_DEFRAYAL_AMOUNT);
        this.visit = visit;
    }

    public Visit getVisit() {
        return visit;
    }
}
