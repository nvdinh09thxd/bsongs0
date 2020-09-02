<%@page import="util.StringUtil"%>
<%@page import="model.bean.Song"%>
<%@page import="model.dao.SongDao"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDao"%>
<%@page import="util.DefineUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
	<%
		String songName = (String) request.getAttribute("songName");
	%>
  <form id="formsearch" name="formsearch" method="get" action="<%=request.getContextPath()%>/search">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" placeholder="Tìm kiếm bài hát" value="<%if(songName!=null) out.print(songName); %>" type="text" />
    </span>
    <input name="button_search" src="<%=DefineUtil.URL_PUBLIC%>/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  <%
  CatDao catDao = new CatDao();
  ArrayList<Category> listCat = catDao.getItems();
  if(listCat.size()>0){
	  for(Category objCat: listCat){
		  String urlSlug = request.getContextPath() + "/danh-muc/"+ StringUtil.makeSlug(objCat.getName()) +"-"+objCat.getId();
  %>
    <li><a id="<%=objCat.getId()%>" href="<%=urlSlug%>"><%=objCat.getName() %></a></li>
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
  SongDao songDao = new SongDao();
  ArrayList<Song> recentSongs = songDao.getItems(6);
  if(recentSongs.size()>0){
	  for(Song objSong: recentSongs){
		  String urlSlug = request.getContextPath() + "/chi-tiet/" + StringUtil.makeSlug(objSong.getName()) + "-" + objSong.getId() + ".html";
  %>
    <li><a href="<%=urlSlug%>"><%=objSong.getName()%></a><br />
      <%if(objSong.getPreview_text().length()>50) out.print(objSong.getPreview_text().substring(0, 50)+"..."); else out.print(objSong.getPreview_text()); %></li>
      <%}} %>
  </ul>
</div>