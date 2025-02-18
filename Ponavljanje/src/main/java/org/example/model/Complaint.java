package org.example.model;

import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Complaint extends Entity{
    @ManyToOne
    private Centre centre;
    @ManyToOne
    private User user;
    private String comment;

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
