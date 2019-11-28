<%@page import="model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    	<%
    	Category category= (Category) request.getAttribute("category");
    	if(category !=null){
    
    %>
		<h1><%=category.getName() %></h1>
		<% } %>
    </div>
    <%
	if(request.getAttribute("list")!= null){
  		ArrayList<Song> list = (ArrayList<Song>)request.getAttribute("list");
  		
  		if(list.size() > 0){
  			int stt = 1;
  			for(Song objS:list){
  				String urlSlug = request.getContextPath() + "/chi-tiet/" + StringUtil.makeSlug(objS.getName()) + "-" + objS.getId();
    %>
    <div class="article">
      <h2><a href="<%=urlSlug %>" title=""><%=objS.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=objS.getDate() %>. Lượt xem: <%=objS.getCounter() %> <a href="#" class="com"><span><%=stt++ %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/files/<%=objS.getPicture() %>" width="177" height="213" alt="" class="fl" /></div>
      <div class="post_content">
        <p> <%=objS.getPre() %></p>
        <p class="spec"><a href="<%=urlSlug %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%}} %>
    <%
    int numberOfPages = (Integer) request.getAttribute("numberOfPages");
    int currentPages = (Integer) request.getAttribute("currentPage");
    if(list != null && list.size() > 0){
    %>
    <p class="pages"><small>Trang <%=currentPages %> của <%=numberOfPages %></small>
    <%
    	for(int i = 1 ; i <= numberOfPages; i++){
    		if(currentPages == i){
    %>
    <span><%=i %></span>
    <%}else{ %>
    <a href="<%= request.getContextPath()%>/public/cat?id=<%=category.getId()%>&page=<%=i%>"><%=i %></a>
    <%} }%>
    <a href="<%=request.getContextPath()%>/public/cat?id=<%=category.getId()%>&page=<%=currentPages+1%>">&raquo;</a></p>
    <%} %>
  </div>
  <%} %>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>