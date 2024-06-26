<%@page import="com.tech.blog.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<%@page import="com.tech.blog.entities.Message"%>
<%@page import="com.tech.blog.entities.User"%>
<%@page errorPage="error_page.jsp" %>
<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login_page.jsp");
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark primary-background ">
            <a class="navbar-brand" href="index.jsp"><span class="fa fa-asterisk"></span> Tech Blog</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class="fa fa-home"</span> Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="fa fa-check-square-o"</span> Categories
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Programming Language</a>
                            <a class="dropdown-item" href="#">Project Implementation</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Data Structures</a>
                        </div>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#add-post-modal"><span class="fa fa-asterisk"></span>Add Post</a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link" href="#">Link</a>
                    </li>
                </ul>
                <ul class="navbar-nav mr-right">
                    <li class="nav-item" >
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#profile-modal" ><span class="fa fa-user "></span><%= user.getName()%></a>
                    </li>
                    <li class="nav-item" >
                        <a class="nav-link" href="LogoutServlet" ><span class="fa fa-user-circle "></span>Log out</a>
                    </li>
                </ul>
            </div>
        </nav>

        <%
            Message msg = (Message) session.getAttribute("msg");
            if (msg != null) {
        %>
        <div class="alert <%=msg.getCssClass()%>" role="alert">
            <%= msg.getContent()%>
        </div>
        <%
                session.removeAttribute("msg");
            }
        %>

        <!-- main body -->
        <main>
            <div class="container">
                <div class="row mt-4">
                    <div class="col-md-4">
                        <!---categories-->
                        <div class="list-group">
                            <a href="#" onclick="getPosts(0,this)" class="c-link list-group-item list-group-item-action active">
                                All Posts
                            </a>
                            
                            <%
                                
                                PostDao dao=new PostDao(ConnectionProvider.getConnection());
                                ArrayList<Category> categories=dao.getCategories();
                                for(Category category:categories)
                                {
                                
                             %>
                             <a href="#" onclick="getPosts(<%= category.getIdCategory() %>,this)" class="c-link list-group-item list-group-item-action"><%= category.getName()%></a>
                            <%
                                }
                             %>
                       </div>

                    </div>
                    <div class="col-md-8" >
                        
                        <!--posts--->
                        <div class="container text-center" id="loader">
                            <i class="fa fa-refresh fa-3x fa-spin"></i>
                            <h3 class="mt-2">Loading...</h3>
                        </div>
                        <div class="container-fluid" id="post-container">
                            
                        </div>
                    </div>
                </div>
            </div>
        </main>


        <!-- end of main body -->




        <!-- profile Modal -->
        <div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header primary-background text-white text-center">
                        <h5 class="modal-title" id="exampleModalLabel">Profile</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <img src="ProfilePics/<%= user.getProfile()%>" style="border-radius:50%;max-width: 150px">
                            <h5 class="modal-title" id="exampleModalLabel"> <%= user.getName()%></h5>

                            <!-- details -->
                            <div id="profile-details">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th scope="row">ID : </th>
                                            <td><%= user.getId()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Email : </th>
                                            <td><%= user.getEmail()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Gender :</th>
                                            <td><%= user.getGender()%> </td>

                                        </tr>
                                        <tr>
                                            <th scope="row">User Type :</th>
                                            <td><%= user.getUserType()%> </td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Registered on :</th>
                                            <td><%= user.getDateTime().toString()%> </td>

                                        </tr>

                                    </tbody>
                                </table>
                            </div>

                            <div id="profile-edit" style="display: none">
                                <h4 class="mt-2">Update the details</h4>
                                <form action="EditServlet" method="POST" enctype="multipart/form-data">
                                    <table class="table">
                                        <tr>
                                            <td>ID : </td>
                                            <td><%= user.getId()%></td>
                                        </tr>
                                        <tr>
                                            <td>Gender : </td>
                                            <td><%= user.getGender().toUpperCase()%></td>
                                        </tr>
                                        <tr>
                                            <td>Name : </td>
                                            <td> <input type="text" name="user_name" class="form-control" value="<%= user.getName()%>"></td>
                                        </tr>

                                        <tr>
                                            <td>Email : </td>
                                            <td> <input type="email" name="user_email" class="form-control" value="<%= user.getEmail()%>"></td>
                                        </tr>
                                        <tr>
                                            <td>Password: </td>
                                            <td> <input type="password" name="user_password" class="form-control" value="<%= user.getPassword()%>"></td>
                                        </tr>
                                        <tr>
                                            <td>User Type </td>
                                            <td> <select class="form-control" id="userType" name="userType" value="<%= user.getUserType()%>" >
                                                    <option>School Student</option>
                                                    <option>College Student</option>
                                                    <option>Working Professional</option>
                                                </select></td>
                                        </tr>
                                        <tr>
                                            <td>Profile pic </td>
                                            <td> <input type="file" name="profilePic" class="form-control"></td>
                                        </tr>

                                    </table>
                                    <div class="container">
                                        <button type="submit" class="btn btn-outline-primary primary-background text-white">Save</button>
                                    </div>
                                </form>
                            </div>



                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary primary-background text-white" data-dismiss="modal">Close</button>
                        <button id="edit-profile-btn" type="button" class="btn btn-primary primary-background text-white">Edit</button>
                    </div>
                </div>
            </div>
        </div>


        <!-- post modal -->
        <div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add Post</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="add-post-form" action="AddPostServlet" method="post">
                            <div class="form-group">
                                <select class="form-control" name="catID">
                                    <option selected disabled>----Select Category----</option>
                                    <%
                                        PostDao postd = new PostDao(ConnectionProvider.getConnection());
                                        ArrayList<Category> list = postd.getCategories();
                                        for (Category c : list) {
                                    %>
                                    <option value="<%= c.getIdCategory()%>"><%= c.getName()%></option>

                                    <%
                                        }
                                    %>

                                </select>

                            </div>

                            <div class="form-group">
                                <input type="text" name="pTitle" placeholder="Enter the post title" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" name="pContent" style="height: 200px;" placeholder="Enter your content"></textarea>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" name="pCode" style="height: 200px;" placeholder="Enter your related code(if any)"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pPic">Attach related pic</label>
                                <br>
                                <input id="pPic" type="file" name="pPic">

                            </div>
                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-primary primary-background text-white">Post</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <!-- javascripts -->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script src="js/myjs.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {
                let editStatus = false;
                $('#edit-profile-btn').click(function () {
                    if (editStatus == false) {
                        $('#profile-details').hide();
                        $('#profile-edit').show();
                        editStatus = true;
                        $(this).text("Back");
                    } else
                    {
                        $('#profile-details').show();
                        $('#profile-edit').hide();
                        editStatus = false;
                        $(this).text("Edit");
                    }
                });
            });
        </script>
        <!-- after submission of add post modal -->
        <script>
            $(document).ready(function () {
                $('#add-post-form').on("submit", function (event) {
                    event.preventDefault();
                    let form = new FormData(this);
                    //now requesting to server
                    $.ajax({
                        url: "AddPostServlet",
                        type: 'POST',
                        data: form,
                        success: function (data, textStatus, jqXHR) {
                            //SUCCESS
                            console.log(data);
                            if (data.trim() == 'success')
                            {
                                swal("Good Job!", "saved successfully", "success");
                            } else
                            {
                                swal("Error!!", "Something went wrong try again...", "error");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            //error
                            swal("Error!!", "Something went wrong try again...", "error");
                        },
                        processData: false,
                        contentType: false
                    });
                });
            });
        </script>
        <!-- loading post using ajax -->
        <script>
            function getPosts(catID,clickedRef){
                $("#loader").show();
                $("#post-container").hide();
                $(".c-link").removeClass('active');
                 $.ajax({
                    url: "load_posts.jsp",
                    data: {catID: catID},
                    success: function(data,textStatus,jqXHR){
                        console.log(data);
                        $("#loader").hide();
                         $("#post-container").show();
                        $("#post-container").html(data);
                        $(clickedRef).addClass('active');
                         $(clickedRef).addClass('primary-background');
                    }
                });
            } 
            $(document).ready(function (e){
                let AllPostRef=$('.c-link')[0]
                getPosts(0,AllPostRef);
            });
            
        </script>

    </body>
</html>
