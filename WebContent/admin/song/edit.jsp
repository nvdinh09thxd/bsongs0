<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Song"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>

<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa bài hát</h2>
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
                        		String preview = request.getParameter("preview");
                        		String detail = request.getParameter("detail");
                        		int catId = 0;
                        		if(request.getParameter("catId")!=null){
                        			catId = Integer.parseInt(request.getParameter("catId"));
                        		}
                        		String picture = "";
                        	
								if(request.getParameter("msg")!=null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch(msg){
										case 0: out.print("<p style='color: red; background: yellow'>Có lỗi trong quá trình xử lý!</p>");
										break;
										case 1: out.print("<p style='color: red; background: yellow'>Vui lòng nhập tên bài hát!</p>");
										break;
										case 2: out.print("<p style='color: red; background: yellow'>Vui lòng nhập mô tả bài hát!</p>");
										break;
										case 3: out.print("<p style='color: red; background: yellow'>Vui lòng nhập chi tiết bài hát!</p>");
										break;
									}
								}
							%>
							<%
	                             if(request.getAttribute("itemSong")!=null){
	                             	Song itemSong = (Song) request.getAttribute("itemSong");
	                             	name = itemSong.getName();
	                             	preview = itemSong.getPreview_text();
	                             	detail = itemSong.getDetail_text();
	                             	catId = itemSong.getItemCat().getId();
	                             	picture = itemSong.getPicture();
	                             }
                             %>
                            <div class="col-md-12">
                                <form role="form" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="catId" class="form-control">
	                                        <%
	                                        	CatDao catDao = new CatDao();
	                                        	ArrayList<Category> listCat = catDao.getItems();
	                                  			if(listCat.size()>0){
	                                  				for(Category objCat : listCat){
	                                        %>	                                        
	                                        	<option <%if(objCat.getId()==catId) out.print("selected");%> value="<%=objCat.getId() %>"><%=objCat.getName() %></option>
	                                        <%}} %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                        <%if(!"".equals(picture)){ %>
                                        <img width="200px" height="200px" src="<%=DefineUtil.URL_PICTURE %>/<%=picture %>" alt="Không có hình ảnh" />
                                        <%} %>
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview"><%=preview %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail" class="ckeditor"><%=detail %></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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
<%@ include file="/templates/admin/inc/footer.jsp" %>