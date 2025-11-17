package com.anime.controller;

import com.anime.model.dao.*;
import com.anime.model.*;
import com.anime.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class AppController {
    private AnimeFrame view = new AnimeFrame();
    private AppModel model = new AppModel();
    private Account currentSession;
    public AppController(AnimeFrame view, AppModel model){
        this.view = view;
        this.model = model;

        init_application();
    }

    private void init_application(){
        init_accpnl_listeners();
    }

    private void init_accpnl_listeners() {
        AccountPanel login = view.getLoginPanel();

        login.getLoginBtn().addActionListener(e->{
            String username = login.getLoginName();
            String pw = login.getLoginPassword();
            if(username.isEmpty() || pw.isEmpty()){
                System.out.println("Username and password cannot be empty");
                return;
            }
            try {
                Account account = model.getAccountDAO().selectAccountByUsername(username);
                if(account!=null){
                    this.currentSession = account;
                    System.out.println("Login Success");
                    view.switchView(view.HOME);
                } else {
                    System.out.println("Login failed: Invalid username or password");
                    return;
                }


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        login.getSignupBtn().addActionListener(e->{
            login.getLoginContainer().setVisible(false);
            login.getSignupContainer().setVisible(true);
        });

        login.getAlreadyHasAccountBtn().addActionListener(e->{
            login.getLoginContainer().setVisible(true);
            login.getSignupContainer().setVisible(false);
        });

        login.getSubmitSignUpBtn().addActionListener(e->{
            String username = login.getSignName();
            String password = login.getSignPassword();
            String confirmPw = login.getSignConfirm();
            String dob = login.getSignDob();
            String country = login.getCountry();
            if(username.isEmpty() || password.isEmpty() || confirmPw.isEmpty() || dob == null || country.isEmpty()){
                System.out.println("Username and password cannot be empty");
                return;
            }
            else if (!password.equals(confirmPw)){
                System.out.println("Passwords do not match or is empty");
                return;
            }

            LocalDate date;
            try{
                date = LocalDate.parse(dob);
            } catch (Exception ex){
                System.out.println("Invalid date");
                return;
            }
            try {
                Account checker = model.getAccountDAO().selectAccountByUsername(username);
                if(checker!=null){
                    System.out.println("Account already exist");
                }
                else {
                    model.getAccountDAO().addUser(username,password, String.valueOf(dob),country,"None");
                    Account account = model.getAccountDAO().selectAccountByUsername(username);

                    if(account!=null){
                        this.currentSession = account;
                    System.out.println("Signup Success. Logging in...");
                    view.switchView(view.HOME);
                    }
                    else{
                        System.out.println("Account created, but auto login failed.");
                    }

                }
            } catch (SQLException ex) {
                System.err.println("DB Error during signup: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });
    }
}
