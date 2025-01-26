package fr.treemanager.models.visit;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.member.Member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Visit {
    private final UUID id;
    private final Date date;
    private final UUID treeId;
    private VisitState state;
    private UUID memberId;
    private Report report;

    public Visit(@JsonProperty("id") UUID
                  id, @JsonProperty
            ("date") Date date, @JsonProperty("treeId") UUID treeId) {
        this.id = id;
        this.date = date;
        this.treeId = treeId;
        this.state = VisitState.SCHEDULED;
        this.memberId = null;
    }


    public void setMember(Member member) {
        this.memberId = member.getId();
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public VisitState getState() {
        return state;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public UUID getTreeId() {
        return treeId;
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
        return this.memberId != null;
    }

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        return sdf.format(date);
    }
}
