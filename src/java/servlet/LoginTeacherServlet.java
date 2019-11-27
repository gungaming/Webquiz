/*  5555555555555
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Students;
import model.Teacher;
import model.controller.StudentsController;
import model.controller.TeacherController;
/////
/**
 *
 * @author GunPc
 */
public class LoginTeacherServlet extends HttpServlet {

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
       getServletContext().getRequestDispatcher("/TeacherLogin.jsp").forward(request, response);
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
        
        System.out.println("username : " + request.getParameter("username"));
        System.out.println("password : " + request.getParameter("password"));

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("msg", "Please input username and password");
            getServletContext().getRequestDispatcher("/TeacherLogin.jsp").forward(request, response);
        } else {
            TeacherController tc = new TeacherController();
            Teacher t = tc.findByUsername(username);

            if (t == null) {               
                request.setAttribute("msg", "Not teacher");
                getServletContext().getRequestDispatcher("/TeacherLogin.jsp").forward(request, response);
            } else {
                System.out.println(t);
                if (t.getUsername().equals(username) && t.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("teacher", t);
                    response.sendRedirect("/Webquiz/TeacherFunction.jsp");
                } else {
                    request.setAttribute("msg", "Error");
                    getServletContext().getRequestDispatcher("/TeacherLogin.jsp").forward(request, response);
                }
            }
            
        }
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
