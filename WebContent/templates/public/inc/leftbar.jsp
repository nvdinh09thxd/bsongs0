<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="model.bean.Category"%>
    <%@ page import="model.dao.CatDao"%>
    <%@ page import="model.bean.Song"%>
    <%@ page import="model.dao.SongDao"%>
<div class="searchform">
  <form id="formsearch" name="formsearch" method="post" action="#">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Tìm kiếm bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath() %>/templates/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  <%
  ArrayList<Category> listCat = CatDao.getItems();
  if(listCat.size()>0){
	  for(Category item : listCat){
  %>
    <li><a id="<%=item.getIdCat()%>" href="<%=request.getContextPath()%>/cat?id=<%=item.getIdCat()%>"><%=item.getName() %></a></li>
    <%
	  }}
    %>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  <%
  ArrayList<Song> listRecentSong = SongDao.getItems(6);
  if(listRecentSong.size()>0){
	  for(Song item : listRecentSong){
  %>
    <li><a href="#"><%=item.getName() %></a><br />
      <%if(item.getPreview_text().length()>50) out.print(item.getPreview_text().substring(0, 50)+"..."); else out.print(item.getPreview_text()); %></li>
    <%
	  }}
    %>
  </ul>
</div>