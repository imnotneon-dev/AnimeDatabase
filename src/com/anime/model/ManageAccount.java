package com.anime.model;

import java.sql.Date;

public class ManageAccount {

    private int userId;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String country;
    private String topGenre;
    private Date dateCreated;

    public ManageAccount(int userId, String username, String password, Date dateOfBirth, String country, String topGenre, Date dateCreated) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.topGenre = topGenre;
        this.dateCreated = dateCreated;
    }

    public ManageAccount(String username, String password, Date dateOfBirth, String country, String topGenre) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.topGenre = topGenre;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTopGenre() {
        return topGenre;
    }

    public void setTopGenre(String topGenre) {
        this.topGenre = topGenre;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
