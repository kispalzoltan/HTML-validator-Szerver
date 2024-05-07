package com.validator.htmlvalidator.models;

import java.util.List;

public class FirebaseUser {

    private String email;
    private List<OwnRule> ownRules;

    private List<OwnRuleGroup> ownRuleGroups;

    public FirebaseUser() {
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

    public List<OwnRuleGroup> getOwnRuleGroups() {
        return ownRuleGroups;
    }

    public void setOwnRuleGroups(List<OwnRuleGroup> ownRuleGroups) {
        this.ownRuleGroups = ownRuleGroups;
    }
}
