package fr.treemanager.entities.member;
import fr.treemanager.entities.payment.Issuer;
import fr.treemanager.entities.payment.Receiver;
import fr.treemanager.entities.tree.Tree;

import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;


public class Member implements Issuer, Receiver {
    private final static int MAX_VOTES = 5;

    private final UUID id;
    private String lastName;
    private String firstName;
    private Date lastSubscriptionDate;
    private Role role;
    private LinkedList<Tree> votes;

    public Member(String lastName, String firstName, Date lastSubscriptionDate, Role role) {
        this.id = UUID.randomUUID(); // on génère un identifiant unique et aléatoire
        this.lastName = lastName;
        this.firstName = firstName;
        this.lastSubscriptionDate = lastSubscriptionDate;
        this.role = role;
        this.votes = new LinkedList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getLastSubscriptionDate() {
        return lastSubscriptionDate;
    }

    public Role getRole() {
        return role;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastSubscriptionDate(Date lastSubscriptionDate) {
        this.lastSubscriptionDate = lastSubscriptionDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void vote(Tree tree) {
        if(votes.size() == MAX_VOTES) {
            votes.removeLast();
        }
        votes.addFirst(tree);
    }

    public LinkedList<Tree> getVotes() {
        return votes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
