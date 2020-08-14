<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý danh mục</h2>
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
									<a href="<%=request.getContextPath()%>/admin/cat/add"
										class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post" action="">
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" class="form-control input-sm"
											placeholder="Nhập tên bài hát"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>
							<%
								if (request.getParameter("msg") != null) {
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch (msg) {
									case 1:
										out.print("<h3 style='background: #67FF67; color: red'>Đã thêm thành công!</h3>");
										break;
									case 2:
										out.print("<h3 style='background: #67FF67; color: red'>Đã sửa thành công!</h3>");
										break;
									case 3:
										out.print("<h3 style='background: #67FF67; color: red'>Đã xóa thành công!</h3>");
										break;
									case 0:
										out.print("<h3 style='background: #67FF67; color: red'>Có lỗi trong quá trình xử lý!</h3>");
										break;
									}
								}
							%>
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Danh mục</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
										ArrayList<Category> listItems = (ArrayList<Category>) request.getAttribute("listCat");
										if (listItems != null) {
											for (Category objCat : listItems) {
									%>
									<tr>
										<td><%=objCat.getIdCat()%></td>
										<td class="center"><%=objCat.getName()%></td>
										<td class="center"><a
											href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=objCat.getIdCat()%>"
											title="" class="btn btn-primary"><i class="fa fa-edit "></i>
												Sửa</a> <a onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" 
												href="<%=request.getContextPath()%>/admin/cat/del?id=<%=objCat.getIdCat()%>"
											title="" class="btn btn-danger"><i class="fa fa-pencil"></i>
												Xóa</a></td>
									</tr>
									<%
										}
										}
									%>
								</tbody>
							</table>
							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ 1 đến 5 của 24
										truyện</div>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											<li class="paginate_button previous disabled"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a href="#">Trang
													trước</a></li>
											<li class="paginate_button active"
												aria-controls="dataTables-example" tabindex="0"><a
												href="">1</a></li>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="">2</a></li>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="">3</a></li>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="">4</a></li>
											<li class="paginate_button"
												aria-controls="dataTables-example" tabindex="0"><a
												href="">5</a></li>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="#">Trang tiếp</a></li>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>