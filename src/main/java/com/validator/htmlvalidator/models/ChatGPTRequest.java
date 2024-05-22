package com.validator.htmlvalidator.models;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTRequest {

        private String model;
        private List<Message> messages;
        private int n;
        private double temperature;
        private final int max_tokens;
        private final String system = "Act like youre a html support assistant, and you have to correct the html code what the user will send you. So you will get a html code and an instruction, and you have to send back just the correct html code. ";

        public ChatGPTRequest(String model, String prompt) {
            this.model = model;
            this.n = 1;
            this.max_tokens = 50;
            this.messages = new ArrayList<>();
            this.messages.add(new Message("system", system));
            this.messages.add(new Message("user", prompt));
        }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
