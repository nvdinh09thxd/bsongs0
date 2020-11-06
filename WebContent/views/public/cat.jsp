<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp"%>
<div class="content_resize">
	<div class="mainbar">
		<%
			Category category = (Category) request.getAttribute("category");
	    	if(category != null){
		%>
		<div class="article">
			<h1><%=category.getName()%></h1>
		</div>
		<%
    		}
		%>
		<%
			@SuppressWarnings("unchecked")
			List<Song> listSongsByIdCat = (List<Song>) request.getAttribute("listSongsByIdCat");
			if (listSongsByIdCat != null && listSongsByIdCat.size() > 0) {
			int i = 0;
			for (Song objSong : listSongsByIdCat) {
				i++;
				String date_string = new SimpleDateFormat("dd-MM-yyyy").format(objSong.getCreateAt());
				String urlSlug = request.getContextPath() + "/chi-tiet/" + StringUtil.makeSlug(objSong.getName())
				+ "-" + objSong.getId() + ".html";
		%>
		<div class="article">
			<h2>
				<a href="<%=urlSlug%>" title="<%=objSong.getName()%>"><%=objSong.getName()%></a>
			</h2>
			<p class="infopost">
				Ngày đăng: <%=date_string%>. Lượt xem: <%=objSong.getCount()%>
				<a href="#" class="com"><span><%=i%></span></a>
			</p>
			<div class="clr"></div>
			<div class="img"><a href="<%=urlSlug%>" title="<%=objSong.getName() %>">
			<%if(!"".equals(objSong.getPicture())){%>
		      <img src="<%=GlobalConstant.URL_PICTURE%>/<%=objSong.getPicture() %>" alt="Không có hình ảnh" width="177" height="213" class="fl" />
		      <%}else{%>
		      <img src="<%=GlobalConstant.URL_PICTURE%>/nopicture.jpg" alt="Không có hình ảnh" width="177" height="213" class="fl" />
		      <%}%>
			</a>
			</div>
			<div class="post_content">
				<p><%=objSong.getDescription()%></p>
				<p class="spec">
					<a href="<%=urlSlug%>"
						class="rm">Chi tiết &raquo;</a>
				</p>
			</div>
			<div class="clr"></div>
		</div>
		<%
			}} else {
		%>
		<p>Không có bài hát trong mục này!</p>
		<%
			}
		%>
		<%
			int numberOfPages = (Integer) request.getAttribute("numberOfPages");
			int currentPage = (Integer) request.getAttribute("currentPage");
			if (listSongsByIdCat != null && listSongsByIdCat.size() > 0 && numberOfPages > 1) {
				String urlSlug = request.getContextPath() + "/danh-muc/"
						+ StringUtil.makeSlug(category.getName()) + "-"
						+ category.getId();
		%>
		<p class="pages">
			<small>Trang <%=currentPage%> của <%=numberOfPages%></small>
			<a href="<%=urlSlug%>&page=<%=currentPage - 1%>"
				style="<%if (currentPage == 1) out.print("display: none");%>">&laquo;</a>
			<%
				for (int i = 1; i <= numberOfPages; i++) {
						if (currentPage == i) {
			%>
			<span><%=i%></span>
			<%
				} else {
			%>
			<a href="<%=urlSlug%>&page=<%=i%>"><%=i%></a>
			<%
				}}
			%>
			<a href="<%=urlSlug %>&page=<%=currentPage+1 %>" style="<%if(currentPage==numberOfPages) out.print("display: none");%>">&raquo;</a></p>
			<%
				}
			%>
		
	</div>
	<div class="sidebar">
		<%@ include file="/templates/public/inc/leftbar.jsp"%>
	</div>
	<div class="clr"></div>
</div>
<script>
	<%if(category != null){%>
		document.getElementById("<%=category.getId()%>").classList.add('active_cat');
	<%}%>
	document.getElementById("index").classList.add('active');
</script>
<%@ include file="/templates/public/inc/footer.jsp"%>