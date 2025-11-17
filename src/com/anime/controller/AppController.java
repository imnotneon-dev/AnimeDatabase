package com.anime.controller;

import com.anime.model.dao.*;
import com.anime.model.*;
import com.anime.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
                currentSession = model.getAccountDAO().selectAccountByUsername(username,pw);

                if(currentSession!=null){
                    System.out.println("Login Success");
                    view.switchView(view.HOME);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
