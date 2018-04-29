package com.kk.ssm.entity;

public class UserComment {
    private int commentId;
    private int songId;
    private String commentTime;
    private String commentInf;
    private int userNumber;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentInf() {
        return commentInf;
    }

    public void setCommentInf(String commentInf) {
        this.commentInf = commentInf;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }
}
