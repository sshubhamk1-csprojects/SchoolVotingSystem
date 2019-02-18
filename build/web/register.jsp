<%-- 
    Document   : register
    Created on : Nov 20, 2018, 10:34:31 PM
    Author     : shubh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession st = request.getSession();
    if(st.getAttribute("uname")!=null){
        response.sendRedirect("./welcome.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <link href="css/css.css" rel="stylesheet"/>
        <title>Register</title>
    </head>
    <body style="background-color: #525252;">
        <div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Register first to vote <small>It's free!</small></h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form"name="getRegistered" action="registerValidate" method="POST">
                                                <div class="form-group">
			    				<input type="text" name="uname"  class="form-control input-sm" placeholder="Registration Number">
			    			</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="firstName" id="first_name" class="form-control input-sm" placeholder="First Name">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="lastName" id="last_name" class="form-control input-sm" placeholder="Last Name">
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group">
			    				<input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address">
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="password" name="pword" id="password" class="form-control input-sm" placeholder="Password">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="password"  name="pword_d"id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password">
			    					</div>
			    				</div>
			    			</div>
                                            <div class="form-group row" >
                                                <div class=col-sm-12 col-xs-12 col-md-12>Standing in election?
                                                </div>
                                                        <div class="col-xs-2 col-sm-2 col-md-2">
                                                            <div class="form-group">
			    				<input type="radio" name="standing" id="radio" value="YES" class="form-control input-sm">
                                                            </div>
                                                        </div>
                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                    YES
                                                </div>
                                                <div class="col-xs-2 col-sm-2 col-md-2">
                                                    <input type="radio" name="standing" id="radio" value="NO"class="form-control input-sm" checked>
                                                </div>
                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                    NO
                                                </div>
			    			</div>
			    			
			    			<input type="submit" value="Register" class="btn btn-info btn-block">
			    		
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
    </body>
</html>
