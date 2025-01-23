package fr.treemanager.models.member;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.tree.Tree;

import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;


public class Member {
    private final static int MAX_VOTES = 5;

    private final UUID id;
    private String lastName;
    private String firstName;
    private Date lastSubscriptionDate = null;
    private Role role;
    private LinkedList<UUID> votes;

    public Member(@JsonProperty("lastName") String lastName,@JsonProperty("firstName") String firstName,@JsonProperty("role") Role role) {
        this.id = UUID.randomUUID(); // on génère un identifiant unique et aléatoire
        this.lastName = lastName;
        this.firstName = firstName;
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
        votes.addFirst(tree.getId());
    }

    public LinkedList<UUID> getVotes() {
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
