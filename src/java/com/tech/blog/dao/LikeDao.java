
package com.tech.blog.dao;
import java.sql.*;
public class LikeDao {
    Connection con;

    public LikeDao(Connection con) {
        this.con = con;
    }
    
    public boolean insertLike(int postID,int userID)
    {
        boolean isSaved=false;
        try
        {
            String query="insert into likes(pid,uid) values(?,?)";
            PreparedStatement pstmt=this.con.prepareStatement(query);
            pstmt.setInt(1, postID);
            pstmt.setInt(2, userID);
            pstmt.executeUpdate();
            isSaved=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isSaved;
    }
    public int countLikeOnPost(int postID)
    {
        int count=0;
        try
        {
            String query="select count(*) from likes where pid=?";
            PreparedStatement pstmt=this.con.prepareStatement(query);
            pstmt.setInt(1, postID);
            ResultSet result= pstmt.executeQuery();
            if(result.next())
            {
                count=result.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return count;
    }
    public boolean isLikedByUser(int userID,int postID)
    {
        boolean isLiked=false;
        try{
            PreparedStatement pstmt=this.con.prepareStatement("select * from likes where pid=? and uid=?");
            pstmt.setInt(1, postID);
            pstmt.setInt(2, userID);
            ResultSet result=pstmt.executeQuery();
            if(result.next())
            {
                isLiked=true;
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isLiked;
    }
    public boolean deleteLike(int userID,int postID)
    {
        boolean isDeleted=false;
        try
        {
            String query="delete from likes where uid=? and pid=?";
            PreparedStatement pstmt=this.con.prepareStatement(query);
            pstmt.setInt(1,userID);
            pstmt.setInt(2, postID);
            pstmt.executeUpdate();
            isDeleted=true;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isDeleted;
    }
    
    
}
