<%@page import="util.StringUtil"%>
<%@page import="model.bean.Song"%>
<%@page import="model.dao.SongDAO"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.DefineUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="post" action="<%=request.getContextPath()%>/public/index">
    <span>
    <input name="search" class="editbox_search" id="editbox_search" maxlength="80" placeholder="Nhập tên bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath() %>/templates/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <%
  	CategoryDAO catDAO = new CategoryDAO();
  	ArrayList<Category> listCat = catDAO.getItems();
  	if(listCat.size() > 0){
  		for (Category objCat : listCat) {
  			String urlSlug = request.getContextPath() + "/danh-muc/" + StringUtil.makeSlug(objCat.getName()) + "-" +objCat.getId(); 
  		//	urlSlug = StringUtil.makeSlug(urlSlug);
  %>
  <ul class="sb_menu">
    <li><a href="<%=urlSlug%>"><%=objCat.getName() %></a></li>
   
  </ul>
  <%}} else{ %>
  <ul class="sb_menu">
    <li><a href="#">Danh sách rỗng</a></li>
   
  </ul>
  <%} %>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  <%
  	SongDAO songDAO = new SongDAO();
  	ArrayList<Song> listNew = songDAO.getListNew();
  	if(listNew.size() > 0){
  		for(Song objSong:listNew){
  			String urlSlug = request.getContextPath() + "/chi-tiet/" + StringUtil.makeSlug(objSong.getName()) + "-" +objSong.getId();
  %>
    <li><a href="<%=urlSlug%>"><%=objSong.getName() %></a><br />
     <%=objSong.getPre() %></li>
      <%}} %>
  </ul>
</div>