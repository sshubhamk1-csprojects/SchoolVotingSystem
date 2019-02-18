<%-- 
    Document   : Welcome
    Created on : Nov 20, 2018, 11:52:24 AM
    Author     : shubh
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    String firstName;
    String lastName;
    long regid;
    boolean voted;
    long votedFor;
    HttpSession st;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet a;
    Statement stmt;
%>
<%
    st = request.getSession();
    if(st.getAttribute("uname")==null){
        response.sendRedirect("./index.jsp");
    }
    try{
                    conn = DriverManager.getConnection("jdbc:derby://localhost:1527/shubh", "radhika", "encrypt");
                    String strSQL= new String("SELECT * FROM RADHIKA.STUDENTS WHERE REGID=? and SESSION=?");
                    pstmt= conn.prepareStatement(strSQL);
                    pstmt.setLong(1,Long.parseLong(st.getAttribute("uname").toString()));
                    pstmt.setString(2,(st.getAttribute("cookies")).toString());
                    a= pstmt.executeQuery();
                    while(a.next()){
                        regid = a.getLong("REGID");
                        firstName=a.getString("FIRSTNAME");
                        lastName=a.getString("LASTNAME");
                        voted=a.getBoolean("VOTED");
                        votedFor = a.getLong("VOTEDFOR");
                    }
                }
                catch(SQLException s){
                    out.println("Something went Wrong!"+ s);
                }
%>
<!DOCTYPE html>
<html lang="en">
<head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
            <link rel="stylesheet" href="css/css.css"/>
<!------ Include the above in your HEAD tag ---------->
    </head>
    <body>
        <div class="jumbotron text-center">
                    <h1>Welcome, <%=firstName%></h1>
        </div>
        <div class="container">
            <div class="rows">
                <div class="col-sm-8">
                    <h1>Welcome, <%=st.getAttribute("uname")%></h1>
                    <%
                        if(votedFor==0){
                            out.println("Not voted yet<br/>");
                    %>
                    Vote for
                    <form action="vote" method="POST">
                        <select name="toVote">
                            <%
                                pstmt = conn.prepareStatement("SELECT REGID,FIRSTNAME from RADHIKA.STUDENTS WHERE VOTED=TRUE");
                                a = pstmt.executeQuery();
                                while(a.next()){
                                    out.println("<option value="+a.getLong("REGID")+">"+a.getLong("REGID")+"["+a.getString("FIRSTNAME")+"]</option>");
                                }
                            %>
                        </select>
                        <input type="submit" value="Vote"/>
                    </form>
                            <%
                                                        }
                        else{
                            
                            out.println("Already voted for "+votedFor+" <br/>");
                        }
                        %>
                     <br/><br/><br/>
                     <table border="2px">
                         <tr>
                             <th>Registration number</th>
                             <th>Votes</th>
                         </tr>
                         <%
                             stmt=conn.createStatement();
                             a = stmt.executeQuery("SELECT VOTEDFOR,count(VOTEDFOR) from RADHIKA.STUDENTS GROUP BY VOTEDFOR order by count(VOTEDFOR) desc");
                             while(a.next()){
                                 if(a.getLong(1)==0)continue;
                                 //pstmt = conn.prepareStatement("SELECT FIRSTNAME FROM RADHIKA.STUDENTS WHERE REGID=?");
                                 //pstmt.setLong(1,a.getLong(1));
                                 //ResultSet ans=pstmt.executeQuery();
                                 out.println("<tr>");
                                 out.println("<td>"+a.getLong(1)+"</td>");
                                 //out.println("<td>"+ans.getString(1)+"</td>");
                                 out.println("<td>"+a.getInt(2)+"</td>");
                                 out.println("</tr>");
                             } 
                         %>
                     </table>
                    <a href="logout.jsp">Logout</a>
                </div>
            </div>
        </div>
    </body>
</html>
