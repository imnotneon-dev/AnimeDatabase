package com.anime.model;

import java.time.LocalDate;

public class Actor {

    private int id;
    private String lastName;
    private String firstName;
    private String gender;
    private LocalDate dob;
    private String pob;
    private String agency;

    public Actor(int id, String lastName, String firstName, String gender, LocalDate dob, String pob, String agency) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dob = dob;
        this.pob = pob;
        this.agency = agency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}