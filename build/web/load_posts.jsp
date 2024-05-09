<%@page import="com.tech.blog.dao.LikeDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.tech.blog.entities.Post"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<div class="row">
    <%
        PostDao dao = new PostDao(ConnectionProvider.getConnection());
        int catID = Integer.parseInt(request.getParameter("catID"));
        List<Post> posts = null;
        if (catID == 0) {
            posts = dao.getAllPosts();
        } else {
            posts = dao.getPostsByCatID(catID);
        }
        if (posts.size() == 0) {
            out.println("<h3 class='display-3 text-center'>No posts in this category....</h3>");
        }
        for (Post post : posts) {
    %>

    <div class="col-md-6 mt-2">
        <div class="card">
            <div class="card-body">
                <img class="card-img-top" src="blogPics/<%= post.getpPic()%> " alt="Card image cap">
                <b><%= post.getpTitle()%></b>
                <p><%= post.getpContent()%></p>
            </div>
            <div class="card-footer primary-background text-center">
                <a href="show_post.jsp?post_id=<%= post.getIdPost()%>" class="btn btn-outline-light btn-sm">Read more..</a>

                <%
                    LikeDao ldao = new LikeDao(ConnectionProvider.getConnection());
                %>
                <a href="#" class="btn btn-outline-light btn-sm"> <i class="fa fa-thumbs-o-up"></i> <span class="like-counter"><%= ldao.countLikeOnPost(post.getIdPost())%></span></a>
                <a href="#" class="btn btn-outline-light btn-sm"> <i class="fa fa-commenting-o"></i> <span>10</span></a>
            </div>
        </div>
    </div>


    <%
        }
    %>


</div>