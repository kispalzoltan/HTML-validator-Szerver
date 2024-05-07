package com.validator.htmlvalidator.models;

import java.util.List;

public class OwnRule {
    private int id;
    private String ruleName;
    private String tagName;
    private List<Attribute> attributes;
    private String type;
    private int priority;
    private String message;
    private String fix;

    public OwnRule() {
    }

    public OwnRule(int id, String ruleName, String tagName, List<Attribute> attributes, String type, int priority, String message, String fix) {
        this.id = id;
        this.ruleName = ruleName;
        this.tagName = tagName;
        this.attributes = attributes;
        this.type = type;
        this.priority = priority;
        this.message = message;
        this.fix = fix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    @Override
    public String toString() {
        return "OwnRule{" +
                "id=" + id +
                ", ruleName='" + ruleName + '\'' +
                ", tagName='" + tagName + '\'' +
                ", attributes=" + attributes +
                ", type='" + type + '\'' +
                ", priority=" + priority +
                ", message='" + message + '\'' +
                ", fix='" + fix + '\'' +
                '}';
    }
}