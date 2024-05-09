/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.dao;

import com.tech.blog.entities.User;
import java.sql.*;

/**
 *
 * @author pallavi
 */
public class UserDao {

    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    //method to insert user to db
    public boolean saveUser(User user) {
        boolean saved = false;
        try {
            String query = "insert into user(name,email,password,gender,userType) values (?,?,?,?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getUserType());
            pstmt.executeUpdate();
            saved = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }

    //get user by useremail and userpassword
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            String query = "select * from User where email=? and password=?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                user = new User();
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setId(result.getInt("id"));
                user.setGender(result.getString("gender"));
                user.setDateTime(result.getTimestamp("created"));
                user.setProfile(result.getString("profile"));
                user.setUserType(result.getString("userType"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUser(User user) {
        boolean isUpdated = false;
        try {
            String query = "update user set name=?, email=?, password=?, userType=?, profile=? where id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getUserType());
            pstmt.setString(5, user.getProfile());
            pstmt.setInt(6, user.getId());
            pstmt.executeUpdate();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public User getUserByUserID(int userID) {
        User user = null;
        try {
            String query = "select * from User where id=?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setInt(1, userID);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                  user = new User();
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setId(result.getInt("id"));
                user.setGender(result.getString("gender"));
                user.setDateTime(result.getTimestamp("created"));
                user.setProfile(result.getString("profile"));
                user.setUserType(result.getString("userType"));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }
}
