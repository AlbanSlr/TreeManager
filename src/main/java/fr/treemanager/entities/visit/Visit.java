package fr.treemanager.entities.visit;

import fr.treemanager.entities.member.Member;
import fr.treemanager.entities.tree.Tree;
import java.util.Date;

public class Visit {
    private final Date date;
    private final Tree tree;
    private VisitState state;
    private Member member;
    private Report report;

    public Visit(Date date, Tree tree) {
        this.date = date;
        this.tree = tree;
        this.state = VisitState.SCHEDULED;
        this.member = null;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Tree getTree() {
        return tree;
    }

    public Date getDate() {
        return date;
    }

    public VisitState getState() {
        return state;
    }

    public Member getMember() {
        return member;
    }

    public void setReport(Report report) {

        if(state != VisitState.SCHEDULED) {
            throw new IllegalStateException("Visit state is not scheduled");
        }

        this.report = report;
        state = VisitState.DONE;

    }

    public Report getReport() {
        if(state != VisitState.DONE) {
            throw new IllegalStateException("Visit has not been done");
        }

        return report;

    }

    public boolean isBooked() {
        return this.member != null;
    }


}
