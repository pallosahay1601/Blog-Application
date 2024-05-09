/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.entities;

import java.sql. *;
public class Post {
    private int idPost;
    private String pTitle;
    private String pContent;
    private String pCode;
    private String pPic;
    private Timestamp pDate;
    private int catID;
    private int userID;

    public Post() {
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Post(int idPost, String pTitle, String pContent, String pCode, String pPic, Timestamp pDate, int catID,int userID) {
        this.idPost = idPost;
        this.pTitle = pTitle;
        this.pContent = pContent;
        this.pCode = pCode;
        this.pPic = pPic;
        this.pDate = pDate;
        this.catID = catID;
        this.userID=userID;
    }

    public int getUserID() {
        return userID;
    }

    public Post(String pTitle, String pContent, String pCode, String pPic, Timestamp pDate, int catID,int userID) {
        this.pTitle = pTitle;
        this.pContent = pContent;
        this.pCode = pCode;
        this.pPic = pPic;
        this.pDate = pDate;
        this.catID = catID;
        this.userID=userID;
    }

    public int getIdPost() {
        return idPost;
    }

    public String getpTitle() {
        return pTitle;
    }

    public String getpContent() {
        return pContent;
    }

    public String getpCode() {
        return pCode;
    }

    public String getpPic() {
        return pPic;
    }

    public Timestamp getpDate() {
        return pDate;
    }

    public int getCatID() {
        return catID;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public void setpContent(String pContent) {
        this.pContent = pContent;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public void setpPic(String pPic) {
        this.pPic = pPic;
    }

    public void setpDate(Timestamp pDate) {
        this.pDate = pDate;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }
    
    
}
