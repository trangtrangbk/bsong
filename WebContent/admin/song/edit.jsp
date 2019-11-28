<%@page import="model.bean.Song"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
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
                                <form action="" role="form" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                    <%
                                    	String picture = "";
                                    	if(request.getAttribute("song")!=null){
                                    		Song song = (Song) request.getAttribute("song");
                                    		picture = song.getPicture();
                                    	
                                    %>
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%=song.getName() %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="catId" class="form-control">
                                        <%
                                        	if(request.getAttribute("listCat") !=null){
                                        		int cat_id = (Integer)request.getAttribute("oldId");
                                        		ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
                                        		if(listCat.size() > 0){
                                        			String selected = "";
                                        			for(Category objCat:listCat){	
                                        				if(objCat.getId() == cat_id ) selected ="selected = 'selected'";
                                        				else selected ="";
                                        %>
	                                        <option value="<%=objCat.getId()%>" <%=selected %>><%=objCat.getName() %></option>
										<%}}} %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                        <%
                                        	if(!picture.isEmpty()){
                                        %>
                                        <br>
                                        <img width = "200px" height = "200px" alt="picture"
                                         src="<%=request.getContextPath() %>/<%=DefineUtil.DIR_UPLOAD %>/<%=picture %>">
                                         <%} %>
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%=song.getPre() %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail"><%=song.getDetail() %></textarea>
                                    </div>
                                    <%} %>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>