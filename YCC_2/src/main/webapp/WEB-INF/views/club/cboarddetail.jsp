
 <!-- 작성자 : alwaysFinn(김지호)
 	  최초 작성일 : '23.01.06
 	  마지막 업데이트 : '23.03.01
 	  업데이트 내용 : 수정 시 메세지 기능 추가
 	  기능 : 동아리 main페이지 view 파일 
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css" >
<!-- header-->
<%@include file="/WEB-INF/views/metahead.jsp"%>
    <title>게시글 상세 보기</title>
</head>
<body>
    <!-- header inlcude -->
<%@include file="/WEB-INF/views/header.jsp"%>
    
	<main class="mt-5 pt-5">
			<div class="container px-4">
				
				<form id="modform">
					<h2 class="writing-header mb-3">
						<a id="cBoardList" value=${cbdetail[0].club_article_id }>게시글 읽기</a> 
						<input type="hidden" name="club_article_id" value=${cbdetail[0].club_article_id }>
						<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
					</h2>
				</form>
				<form id="form" class="frm" action="ycc/club/board" method="post">
					<div class="card mb-4">
	    				<div class="card-body">
    						<!-- 게시글 정보 -->
  							<h4 class="title" >${cbdetail[0].club_article_title }</h4>
  							<p class="writingInfo">작성자 : ${cbdetail[0].user_id} |
   								게시일 : <fmt:formatDate value="${cbdetail[0].club_board_upload_time }" pattern="yyyy-MM-dd" type="date"/> 
  								| 조회수 : ${cbdetail[0].club_article_viewcnt }
 							</p>
 							<hr>
 							<!-- 내용 -->
  							<p class="content" >${cbdetail[0].club_article_content }</p>
	    				</div>
	    			</div>
					
					<div class="row pb-5" style="float:right">
						<div class="col-auto px-1" >
			  				<a id="listBtn" class="btn btn-outline-secondary" ><i class="bi bi-justify"></i>목록</a>	
						</div>
						<!-- 본인이 쓴 게시글에만 수정, 삭제 가능 -->
						<!-- 세션 아이디와 boardDto에 저장되 아이디가 같으면 수정, 삭제 버튼 활성화 -->
						<sec:authentication property="principal" var="pinfo"/>
						<sec:authorize access="isAuthenticated()">
							<c:if test="${pinfo.member.user_id eq cbdetail[0].user_id}">
								<div class="col-auto px-1">
					  				<button type="button" class="btn btn-outline-success" id="modifyBtn"><i class="bi bi-pen"></i>수정</button>
								</div>
								<div class="col-auto px-1">
					  				<button type="button" class="btn btn-outline-danger" id="deleteBtn"><i class="bi bi-trash3"></i>삭제</button>
					      		</div>
				      		</c:if>
			      		</sec:authorize>
      			         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			       </div>
		       </form>
	    	</div>
	    	<div class="row pb-5">
	    	</div>
	</main>
	
				
<script type="text/javascript">

	$(document).ready(function(){
		
		let msg = "${msg}";
			if(msg == "MOD_SUCCESS") alert("글 수정이 완료되었습니다.");
		
		
		
		let club_article_id = ${cbdetail[0].club_article_id }
		let club_id = ${cbdetail[0].club_id }
		console.log("club_article_id", club_article_id )
		console.log("club_id", club_id )

		
		$("#listBtn").on("click", function() {
			location.href ="<c:url value='/club/detail?id=${cbdetail[0].club_id }' />";
		})
		
		$("#modifyBtn").on("click", function() {
			if(!confirm("수정하시겠습니까?")) return;
			location.href ="<c:url value='/club/board/edit?article_id=${cbdetail[0].club_article_id }' />";
			})
	
		$("#deleteBtn").on("click", function() {
			if(!confirm("정말로 삭제하시겠습니까?")) return;
			
			let form = $("#form")
			form.attr("action","<c:url value='/club/remove${searchItem.queryString}' />")
			form.attr("method", "post")
			form.submit()
		})	
	})
	
</script>
    
  <!-- footer inlcude -->
<%@include file="/WEB-INF/views/footer.jsp"%>
</body>

</html>