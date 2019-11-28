<%@page import="model.bean.Contact"%>
<%@page import="model.bean.Category"%>
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
                <h2>Quản lý liên hệ</h2>
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
                                <div class="col-sm-6" style="text-align: right;">
                                   
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
                                        <th>Tên</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Message</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <%
  									if(request.getAttribute("contacts") != null){
  									int index = 0;
  									 ArrayList<Contact> listCon = (ArrayList<Contact>)request.getAttribute("contacts");
  										if(listCon.size() > 0){
  										for(Contact objCon:listCon){
  											
   													%>
                                    <tr>
                                        <td><%=objCon.getId() %></td>
                                        <td class="center"><%=objCon.getName() %></td>
                                        <td class="center"><%=objCon.getEmail() %></td>
                                        <td class="center"><%=objCon.getWebsite() %></td>
                                        <td class="center"><%=objCon.getMessage() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/contact/del?id=<%=objCon.getId() %>" onclick="return confirm('Bạn có muốn xóa?')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%}}} %>
                                </tbody>
                            </table>
                           <%
                            	int numberOfPages =(Integer)(request.getAttribute("numberOfPages"));
                           		int currentPages =(Integer)(request.getAttribute("currentPage"));

                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Trang <%=currentPages %> / <%=numberOfPages %></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=currentPages-1%>">Trang trước</a></li>
                                           
                                           	   <%
                                           String active ="";
                                           for(int i = 1; i <= numberOfPages ; i++){
                                        	   if(currentPages == i)
                                           			active = "active";
                                        	   else active = "";
                                           %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=i%>"><%=i %></a></li>
											 <%} %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=currentPages+1%>">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>