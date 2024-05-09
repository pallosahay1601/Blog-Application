package com.tech.blog.dao;
import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PostDao {
   Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }

   
    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> list=new ArrayList<>();
        try
        {
            String query="select * from categories";
            Statement st=this.con.createStatement();
            ResultSet categories=st.executeQuery(query);
            while(categories.next())
            {
                int idCategory=categories.getInt("idCategory");
                String name=categories.getString("name");
                String description=categories.getString("description");
                Category category=new Category(idCategory,name,description);
                System.out.println(name);
                list.add(category);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
        
    }
    public boolean savePost(Post post)
    {
        boolean isSaved=false;
        try{
            String query="insert into posts (pTitle,pContent,pCode,pPic,catID,userID) values(?,?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, post.getpTitle());
            pstmt.setString(2, post.getpContent());
            pstmt.setString(3, post.getpCode());
            pstmt.setString(4, post.getpPic());
            pstmt.setInt(5, post.getCatID());
            pstmt.setInt(6, post.getUserID());
            pstmt.executeUpdate();
            isSaved=true;
            return isSaved;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return isSaved;
    }
    public List<Post> getAllPosts()
    {
        List<Post> list=new ArrayList<>();
        try
        {
            PreparedStatement pstmt=con.prepareStatement("select * from posts order by pDate desc");
            ResultSet result=pstmt.executeQuery();
            while(result.next())
            {
                int idPost=result.getInt("idPost");
                String pTitle=result.getString("pTitle");
                String pContent=result.getString("pContent");
                String pCode=result.getString("pCode");
                Timestamp pDate=result.getTimestamp("pDate");
                String pPic=result.getString("pPic");
                int catID=result.getInt("catID");
                int userID=result.getInt("userID");
                Post post=new Post(idPost,pTitle,pContent,pCode,pPic,pDate,catID,userID);
                list.add(post);
                
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    public List<Post> getPostsByCatID(int catID)
    {
         List<Post> list=new ArrayList<>();
          try
        {
             PreparedStatement pstmt=con.prepareStatement("select * from posts where catID=?");
             pstmt.setInt(1,catID);
            ResultSet result=pstmt.executeQuery();
            while(result.next())
            {
                int idPost=result.getInt("idPost");
                String pTitle=result.getString("pTitle");
                String pContent=result.getString("pContent");
                String pCode=result.getString("pCode");
                Timestamp pDate=result.getTimestamp("pDate");
                String pPic=result.getString("pPic");
                int userID=result.getInt("userID");
                Post post=new Post(idPost,pTitle,pContent,pCode,pPic,pDate,catID,userID);
                list.add(post);
                
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    public Post getPostByID(int postID)
    {
        Post post=null;
        try
        {
             PreparedStatement pstmt=con.prepareStatement("select * from posts where idPost=?");
             pstmt.setInt(1,postID);
            ResultSet result=pstmt.executeQuery();
            while(result.next())
            {
                int idPost=result.getInt("idPost");
                String pTitle=result.getString("pTitle");
                String pContent=result.getString("pContent");
                String pCode=result.getString("pCode");
                Timestamp pDate=result.getTimestamp("pDate");
                String pPic=result.getString("pPic");
                int catID=result.getInt("catID");
                int userID=result.getInt("userID");
                post=new Post(idPost,pTitle,pContent,pCode,pPic,pDate,catID,userID);
                
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return post;
    }
    
}
