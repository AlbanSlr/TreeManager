package fr.treemanager.entities.association;

import fr.treemanager.entities.member.Donator;
import fr.treemanager.entities.payment.*;
import fr.treemanager.entities.member.Member;
import fr.treemanager.entities.visit.Visit;

import java.util.ArrayList;
import java.util.List;

public class Association implements Receiver, Issuer {
    private final List<Member> members;
    private final String name;
    private double balance;
    private final List<Payment> payments;
    private final List<Donator> donators;
    private final List<Visit> visits;
    private BudgetYear budgetYear;

    public Association(String name) {
        this.name = name;
        this.balance = 0;
        this.members = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.donators = new ArrayList<>();
        this.visits = new ArrayList<>();
        this.budgetYear = new BudgetYear();
    }

    public double getBalance() {
        return balance;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);

        for(Visit visit: visits) {
            if(visit.getMember().equals(member)) {
                visit.setMember(null);
            }
        }

        for(Payment payment : payments) {
            if(payment instanceof Subscription subscription) {
                if(subscription.getMember().equals(member)) {
                    subscription.setMember(null);
                }
            }
        }
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addDonator(Donator donator) {
        this.donators.add(donator);
    }

    public void removeDonator(Donator donator) {
        this.donators.remove(donator);
    }

    public void addScheduledVisit(Visit visit) {
        this.visits.add(visit);
    }

    public String getName() {
        return name;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public boolean isSubscribed(Member member) {

        if(!members.contains(member)) {
            return false;
        }

        for(Payment payment : payments) {

            if(payment instanceof Subscription subcription) {
                if(budgetYear.include(payment.getPaymentDate()) && subcription.getMember() == member) {
                    return true;
                }
            }
        }

        return false;

    }

     public void tryToPay(Payment payment) throws NotEnoughFunds {
        if(this.balance < payment.getAmount()) {
            throw new NotEnoughFunds();
        }
        this.balance -= payment.getAmount();
    }

    public void processPayment(Payment payment) {
        this.balance += payment.getAmount();
    }
}
