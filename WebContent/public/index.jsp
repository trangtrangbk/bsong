<%@page import="model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
  	if(request.getAttribute("listSong") != null){
  		int index = 0;
  		ArrayList<Song> listSong = (ArrayList<Song>)request.getAttribute("listSong");
  		if(listSong.size() > 0){
  			for(Song objSong:listSong){
  				String urlSlug = request.getContextPath() + "/chi-tiet/" + StringUtil.makeSlug(objSong.getName()) + "-" + objSong.getId();
  %>
    <div class="article">
      <h2><a href="<%=urlSlug %>" title=""><%=objSong.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=objSong.getDate() %>. Lượt xem: <%=objSong.getCounter() %> <a href="#" class="com"><span><%=++index %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/files/<%=objSong.getPicture() %>" width="177" height="213" alt="" class="fl" /></div>
      <div class="post_content">
        <p><%=objSong.getPre() %></p>
        <p class="spec"><a href="<%=urlSlug%>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
      <%} %>
       <%
                            	int numberOfPages = (Integer)request.getAttribute("numberOfPages");
                           		int currentPage = (Integer)request.getAttribute("currentPage");
                           		if(listSong != null && listSong.size() > 0){
                            %>
    <p class="pages"><small>Trang <%=currentPage %> / <%=numberOfPages %></small>
    <%
    	for(int i =1; i<= numberOfPages; i++){
    		if(i == currentPage) {
    %>
    <span><%=i %></span>
    <%} else{ %>
	<a href="<%=request.getContextPath()%>/public/index?page=<%=i%>"><%=i %></a>
<%}} %>
    <a href="<%=request.getContextPath()%>/public/index?page=<%=currentPage+1%>">&raquo;</a></p>
  </div>
<%}}} %>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
