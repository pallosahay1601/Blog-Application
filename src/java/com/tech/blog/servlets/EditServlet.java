/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tech.blog.servlets;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author pallavi
 */
@MultipartConfig
public class EditServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServlet</title>");
            out.println("</head>");
            out.println("<body>");
            //fetch the edited data
            String userName = request.getParameter("user_name");
            String userEmail = request.getParameter("user_email");
            String userPassword = request.getParameter("user_password");
            String userType = request.getParameter("userType");
            Part profilePic = request.getPart("profilePic");
            String imageName = profilePic.getSubmittedFileName();

            //Get the user from the session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");
            String prevProfile = user.getProfile();
            user.setName(userName);
            user.setEmail(userEmail);
            user.setPassword(userPassword);
            user.setUserType(userType);
            if (!imageName.equals("")) {
                user.setProfile(imageName);
            }

            UserDao userdao = new UserDao(ConnectionProvider.getConnection());
            if (userdao.updateUser(user)) {
                if (!imageName.equals("")) {
                    String oldProfilePath = request.getRealPath("/") + "ProfilePics" + File.separator + prevProfile;
                    String newProfilePath = request.getRealPath("/") + "ProfilePics" + File.separator + user.getProfile();
                    Helper.deleteFile(oldProfilePath);
                    if (Helper.saveFile(profilePic.getInputStream(), newProfilePath)) {

                        session.setAttribute("currentUser", user);
                        Message msg = new Message("Profile details updated", "success", "alert-success");
                        session.setAttribute("msg", msg);

                    } else {
                        Message msg = new Message("ProfilePic didn't get updated, something went wrong", "error", "alert-error");
                        session.setAttribute("msg", msg);
                    }
                } else {
                    session.setAttribute("currentUser", user);
                    Message msg = new Message("Profile details updated", "success", "alert-success");
                    session.setAttribute("msg", msg);
                }

            } else {
                Message msg = new Message("Something went wrong", "error", "alert-error");
                session.setAttribute("msg", msg);

            }

            response.sendRedirect("profile.jsp");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
