<%@page import="model.dao.CommentDao"%>
<%@page import="model.bean.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bình luận</h2>
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
										case 1: out.print("<p style='color: green; background: yellow'>Xóa thành công!</p>");
										break;
										case 2: out.print("<p style='color: red; background: yellow'>ID không tồn tại!</p>");
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
                                        <th>Username</th>
                                        <th>Comment</th>
                                        <th>Active</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
	                               		@SuppressWarnings("unchecked")
	                                	ArrayList<Comment> listCmts = (ArrayList<Comment>) request.getAttribute("listCmts");
	                                	if(listCmts!=null && listCmts.size()>0){
	                                		for(Comment objCmt : listCmts){
                                %>
                                    <tr>
                                        <td><%=objCmt.getId() %></td>
                                        <td class="center"><%=objCmt.getUsername() %></td>
                                        <td class="center"><%=objCmt.getComment() %></td>
                                        <td class="center">
	                                        <a href="javascript: void(0)" title="">
		                                        <img src="<%=DefineUtil.URL_ADMIN %>/display/<%if(objCmt.isActive()) out.print("active.gif"); else out.print("deactive.gif");%>" alt="<%=objCmt.getId() %>"/>
	                                        </a>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/comment/del?id=<%=objCmt.getId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" title="Xóa" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<% }} else {
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
    document.getElementById("comment").classList.add('active-menu');
    $("img").click(function(){
	    var image = $(this)
	    $.ajax({
			url: '<%=request.getContextPath()%>/admin/comments',
				type : 'POST',
				cache : false,
				data : {
					aid: image.attr("alt"),
					asrc : image.attr("src")
				},
				success : function(data) {
					image.attr("src", data)
				},
				error : function() {
					alert("Có lỗi xảy ra");
				}
			});
		});
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>