<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
<div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
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
                            <div class="col-md-6">
                                                                <form action="<%=request.getContextPath() %>/admin/user/add" role="form" method="POST">
                                    <!-- username -->
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" placeholder="Tên đăng nhập" required="required" type="text" name="username" class="form-control">
                                    </div>
                                    <!-- Password -->
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input placeholder="Mật khẩu" required="required" type="password" name="password" class="form-control">
                                    </div>
                                    <!-- Full name -->
                                    <div class="form-group">
                                        <label>Fullname</label>
                                        <input placeholder="Họ và tên người dùng" required="required" type="text" name="fullname" class="form-control">
                                    </div>
                                    
                                    <hr style="margin-top:30px;">
                                    <input type="submit" name="add" class="btn btn-success btn-md" value="Thêm">
                                    <input type="reset" name="reset" class="btn btn-defaut btn-md" value="Nhập lại">
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



