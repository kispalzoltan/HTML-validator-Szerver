package com.validator.htmlvalidator.models;

public class Result {

    private String type;
    private String subType;
    private int lastbLine;
    private int lastColumn;
    private int firstColumn;
    private String message;
    private String extract;

    public Result(String type, String subType, int lastbLine, int lastColumn, int firstColumn, String message, String extract) {
        this.type = type;
        this.subType = subType;
        this.lastbLine = lastbLine;
        this.lastColumn = lastColumn;
        this.firstColumn = firstColumn;
        this.message = message;
        this.extract = extract;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getLastbLine() {
        return lastbLine;
    }

    public void setLastbLine(int lastbLine) {
        this.lastbLine = lastbLine;
    }

    public int getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(int lastColumn) {
        this.lastColumn = lastColumn;
    }

    public int getFirstColumn() {
        return firstColumn;
    }

    public void setFirstColumn(int firstColumn) {
        this.firstColumn = firstColumn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }
}
