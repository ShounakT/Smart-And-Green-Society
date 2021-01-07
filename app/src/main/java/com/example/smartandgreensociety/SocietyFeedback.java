package com.example.smartandgreensociety;

import java.util.HashMap;
import java.util.Map;

public class SocietyFeedback {

    private String feedbackHeading, feedbackContent;

    public SocietyFeedback(){

    }

    public String getFeedbackHeading() {
        return feedbackHeading;
    }

    public void setFeedbackHeading(String feedbackHeading) {
        this.feedbackHeading = feedbackHeading;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Map toCComplaintMap(){

        Map feedbackMap = new HashMap();
        feedbackMap.put("noticeTitle",getFeedbackHeading());
        feedbackMap.put("noticeContent",getFeedbackContent());
        return feedbackMap;
    }
}
