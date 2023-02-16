<%@page import="models.Contact"%>
<%@page import="models.Category"%>
<%@page import="daos.CatDao"%>
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
							<%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch(msg){
										case 3: out.print("<p style='background: yellow; color: green'>Xóa thành công!</p>");
										break;
										case 4: out.print("<p style='background: yellow; color: red'>ID không tồn tại!</p>");
										break;
										case 0: out.print("<p style='background: yellow; color: red'>Có lỗi trong quá trình xử lý!</p>");
										break;
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
                                        <th width="90px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                		@SuppressWarnings("unchecked")
	                                	ArrayList<Contact> listContacts = (ArrayList<Contact>) request.getAttribute("listContacts");
	                                	if(listContacts!=null && listContacts.size()>0){
	                                		for(Contact objContact : listContacts){
                                %>
                                    <tr>
                                        <td><%=objContact.getId() %></td>
                                        <td class="center"><%=objContact.getName() %></td>
                                        <td class="center"><%=objContact.getEmail() %></td>
                                        <td class="center"><%=objContact.getWebsite() %></td>
                                        <td class="center"><%=objContact.getMessage() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/contact/del?id=<%=objContact.getId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>