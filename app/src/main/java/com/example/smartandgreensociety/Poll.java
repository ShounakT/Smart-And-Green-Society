package com.example.smartandgreensociety;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Poll implements Serializable {
    String id;
    String ques;
    Map<String,Integer> options; // Option : Count

    public Poll() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public Map<String, Integer> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Integer> options) {
        this.options = options;
    }
}
