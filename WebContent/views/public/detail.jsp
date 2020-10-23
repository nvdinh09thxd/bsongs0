<%@page import="models.Comment"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="daos.RatingDao"%>
<%@page import="models.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    <%
		    RatingDao ratingDao = new RatingDao();
		    float rating = 0;
		  	Song itemSong = (Song) request.getAttribute("itemSong");
		  	if(itemSong != null){
		  		rating =  ratingDao.getRating(itemSong.getId());
		  		String songDate = new SimpleDateFormat("dd-MM-yyyy").format(itemSong.getCreateAt());
  	%>
  		<p style="display: none" id="idSong"><%=itemSong.getId() %></p>
      <h1><%=itemSong.getCat().getName() %></h1>
      <div class="clr"></div>
      <h2><a href="javascript: void(0)" title="<%=itemSong.getName() %>"><%=itemSong.getName() %></a></h2>
      <p>Ngày đăng: <%=songDate %>. Lượt xem: <%=itemSong.getCount()%></p>
      <div class="vnecontent">
         <%=itemSong.getDetail() %>
      </div>
    <b>Đánh giá bài hát</b>
    <span id="rating"></span>
    <p style="color: red; font-size: 20px;">Danh sách bình luận</p>
    <div id="comment">
		<%
			@SuppressWarnings("unchecked")
			List<Comment> listCmts = (List<Comment>) request.getAttribute("listCmts");
			if(listCmts != null && listCmts.size() > 0) {
				for(Comment objCmt: listCmts) {
						String cmtDate = new SimpleDateFormat("dd-MM-yyyy").format(objCmt.getDate_create());
		%>
		<b><%=objCmt.getUsername() %>:</b>
		<span><%=objCmt.getComment() %> -----</span>
		<i><%=cmtDate %></i>
		<br />
		<%}} %>
	</div>
    <form>
			<input type="text" name="fullname" id="fullname" value="" placeholder="Nhập tên" />
			<input type="text" name="cmt" id="cmt" value="" placeholder="Nhập bình luận" />
			<a href="javascript:void(0)" title="" onclick="onClickComment()">Bình luận</a>
	</form>
      <%} else { %>
      <p>Không có chi tiết bài hát</p>
      <%} %>
    </div>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
      @SuppressWarnings("unchecked")
      List<Song> relatedSong = (List<Song>) request.getAttribute("relatedSong");
      if(relatedSong != null && relatedSong.size() >0){
    	  for(Song item : relatedSong){
    		  String urlSlug = request.getContextPath() + "/chi-tiet/" + StringUtil.makeSlug(item.getName()) + "-" + item.getId() + ".html";
      %>
      <div class="comment"> 
	      <a href="<%=urlSlug %>">
	      		<img src="<%=GlobalConstant.DIR_UPLOAD%>/<%=item.getPicture() %>" width="40" height="40" alt="" class="userpic" />
	      </a>
          <h2><a href="<%=urlSlug %>"><%=item.getName() %></a></h2>
        <p><%=item.getDescription() %></p>
      </div>
      <%}} %>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>

<script type="text/javascript">
	var idSong = $("#idSong").text();
	$(function() {
		$('#rating').raty({
			number:		10,
			click: function(score) {
				if(score==null) score = 0;
				$.ajax({
					url: '<%=request.getContextPath()%>/rating',
					type: 'POST',
					data: {ascore: score, aid: idSong},
					success: function(data){
						alert(data);
					},
					error: function (){
						alert('Có lỗi xảy ra');
					}
				})
			},
			cancel: true,
			cancelPlace: 'left',
			half: true,
			start: <%=rating%>,
			path:"/imgs/",
		});
	});
	
	function onClickComment() {
			var fullname=$("#fullname").val();
			var cmt=$("#cmt").val();
			if(fullname!="" && cmt!=""){
				$.ajax({
					url : '<%=request.getContextPath()%>/comment',
					type : 'POST',
					data : {
						afullname : fullname,
						acmt : cmt,
						aid: idSong
					},
					success : function(data) {
						$("#comment").append(data);
						$("#fullname").val("");
						$("#cmt").val("");
					},
					error : function() {
						alert("Có lỗi rồi!");
					}
				});
			} else {
				alert("Vui lòng nhập đầy đủ thông tin khi bình luận!");
			}
		};
	document.getElementById("index").classList.add('active');
	<%if(itemSong!=null){%>
		document.getElementById("<%=itemSong.getCat().getId()%>").classList.add('active_cat');
	<%}%>
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>
