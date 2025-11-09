package com.anime.controller;

import com.anime.dao.AccountDAO;

public class AppController {
    private AccountDAO accountDAO; // Data Access Object for account operations

    public AppController() {
        accountDAO = new AccountDAO();
    }

    public boolean loginUser(String username, String password) {
        return accountDAO.validateLogin(username, password);
    }
}
