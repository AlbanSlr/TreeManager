package fr.treemanager.models.association;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.Municipality;
import fr.treemanager.models.payment.*;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.visit.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Association {
    private final UUID id;
    private final List<Member> members = new ArrayList<>();
    private final String name;
    private double balance = 0;
    private final Municipality municipality;

    private final List<Bill> bills = new ArrayList<>();
    private final List<Donation> donations = new ArrayList<>();
    private final List<Subscription> subscriptions = new ArrayList<>();
    private final List<VisitDefrayal> visitDefrayals = new ArrayList<>();

    private final List<Visit> visits = new ArrayList<>();
    private final BudgetYear budgetYear = new BudgetYear();

    public Association(@JsonProperty("name") String name,@JsonProperty("municipality") Municipality municipality) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.municipality = municipality;
    }

    public UUID getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Bill> getBills() { return  bills; }

    public List<Donation> getDonations() { return donations; }

    public List<Subscription> getSubscriptions() { return subscriptions; }

    public List<VisitDefrayal> getVisitDefrayals() { return visitDefrayals; }

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

    public void addDonation(Donation donation) {
        this.donations.add(donation);
    }

    public void addSubscription(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    public void addVisitDefrayal(VisitDefrayal visitDefrayal) {
        this.visitDefrayals.add(visitDefrayal);
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
        for(Subscription subscription : subscriptions) {
                if(budgetYear.include(subscription.getPaymentDate()) && subscription.getMemberId() == member.getId()) {
                    return true;
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

    public BudgetYear getBudgetYear() {
        return budgetYear;
    }

    public Municipality getMunicipality() {
        return municipality;
    }
}
