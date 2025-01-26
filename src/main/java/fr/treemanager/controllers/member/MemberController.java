package fr.treemanager.controllers.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.treemanager.models.Municipality;
import fr.treemanager.models.association.Association;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.payment.Subscription;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.models.visit.VisitState;
import fr.treemanager.models.visit.Report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberController {

    private final Member member;
    private Association association;

    public MemberController(UUID memberID) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.association = mapper.readValue(new File("./association.json"), Association.class);
        } catch (Exception e) {

            System.out.println("Error while reading association from file, creating a new one : " + e);
            this.association = new Association("lez arbres", new Municipality(Tree.loadTreesFromCSV()));
        }
        this.member = association.getMembers().stream()
                .filter(m -> m.getId().equals(memberID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    public Member getMember() {
        return member;
    }

    public void vote(Tree tree) {
        member.vote(tree);
    }

    public void subscribe() {
        if(this.isSubscribed()) {
            throw new IllegalArgumentException("Member is already subscribed");
        }

        Subscription subscription = new Subscription("Souscription de " + member, member.getId());
        subscription.process(association);
        this.association.addSubscription(subscription);
    }

    public void unsubscribe() {
        this.association.removeMember(member);
    }

    public void selectScheduledVisit(Visit visit) {
        if(visit.getState() != VisitState.SCHEDULED) {
            throw new IllegalArgumentException("Visit must be scheduled to be selected");
        }

        if(!visit.isBooked()) {
            throw new IllegalArgumentException("Visit must not be booked to be selected");
        }

        visit.setMember(member);
    }

    public void submitReport(Visit visit, Report report) {
        visit.setReport(report);
    }

    public boolean isSubscribed() {
        return association.isSubscribed(member);
    }

    public List<Visit> getAvailableVisits() {
        List<Visit> availableVisits = new ArrayList<>();

        for(Visit visit : this.association.getVisits() ) {
            if(visit.getState() == VisitState.SCHEDULED) {
                availableVisits.add(visit);
            }
        }
        return availableVisits;
    }

    public List<Tree> getArbres(){
        return association.getMunicipality().getNonRemarkableTrees();
    }

}
