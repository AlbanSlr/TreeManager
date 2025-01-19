package fr.treemanager.entities.association;

import fr.treemanager.entities.member.Member;

import java.util.List;

public class Association {

    private final List<Member> members;
    private final String name;
    private double balance;
    private final List<Payment> payments;
    private final List<Donator> donators;
    private final List<Visit> visits;

    public Association(String name) {
        this.name = name;
        this.balance = 0;
        this.members = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.donators = new ArrayList<>();
        this.visits = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
}
