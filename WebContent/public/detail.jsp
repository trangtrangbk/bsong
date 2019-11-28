<%@page import="model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <%
      if(request.getAttribute("objSong") !=null){
    	  Song objSong = (Song)request.getAttribute("objSong");
      	
      %>
      <h1><%=objSong.getName() %></h1>
      <div class="clr"></div>
      <p>Ngày đăng: <%=objSong.getDate() %>. Lượt xem: <%=objSong.getCounter() %></p>
      <div class="vnecontent">
        <%=objSong.getDetail() %>
      </div>
    </div><%} %>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
      		if(request.getAttribute("listRelative") != null){
    		int index = 0;
    		ArrayList<Song> listRelative = (ArrayList<Song>)request.getAttribute("listRelative");
    		if(listRelative.size() > 0){
    			for(Song objSong:listRelative){
    				String urlSlug = request.getContextPath() + "/chi-tiet/" + StringUtil.makeSlug(objSong.getName()) + "-" + objSong.getId();
      %>
      <div class="comment"> <a href=""><img src="<%=request.getContextPath() %>/files/<%=objSong.getPicture() %>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href="<%=urlSlug%>"><%=objSong.getName() %></a></h2>
        <p><%=objSong.getPre() %></p>
      </div>
      <%}}} %>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
