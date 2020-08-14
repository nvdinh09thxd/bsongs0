<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    <%
    	Category itemCat = (Category) request.getAttribute("itemCat");
    	if(itemCat!=null){
    %>
		<h1><%=itemCat.getName() %></h1>
		<%
    	}
		%>
    </div>
    <%
    @SuppressWarnings("unchecked")
    ArrayList<Song> listSongByCategory = (ArrayList<Song>) request.getAttribute("listSongByCategory");
    if(listSongByCategory!=null && listSongByCategory.size()>0){
  	  int i=0;
  	  for(Song item: listSongByCategory){
  		  i++;
    %>
    <div class="article">
      <h2><a href="<%=request.getContextPath() %>/detail?id=<%=item.getId() %>" title="<%=item.getName() %>"><%=item.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=item.getDate_create() %>. Lượt xem: <%=item.getCounter() %> <a href="<%=request.getContextPath() %>/detail?id=<%=item.getId() %>" class="com"><span><%=i %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/templates/public/images/<%=item.getPicture()%>" width="177" height="213" alt="<%=item.getPicture() %>" class="fl" /></div>
      <div class="post_content">
        <p><%=item.getPreview_text() %></p>
        <p class="spec"><a href="<%=request.getContextPath() %>/detail?id=<%=item.getId() %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%
  	  }} else {
    %>
    <div class="article">
    <p>Không có bài hát nào!</p>
    </div>
    <%} %>
    <p class="pages"><small>Trang 1 của 3</small>
    <span>1</span>
    <a href="">2</a>
    <a href="">3</a>
    <a href="#">&raquo;</a></p>
  </div>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script>
	<%if(itemCat!=null){%>
    	document.getElementById("<%=itemCat.getIdCat()%>").classList.add('active_cat');
	<%}%>
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>