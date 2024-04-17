package com.validator.htmlvalidator.models;

import java.util.List;

public class FirebaseUser {

    private String email;
    private List<OwnRule> ownRules;

    public FirebaseUser() {
    }

    public FirebaseUser(String email, List<OwnRule> ownRules) {
        this.email = email;
        this.ownRules = ownRules;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OwnRule> getOwnRules() {
        return ownRules;
    }

    public void setOwnRules(List<OwnRule> ownRules) {
        this.ownRules = ownRules;
    }
}
