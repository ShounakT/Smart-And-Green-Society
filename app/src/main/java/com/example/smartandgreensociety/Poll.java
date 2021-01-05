package com.example.smartandgreensociety;

import java.io.Serializable;
import java.util.Map;

public class Poll implements Serializable {
    String id;
    String question;
    Map<String,Integer> options; // Option : Count

    public Poll() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, Integer> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Integer> options) {
        this.options = options;
    }
}
