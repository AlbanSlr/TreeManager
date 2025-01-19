package fr.treemanager.controllers;

import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.member.Member;
import fr.treemanager.entities.visit.Visit;
import fr.treemanager.entities.payment.Bill;
import fr.treemanager.entities.member.Donator;

import java.util.Date;

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

    public void payBill(Bill bill) {
        if (bill.isPaid()) {
            throw new IllegalStateException("Bill already paid");
        } else {
           if (association.getBalance() >= bill.getAmount()) {
               association.setBalance(association.getBalance() - bill.getAmount());
               bill.setPaid();
           } else {
               throw new RuntimeException("Not enough money in the association balance");
           }
        }
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

    //TODO reveive compte rendu visite (report)
}
