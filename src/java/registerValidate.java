/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author shubh
 */
@WebServlet(urlPatterns = {"/registerValidate"})
public class registerValidate extends HttpServlet {
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
            
            /*while(rslt.next()){
                long regid = rslt.getLong("REGID");
                String firstName = rslt.getString("FIRSTNAME");
                String lastName= rslt.getString("LASTNAME");
                boolean voted = rslt.getBoolean("VOTED");
                String pwd= rslt.getString("PASSWORD");
                out.println(firstName+" "+ lastName+" "+regid+" "+voted+" "+pwd);
            }*/
            /* TODO output your page here. You may use following sample code. */
            if("".equals(request.getParameter("uname"))){
                out.println("Error in registration number!");
            }
            else if("".equals(request.getParameter("firstName"))){
                out.println("Error in first name");
            }
            else if("".equals(request.getParameter("lastName"))){
                out.println("Error in last Name");
            }
            else if("".equals(request.getParameter("email"))){
                out.println("Error in email ID");
            }
            else if(!(request.getParameter("pword").equals(request.getParameter("pword_d")))){
                out.println("Password not matched");
            }
            else if("".equals(request.getParameter("pword"))){
                out.println("password is empty");
            }
            else{
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/shubh", "radhika", "encrypt");
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt= conn.prepareStatement("INSERT INTO RADHIKA.STUDENTS (REGID,FIRSTNAME,LASTNAME,VOTED,PASSWORD) VALUES (?,?,?,?,?)");
                pstmt.setLong(1,Long.parseLong(request.getParameter("uname")) );
                pstmt.setString(2,request.getParameter("firstName") );
                pstmt.setString(3,request.getParameter("lastName"));
                pstmt.setBoolean(4,"NO".equals(request.getParameter("standing"))?false:true);
                pstmt.setString(5, request.getParameter("pword"));
                try{
                    int a = pstmt.executeUpdate();
                    //ResultSet rs= pstmt.executeQuery();
                    out.println("You are registered!<br/><br/><a href='index.jsp'>Click here</a> to go to login page");
                }
                catch(SQLException c){
                    out.println("Something wrong happened. Check your information correctly or contact to our adminstrator! ");
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
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
