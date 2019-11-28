﻿<%@page import="model.bean.Category"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath() %>/admin/song/index">
                                        <input type="submit" name="" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name ="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
							<%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch(msg){
									case 0: out.print("ERROR"); break;
									case 1: out.print("Thêm thành công"); break;
									case 2: out.print("Sửa thành công"); break;
									case 3: out.print("Xóa thành công"); break;
									}
								}
							%>	
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <%
  									if(request.getAttribute("listSong") != null){
  									int index = 0;
  									 ArrayList<Song> listSong = (ArrayList<Song>)request.getAttribute("listSong");
  										if(listSong.size() > 0){
  										for(Song objSong:listSong){
  											CategoryDAO catDAO = new CategoryDAO();
  											Category objCat = catDAO.getItem(objSong.getCat_id());
  											String category = null;
  											if(objCat != null)  category = objCat.getName();
   													%>
                                    <tr>
                                        <td><%=objSong.getId()%></td>
                                        <td class="center"><%=objSong.getName() %></td>
                                        <td class="center"><%=category %></td>
                                        <td class="center"><%=objSong.getCounter() %></td>
                                        <td class="center">
                                        <%
                                        	if(!"".equals(objSong.getPicture())){
                                        %>
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/<%=DefineUtil.DIR_UPLOAD %>/<%=objSong.getPicture() %>" />
											<%}else{ %>
											<img width="200px" height="200px" src="<%=DefineUtil.URL_ADMIN%>/display/none.png" />
											<%} %>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath()%>/admin/song/edit?id=<%=objSong.getId() %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/song/del?id=<%=objSong.getId() %>" onclick="return confirm('Bạn có muốn xóa?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%} %>
                                </tbody>
                            </table>
                            <%
                            	int numberOfPages = (Integer)request.getAttribute("numberOfPages");
                           		int currentPage = (Integer)request.getAttribute("currentPage");
                           		if(listSong != null && listSong.size() > 0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Trang <%=currentPage %> / <%=numberOfPages %></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%=currentPage-1%>">Trang trước</a></li>
                                            <%
                                            	String active = "";
                                            	for(int i = 1;i<= numberOfPages;i++){
                                            		if(i == currentPage) active = "active";
                                            		else active = "";
                                            %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%=i%>"><%=i %></a></li>
											<%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%=currentPage+1%>">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%} %>
                        </div>
<%}} %>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>