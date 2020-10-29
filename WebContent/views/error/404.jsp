<%@page import="models.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <h2>404 - PAGE NOT FOUND</h2>
      <div class="clr"></div>
    </div>
  </div>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script>
    document.getElementById("index").classList.add('active');
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>
