<%@page import="constants.GlobalConstant"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>BSong</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" type="image/png" href="<%=GlobalConstant.URL_PICTURE %>/icon.jpg"/>
<link href="<%=GlobalConstant.URL_PUBLIC %>/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=GlobalConstant.URL_PUBLIC %>/css/coin-slider.css" />
<script type="text/javascript" src="<%=GlobalConstant.URL_PUBLIC %>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=GlobalConstant.URL_PUBLIC %>/js/script.js"></script>
<script type="text/javascript" src="<%=GlobalConstant.URL_PUBLIC %>/js/coin-slider.min.js"></script>
<script type="text/javascript" src="<%=GlobalConstant.URL_PUBLIC %>/js/jquery.raty.min.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="<%=request.getContextPath()%>/home">BSong <small>Một dự án khóa JAVA tại VinaEnter Edu</small></a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li id="index"><a href="<%=request.getContextPath()%>/home"><span>Trang chủ</span></a>
          <li id="contact"><a href="<%=request.getContextPath()%>/lien-he.html"><span>Liên hệ</span></a></li>
          <li><a href="<%=request.getContextPath()%>/login"><span>Đăng nhập</span></a></li>
        </ul>
      </div>
      <div class="clr"></div>
      <div class="slider">
        <div id="coin-slider">
	        <a href="#"><img src="<%=GlobalConstant.URL_PUBLIC %>/images/slide1.jpg" width="935" height="307" alt="" /></a> 
	        <a href="#"><img src="<%=GlobalConstant.URL_PUBLIC %>/images/slide2.jpg" width="935" height="307" alt="" /></a> 
	        <a href="#"><img src="<%=GlobalConstant.URL_PUBLIC %>/images/slide3.jpg" width="935" height="307" alt="" /></a>
        </div>
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">