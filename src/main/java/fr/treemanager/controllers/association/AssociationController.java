package fr.treemanager.controllers.association;

import fr.treemanager.models.Municipality;
import fr.treemanager.models.association.Association;
import fr.treemanager.models.association.BudgetYear;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.payment.Bill;
import fr.treemanager.models.payment.Donation;
import fr.treemanager.models.payment.VisitDefrayal;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.models.visit.VisitState;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.treemanager.utils.FileChangeListener;
import fr.treemanager.utils.FileWatcherService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AssociationController implements FileChangeListener {

    private Association association;

    public AssociationController() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.association = mapper.readValue(new File("./association.json"), Association.class);
        } catch (Exception e) {
            this.association = new Association("lez arbres", new Municipality(Tree.loadTreesFromCSV()));
            this.save();
        }
        FileWatcherService fileWatcherService = new FileWatcherService("./");
        fileWatcherService.addListener("association.json", this);
        fileWatcherService.start();
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

    public void addScheduledVisit(Visit visit) {
        if(visit.getDate().before(new Date())) {
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

    public List<Tree> getRemarkableTrees() {
        return association.getMunicipality().getRemarkableTrees();
    }

    public List<Donation> getDonations() {
        return association.getDonations();
    }

    public void processDonation(Donation donation) {
        donation.process(association);
    }

    public List<Tree> getNonRemarkableTreesRanking() {
        List<Tree> nonRemarkableTrees = association.getMunicipality().getNonRemarkableTrees();
        HashMap<Tree, Integer> ranking = new HashMap<>();
        for (Tree tree : nonRemarkableTrees) {
            ranking.put(tree, 0);
        }
        for (Member member : association.getMembers()) {
            for (UUID treeID : member.getVotes()) {
                Tree tree = association.getMunicipality().getTree(treeID);
                if (tree == null) {
                    continue; // case where the tree has been removed but still in the votes
                }
                if (ranking.containsKey(tree)) {
                    ranking.put(tree, ranking.get(tree) + 1);
                }
            }
        }
        nonRemarkableTrees.removeIf(tree -> ranking.get(tree) == 0);
        nonRemarkableTrees.sort((t1, t2) -> ranking.get(t2) - ranking.get(t1));
        return nonRemarkableTrees;
    }

    public Visit getVisit(UUID id) {
        for (Visit visit : association.getVisits()) {
            if (visit.getId().equals(id)) {
                return visit;
            }
        }
        return null;
    }

    public Member getMember(UUID id) {
        for (Member member : association.getMembers()) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    public List<Bill> getBills() {
        return association.getBills();
    }

    public Association getAssociation() {
        return association;
    }

    public boolean createVisitDefrayal(Visit visit) {
        if (VisitDefrayal.getVisitDefrayalAmount() > association.getBalance()) {
            return false;
        }
        VisitDefrayal visitDefrayal = new VisitDefrayal("Defraiement de la visite " + visit.getId(), visit.getId());
        visitDefrayal.process(association);
        association.addVisitDefrayal(visitDefrayal);
        return true;
    }

    public List<VisitDefrayal> getVisitDefrayals() {
        return association.getVisitDefrayals();
    }



    //TODO recevoir compte rendu visite (report)
}