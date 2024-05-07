package com.validator.htmlvalidator.models;

import java.util.List;

public class OwnRuleGroup {
    private String groupName;
    private List<OwnRule> rules;

    public OwnRuleGroup() {
    }

    public OwnRuleGroup(String groupName, List<OwnRule> rules) {
        this.groupName = groupName;
        this.rules = rules;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<OwnRule> getRules() {
        return rules;
    }

    public void setRules(List<OwnRule> rules) {
        this.rules = rules;
    }
}
