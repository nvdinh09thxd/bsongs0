<%@page import="constants.GlobalConstant"%>
<%@page import="java.util.List"%>
<%@page import="daos.SongDao"%>
<%@page import="models.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
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
                                    <a href="<%=request.getContextPath() %>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
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
										case 4: out.print("<p style='color: red; background: yellow'>Xóa không thành công!</p>");
										break;
										case 0: out.print("<p style='color: red; background: yellow'>ID không tồn tại!</p>");
										break;
									}
								}
							%>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt xem</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	@SuppressWarnings("unchecked")
                                	List<Song> listSongs = (List<Song>) request.getAttribute("listSongs");
                                	if(listSongs!=null && listSongs.size()>0){
                                	for(Song objSong: listSongs){
                                		int songId = objSong.getId();
                                		String songName = objSong.getName();
                                		String catName = objSong.getCat().getName();
                                		String picture = objSong.getPicture();
                                		int counter = objSong.getCount();
                                		String urlEdit = request.getContextPath() + "/admin/song/edit?id="+songId;
                                		String urlDel = request.getContextPath() + "/admin/song/del?id="+songId;
                                %>
                                    <tr>
                                        <td><%=songId %></td>
                                        <td class="center"><%=songName %></td>
                                        <td class="center"><%=catName %></td>
                                        <td class="center"><%=counter %></td>
                                        <td class="center">
                                        <%
                                        	if(!"".equals(picture)){
                                        %>
											<img width="200px" height="200px" src="<%=GlobalConstant.DIR_UPLOAD %>/<%=picture %>" alt="<%=picture %>"/>
                                        <%} else { %>
                                        	<img width="200px" height="200px" src = "<%=GlobalConstant.URL_ADMIN %>/display/nopicture.jpg" alt = "nopicture" />
                                        <%
                                        }
                                        %>
                                        </td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" title="Xóa" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
									<%
                                	}}
									%>
                                </tbody>
                            </table>
                            <%
								int numberOfPages = (Integer) request.getAttribute("numberOfPages");
								int currentPage = (Integer) request.getAttribute("currentPage");
								if(listSongs!=null && listSongs.size()>0){
									SongDao songDao = new SongDao();
									int n = songDao.numberOfItems();
							%>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=listSongs.get(listSongs.size()-1).getId() %> đến <%=listSongs.get(0).getId() %> của <%=n %> bài hát</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous <%if(currentPage==1) out.print("disabled"); %>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%if(currentPage>1) out.print(request.getContextPath() + "/admin/songs?page="+(currentPage-1)); else out.print("javascript:void(0)");%>">Trang trước</a></li>
                                            <%
												for(int i=1; i<=numberOfPages; i++){
											%>
                                            <li class="paginate_button <%if(currentPage==i) out.print("active");%>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/songs?page=<%=i%>"><%=i%></a></li>
											<%
												}
											%>
                                            <li class="paginate_button next <%if(currentPage==numberOfPages) out.print("disabled"); %>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%if(currentPage<numberOfPages) out.print(request.getContextPath() + "/admin/songs?page="+(currentPage+1)); else out.print("javascript:void(0)");%>">Trang tiếp</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%} %>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>