<%@page import="java.util.List"%>
<%@page import="daos.CatDao"%>
<%@page import="models.Song"%>
<%@page import="models.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
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
							<%
								String name = request.getParameter("name");
								int catId = 0;
								if (request.getParameter("catId") != null) {
									catId = Integer.parseInt(request.getParameter("catId"));
								}
								String preview = request.getParameter("preview");
								String detail = request.getParameter("detail");
								if (request.getParameter("msg") != null) {
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch (msg) {
										case 0 :
											out.print("<p style='color: red; background: yellow'>Có lỗi trong quá trình xử lý!</p>");
											break;
										case 1 :
											out.print("<p style='color: red; background: yellow'>Vui lòng nhập tên bài hát!</p>");
											break;
										case 2 :
											out.print("<p style='color: red; background: yellow'>Vui lòng nhập mô tả bài hát!</p>");
											break;
										case 3 :
											out.print("<p style='color: red; background: yellow'>Vui lòng nhập chi tiết bài hát!</p>");
											break;
										case 4 :
											out.print("<p style='color: red; background: yellow'>Vui lòng chọn danh mục bài hát!</p>");
											break;
									}
								}
							%>
							<div class="col-md-12">
								<form role="form" method="post" enctype="multipart/form-data" id="form">
									<div class="form-group">
										<label for="name">Tên bài hát</label>
										<input type="text" id="name" value="<%if (name != null) out.print(name);%>"	name="name" class="form-control" />
									</div>
									<div class="form-group">
										<label for="category">Danh mục bài hát</label>
										<select	id="category" name="catId" class="form-control">
											<option value="0">---Chọn danh mục---</option>
											<%
												CatDao catDao = new CatDao();
												List<Category> listCat = catDao.getItems();
												if (listCat.size() > 0) {
													for (Category objCat : listCat) {
											%>
											<option <%if(catId==objCat.getId()) out.print("selected");%> value="<%=objCat.getId()%>"><%=objCat.getName()%></option>
											<%
												}}
											%>
										</select>
									</div>
									<div class="form-group">
										<label for="picture">Hình ảnh</label>
										<input type="file" name="picture" />
									</div>
									<div class="form-group">
										<label for="preview">Mô tả</label>
										<textarea id="preview" class="form-control" rows="3" name="preview"><% 
										if (preview != null) out.print(preview);%></textarea>
									</div>
									<div class="form-group">
										<label for="detail">Chi tiết</label>
										<textarea id="detail" class="form-control" id="detail"	rows="5" name="detail"><%
										if (detail != null) out.print(detail);%></textarea>
									</div>
									<button type="submit" name="submit"	class="btn btn-success btn-md">Thêm</button>
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
	document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>