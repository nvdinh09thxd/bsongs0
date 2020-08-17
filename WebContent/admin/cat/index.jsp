<%@page import="model.bean.Category"%>
<%@page import="model.dao.CatDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý danh muc</h2>
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
                                    <a href="<%=request.getContextPath() %>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                </div> <br /> <br />
                            </div>
							<%
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch(msg){
										case 1: out.print("<p style='color: green; background: yellow'>Thêm thành công!</p>");
										break;
										case 2: out.print("<p style='color: green; background: yellow'>Sửa thành công!</p>");
										break;
										case 3: out.print("<p style='color: green; background: yellow'>Xóa thành công!</p>");
										break;
										case 4: out.print("<p style='color: red; background: yellow'>ID không tồn tại!</p>");
										break;
										case 0: out.print("<p style='color: red; background: yellow'>Có lỗi trong quá trình xử lý!</p>");
										break;
									}
								}
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên danh muc</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
	                               		@SuppressWarnings("unchecked")
	                                	ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
	                                	if(listCat!=null && listCat.size()>0){
	                                		for(Category objCat : listCat){
                                %>
                                    <tr>
                                        <td><%=objCat.getId() %></td>
                                        <td class="center"><%=objCat.getName() %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/cat/edit?id=<%=objCat.getId() %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/cat/del?id=<%=objCat.getId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>