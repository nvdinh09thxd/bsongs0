<%@page import="java.util.List"%>
<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý người dùng</h2>
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
                                    <a href="<%=request.getContextPath() %>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <br /><br />
                            </div>
							<%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch(msg){
										case 0: out.print("<p style='background: yellow; color: red'>Có lỗi trong quá trình xử lý!</p>");
										break;
										case 1: out.print("<p style='background: yellow; color: green'>Thêm thành công!</p>");
										break;
										case 2: out.print("<p style='background: yellow; color: green'>Sửa thành công!</p>");
										break;
										case 3: out.print("<p style='background: yellow; color: green'>Xóa thành công!</p>");
										break;
										case 4: out.print("<p style='background: yellow; color: red'>ID không tồn tại!</p>");
										break;
										case 5: out.print("<p style='background: yellow; color: red'>Không có quyền thêm!</p>");
										break;
										case 6: out.print("<p style='background: yellow; color: red'>Không có quyền sửa!</p>");
										break;
										case 7: out.print("<p style='background: yellow; color: red'>Không có quyền xóa!</p>");
										break;
									}
								}
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên đăng nhập</th>
                                        <th>Họ tên</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
	                                	@SuppressWarnings("unchecked")
	                                	List<User> listUsers = (List<User>) request.getAttribute("listUsers");
	                                	if(listUsers!=null && listUsers.size()>0){
	                                		for(User objItem : listUsers){
                                %>
                                    <tr>
                                        <td><%=objItem.getId() %></td>
                                        <td class="center"><%=objItem.getUsername() %></td>
                                        <td class="center"><%=objItem.getFullname() %></td>
                                        <%
                                        User userLogin = (User) session.getAttribute("userLogin");
                                        if("admin".equals(userLogin.getUsername())){
                                        %>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/user/edit?id=<%=objItem.getId() %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/user/del?id=<%=objItem.getId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                        <%} else { %>
                                        <td class="center">
                                        <%if(userLogin.getId()==objItem.getId()){ %>
                                            <a href="<%=request.getContextPath() %>/admin/user/edit?id=<%=objItem.getId() %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        <%} %>
                                        </td>
                                        <%} %>
                                    </tr>
									<% }
									} else {
										out.print("Khong co du lieu!");
									}%>
									
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>