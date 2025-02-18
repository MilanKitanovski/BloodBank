package org.example.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

@javax.persistence.Entity
public class Appointment extends Entity{

    @ManyToOne
    private Centre centre;
    private Date dateAndTime;
    private int duration;
    @Column(name = "reservate", columnDefinition = "boolean default false") //ovim definisemo tip kolone da je boolean i default false je
    private boolean isReserved;
    public Appointment(Centre centre, Date dateAndTime, int duration, boolean isReserved) {
        this.centre = centre;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.isReserved = isReserved;
    }

    public Appointment(){

    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
