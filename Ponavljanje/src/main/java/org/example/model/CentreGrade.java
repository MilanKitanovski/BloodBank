package org.example.model;

import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class CentreGrade extends Entity{
    @ManyToOne
    private Centre centre;
    private double grade;

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
