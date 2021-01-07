package com.example.smartandgreensociety;

import java.util.HashMap;
import java.util.Map;

public class SocietyComplaint {

    private String complaintHeading, complaintContent;

    public SocietyComplaint(){

    }

    public String getComplaintHeading() {
        return complaintHeading;
    }

    public void setComplaintHeading(String complaintHeading) {
        this.complaintHeading = complaintHeading;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public Map toCComplaintMap(){

        Map complaintMap = new HashMap();
        complaintMap.put("noticeTitle",getComplaintHeading());
        complaintMap.put("noticeContent",getComplaintContent());
        return complaintMap;
    }
}
