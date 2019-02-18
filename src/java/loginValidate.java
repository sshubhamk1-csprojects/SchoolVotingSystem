/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author shubh
 */
@WebServlet(urlPatterns = {"/loginValidate"})
public class loginValidate extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("uname")!=null && request.getParameter("pword")!=null){
                try{
                    Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/shubh", "radhika", "encrypt");
                    String strSQL= new String("SELECT REGID,PASSWORD FROM RADHIKA.STUDENTS WHERE REGID=?");
                    PreparedStatement pstmt= conn.prepareStatement(strSQL);
                    pstmt.setLong(1,Long.parseLong(request.getParameter("uname")));
                    ResultSet a= pstmt.executeQuery();
                    if(a.next()){
                        if(request.getParameter("pword").equals(a.getString("PASSWORD"))){
                            HttpSession st = request.getSession();
                            st.setAttribute("uname",request.getParameter("uname"));
                            String cookies=loginValidate.generateRandomString();
                            st.setAttribute("cookies",cookies);
                            pstmt= conn.prepareStatement("UPDATE RADHIKA.STUDENTS SET SESSION=? where REGID=?");
                            pstmt.setString(1,cookies);
                            pstmt.setLong(2,Long.parseLong(request.getParameter("uname")) );
                            int ans= pstmt.executeUpdate();
                            response.sendRedirect("welcome.jsp");

                        }
                        else{
                            out.println("Incorrect credential . <a href='index.jsp'>click here</a> to go to login page!");
                        }
                    }
                    else{
                            out.println("Incorrect credential . <a href='index.jsp'>click here</a> to go to login page!");
  
                    }
                }
                catch(SQLException s){
                    response.sendRedirect("index.jsp");
                }
                


            }
            else{
                response.sendRedirect("index.jsp");
            }
        }
    }
 
    private static final String CHAR_LIST = 
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;
     
    /**
     * This method generates random string
     * @return
     */
    public static String generateRandomString(){
         
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
     
    /**
     * This method generates random numbers
     * @return int
     */
    private static int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginValidate.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginValidate.class.getName()).log(Level.SEVERE, null, ex);
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
