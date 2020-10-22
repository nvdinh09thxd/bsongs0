<%@page import="models.User"%>
<%@page import="util.DefineUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>AdminCP | VinaEnter Edu</title>
    <!-- Icon -->
    <link rel="icon" type="image/png" href="<%=DefineUtil.URL_PICTURE %>/icon.jpg"/>
    <!-- BOOTSTRAP STYLES-->
    <link href="<%=DefineUtil.URL_ADMIN%>/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="<%=DefineUtil.URL_ADMIN%>/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="<%=DefineUtil.URL_ADMIN%>/assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <script type="text/javascript" src="<%=DefineUtil.URL_ADMIN %>/ckeditor/ckeditor.js"></script>
    <!-- JQUERY -->
	<script src="<%=DefineUtil.URL_ADMIN%>/assets/js/jquery-3.2.1.js"></script>
	<script src="<%=DefineUtil.URL_ADMIN%>/assets/js/jquery.validate.min.js"></script>
</head>

<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/home">VinaEnter Edu</a>
            </div>
            <%
            	if(session.getAttribute("userLogin")!=null){
            		User userLogin = (User) session.getAttribute("userLogin");
            %>
            <div style="color: white;padding: 15px 50px 5px 50px;float: right;font-size: 16px;"> Xin chào, <b><%=userLogin.getFullname() %></b> &nbsp; <a href="<%=request.getContextPath() %>/logout" class="btn btn-danger square-btn-adjust">Đăng xuất</a> </div>
            <%} else { %>
            <div style="color: white;padding: 15px 50px 5px 50px;float: right;font-size: 16px;"> Xin chào, <b>Khách</b> &nbsp; <a href="<%=request.getContextPath() %>/login" class="btn btn-danger square-btn-adjust">Đăng nhập</a> </div>
            <%} %>
        </nav>
        <!-- /. NAV TOP  -->