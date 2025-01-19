package fr.treemanager.entities.payment;

import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.member.Member;

import java.util.Date;

public class Subscription extends Payment {
    private final static double SUBSCRIPTION_AMOUNT = 42;

    private Member member;

    public Subscription(Association association, Member member) {
        super(member, association, "Subscription of " + member + " at " + new Date(), SUBSCRIPTION_AMOUNT, new Date());
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}