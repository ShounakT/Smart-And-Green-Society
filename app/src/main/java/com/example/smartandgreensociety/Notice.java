package com.example.smartandgreensociety;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Notice implements Serializable {
    private String noticeTitle;
    private String noticeContent;


    public Notice() {
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Map toNoticeMap(){

        Map noticeMap = new HashMap();
        noticeMap.put("noticeTitle",getNoticeTitle());
        noticeMap.put("noticeContent",getNoticeContent());
        return noticeMap;
    }
}
