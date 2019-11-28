<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
	<div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr>
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                        <%
                                   if(request.getAttribute("user") != null){
                                	   User objU = (User)request.getAttribute("user");
                                   
                                   %> 
                            <div class="col-md-6">
                                 <form action="<%=request.getContextPath() %>/admin/user/edit?id=<%=objU.getId()  %>&oldName=<%=objU.getUsername() %>" role="form" method="POST">
                                   
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" value="<%=objU.getUsername() %>" type="text" name="username" class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label>Password</label>
                                        <input placeholder="" value="" type="password" name="password" class="form-control" required="required">
                                    </div>

                                    <div class="form-group">
                                        <label>Full name</label>
                                        <input required="required" value="<%=objU.getFullname() %>" type="text" name="fullname" class="form-control">
                                    </div>
                                    
                                    <hr style="margin-top:30px;">
                                    <input type="submit" name="edit" class="btn btn-success btn-md" value="Sửa">
									<%} %>
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
    </div>
<%@ include file="/templates/admin/inc/footer.jsp" %>


