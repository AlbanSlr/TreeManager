package fr.treemanager.controllers.association;

import fr.treemanager.models.association.Association;
import fr.treemanager.models.association.BudgetYear;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.payment.VisitDefrayal;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.models.member.Donator;
import fr.treemanager.models.visit.VisitState;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.treemanager.utils.FileChangeListener;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AssociationController implements FileChangeListener {

    private Association association;

    public AssociationController() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.association = mapper.readValue(new File("./association.json"), Association.class);
        } catch (Exception e) {
            this.association = new Association("Make America Great Again");
            this.save();
        }

    }

    public void onFileChange() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.association = mapper.readValue(new File("./association.json"), Association.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while reading association from file", e);
        }

        System.out.println(this.association.getMembers());

    }


    private void save() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("./association.json"), association);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        VisitDefrayal visitDefrayal = new VisitDefrayal("Defraiement de la visite " + visit.getId(), visit.getId());
        visitDefrayal.process(association);
        this.association.addVisitDefrayal(visitDefrayal);

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

    public String getBudgetYear() {
        return association.getBudgetYear().toString();
    }

    public void nextBudgetYear() {
        BudgetYear budget = association.getBudgetYear();
        budget.nextYear();
    }

    public double getBalance() {
        return association.getBalance();
    }



    //TODO reveiw compte rendu visite (report)
}