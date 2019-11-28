<%@page import="util.DefineUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>BSong</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/templates/public/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/css/coin-slider.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/script.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/coin-slider.min.js"></script>
<script  src="<%=request.getContextPath() %>/library/js/jquery.validate.min.js"></script>
<script  src="<%=request.getContextPath() %>/library/ckeditor/ckeditor.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="<%=request.getContextPath() %>/trang-chu">BSong <small>Một dự án khóa JAVA tại VinaEnter Edu</small></a></h1>
      </div>
      <div class="menu_nav">
        <ul>
        <%
        	String activeHome = "";
        	String activeContact="";
        	if(request.getParameter("id") !=null){
        		int id = Integer.parseInt(request.getParameter("id"));
        		if(id ==1) activeHome="active";
        		else activeHome="";
        		if(id == 2) activeContact="active";
        		else activeContact="";
        	}
        %>
          <li class="<%=activeHome%>"><a id = "home" href="<%=request.getContextPath()%>/trang-chu"><span>Trang chủ</span></a>
          <li class="<%=activeContact%>"><a  href="<%=request.getContextPath()%>/public/contact"><span>Liên hệ</span></a></li>
          <li><a href="<%=request.getContextPath()%>/auth/login"><span>Log In</span></a></li>
        </ul>
      </div>
      <div class="clr"></div>
      <div class="slider">
        <div id="coin-slider"><a href="#"><img src="<%=DefineUtil.URL_PUBLIC %>/images/slide1.jpg" width="935" height="307" alt="" /></a> 
        <a href="#"><img src="<%=request.getContextPath() %>/templates/public/images/slide2.jpg" width="935" height="307" alt="" /></a>
		<a href="#"><img src="<%=request.getContextPath() %>/templates/public/images/slide3.jpg" width="935" height="307" alt="" /></a></div>
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">