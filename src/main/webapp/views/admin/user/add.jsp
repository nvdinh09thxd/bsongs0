<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
        <hr />
        					<%
	        					String username = request.getParameter("username");
	        					String fullname = request.getParameter("fullname");
									if(request.getParameter("msg")!=null){
										int msg = Integer.parseInt(request.getParameter("msg"));
										switch(msg){
											case 0: out.print("<p style='background: yellow; color: red'>Có lỗi trong quá trình xử lý!</p>");
											break;
											case 1: out.print("<p style='background: yellow; color: red'>Vui lòng nhập username vào!</p>");
											break;
											case 2: out.print("<p style='background: yellow; color: red'>Vui lòng nhập password vào!</p>");
											break;
											case 3: out.print("<p style='background: yellow; color: red'>Vui lòng nhập fullname vào!</p>");
											break;
											case 4: out.print("<p style='background: yellow; color: red'>Bạn nhập username đã tồn tại!</p>");
											break;
										}
									}
							%>
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" method="post" id="form">
                                    <div class="form-group">
                                        <label for="name">Username</label>
                                        <input type="text" id="username" value="<%if(username!=null) out.print(username); %>" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Password</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">FullName</label>
                                        <input type="text" id="fullname" value="<%if(fullname!=null) out.print(fullname); %>" name="fullname" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>