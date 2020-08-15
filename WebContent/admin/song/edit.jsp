<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<script
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.validate.min.js"></script>
<style>
.error {
	color: red;
}
</style>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Thêm bài hát</h2>
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
								if (request.getParameter("msg") != null) {
									int msg = Integer.parseInt(request.getParameter("msg"));
									if (msg==0) {
											out.print("<h3 style='color: red'>Có lỗi xảy ra trong quá trình xử lý!</h3>");
									}
									if (msg==2) {
										out.print("<h3 style='color: red'>Chưa chọn file!</h3>");
									}
								}
								%>
								<%
									Song item = (Song) request.getAttribute("item");
										if(item!=null){
											int id = item.getId();
											String name = item.getName();
											String preview = item.getPreview_text();
											String detail = item.getDetail_text();
											int cat_id = item.getItemCat().getIdCat();
								%>
								<form role="form" method="post" enctype="multipart/form-data"
									id="form">
									<input type="hidden" name="id" value="<%=id%>">
									<div class="form-group">
										<label for="name">Tên bài hát</label> <input type="text"
											id="name" value="<%=name %>" name="name" class="form-control" />
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label> 
										<select
											id="category" name="category" class="form-control">
											<%
													ArrayList<Category> listItems = CatDao.getItems();
														for (Category objCat : listItems) {
															if(cat_id==objCat.getIdCat()){
											%>
												<option value="<%=objCat.getIdCat()%>" selected><%=objCat.getName()%></option>
											<%} else {%>
												<option value="<%=objCat.getIdCat()%>"><%=objCat.getName()%></option>
											<%}}%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label> <input type="file"
											name="picture" />
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3"
											name="preview"><%=preview %></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" class="form-control" id="detail"
											rows="5" name="detail"><%=detail%></textarea>
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Thêm</button>
								</form>
								<%} %>
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
	$(function() {
		$('form').validate({
			rules : {
				"name" : {
					required : true,
				},
				"category" : {
					required : true,
				},
				"preview" : {
					required : true,
				},
				"detail" : {
					required : true,
				},
			},
			messages : {
				"name" : {
					required : "Yêu cầu nhập",
				},
				"category" : {
					required : "Yêu cầu nhập",
				},
				"preview" : {
					required : "Yêu cầu nhập",
				},
				"detail" : {
					required : "Yêu cầu nhập",
				},
			},
		})
	})
	document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>