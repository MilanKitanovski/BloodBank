package org.example.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

@javax.persistence.Entity
public class Schedule extends Entity{
    @ManyToOne
    private User admin;
    @Column
    private Date startWork;
    @Column
    private Date endWork;
    @ManyToOne
    private User user;
    @ManyToOne
    private Centre centre;

    public Schedule(User admin, Date startWork, Date endWork, User user, Centre centre) {
        this.admin = admin;
        this.startWork = startWork;
        this.endWork = endWork;
        this.user = user;
        this.centre = centre;
    }

    public Schedule(){

    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Date getStartWork() {
        return startWork;
    }

    public void setStartWork(Date startWork) {
        this.startWork = startWork;
    }

    public Date getEndWork() {
        return endWork;
    }

    public void setEndWork(Date endWork) {
        this.endWork = endWork;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
}
