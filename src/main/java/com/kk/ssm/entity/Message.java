package com.kk.ssm.entity;

import java.sql.Timestamp;

public class Message {
    private int msgId;
    private int fromId;
    private String fromName;
    private int toId;
    private String msgText;
    private String msgTime;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    @Override
    public String toString(){
        return "Message{" +
                "msgId=" + msgId +
                ", fromId=" + fromId +
                ", fromName='" + fromName + '\'' +
                ", toId=" + toId +
                ", msgText='" + msgText + '\'' +
                ", msgTime=" + msgTime +
                '}';
    }
}
