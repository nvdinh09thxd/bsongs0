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
				<h2>Sửa danh mục</h2>
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
									switch(msg) {
										case 0: out.print("<h3 style='background: #67FF67; color: red'>Có lỗi xảy ra trong quá trình xử lý!</h3>");
										break;
									}
								}
							%>
							<%
							String name="";
							int id=0;
							if (request.getAttribute("itemCat") != null) {
								Category itemCat = (Category) request.getAttribute("itemCat");
								id= itemCat.getIdCat();
								name = itemCat.getName();
							}
							%>
								<form role="form" method="post"
									id="form">
									<div class="form-group">
										<label for="name">Sửa danh mục</label>
										<input type="hidden" name="id" value="<%=id %>" />
										 <input type="text"
											id="name" value="<%=name %>" name="name" class="form-control" />
									</div>
									
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
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
	$(function() {
		$('form').validate({
			rules : {
				"name" : {
					required : true,
				},
			},
			messages : {
				"name" : {
					required : "Yêu cầu nhập",
				},
			},
		})
	})
	document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>