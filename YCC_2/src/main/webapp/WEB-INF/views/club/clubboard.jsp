
 <!-- 작성자 : alwaysFinn(김지호)
 	  최초 작성일 : '23.01.12
 	  마지막 업데이트 : '23.03.01
 	  업데이트 내용 : 게시글 수정하기 기능 활성화
 	  기능 : 동아리 게시글 작성, 수정, 읽기 페이지
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- head & meta tag include -->
    <%@include file="/WEB-INF/views/metahead.jsp"%>
  	<!--summernote-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
 
<title>글쓰기</title>
</head>

<body>
  	<!-- header inlcude -->
	<%@include file="/WEB-INF/views/header.jsp"%>
  		<!--container start-->
  		<form id="form" method="post" action="" >
  			<div class="container mt-5">
			<input type="hidden" id="club_id" name="club_id" value="${club_id}">
	  			<c:choose>
					<c:when test ="${mode eq 'new'}">
						<h3 class="posttitle pt-3">글쓰기</h3>
					</c:when>
					<c:when test ="${mode eq 'modi'}">
						<h3 class="posttitle pt-3">수정하기</h3>
					</c:when>
				</c:choose>
   				<hr>
   					
   					<c:if test="${mode eq 'new' }">
						<input type="text" class="form-control mb-3" id="title" name="club_article_title"
	   					 placeholder="제목을 입력해주세요">
	    				<textarea class="summernote mb-5" id="contents" name="club_article_content">
	    				</textarea>
	    				
			    		<div class="m-5" style="text-align: center;">
			      			<button type="button" class="btn btn-primary mx-3" id="postBtn">등록하기</button>
			      			<input class="btn btn-secondary" type="button" value="취소하기">
			    		</div>
					</c:if>
					<!-- 여기부터 수정할 차례 수정 시 온 값들 수정할 수 있게끔 -->
					<c:if test="${mode eq 'modi' }">
						<input type="hidden" id="club_article_id" name="club_article_id" value="${list[0].club_article_id}">
						<input type="text" class="form-control mb-3" id="title" name="club_article_title"
	   					 value="${list[0].club_article_title}">
	    				<textarea class="summernote mb-5" id="contents" name="club_article_content">
	    					<c:out value="${list[0].club_article_content}"/>
	    				</textarea>
	    				
	    				<div class="m-5" style="text-align: center;">
			      			<button type="button" class="btn btn-primary mx-3" id="modiBtn">수정하기</button>
			      			<button type="button" class="btn btn-secondary mx-3" id="cBtn">취소하기</button>
			    		</div>
					</c:if>	
   					
    				
				<!-- summernote 업로드 -->
<!--    				<div class="input-group mb-3 mt-3"> -->
<!--       				<input type="file" class="form-control" id="inputGroupFile02"> -->
<!--       				<label class="input-group-text" for="inputGroupFile02">Upload</label> -->
<!--     			</div> -->
    			<!-- 게시글 등록, 취소 버튼 -->
    			
    			
	    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  			</div><!--container end-->
  		</form>

  <script type="text/javascript">
  	$(document).ready(function() {

		 //summernote
		$('#contents').summernote({
			placeholder:'내용을 입력하세요.',
		    height: 600,
		    lang: "ko-KR",
		    disableResizeEditor: true
		  });
		 
		let msg = "${msg}"
			if(msg == "WRITE_FAIL") alert("게시글 등록에 실패했습니다. 잠시 후 다시 시도해주세요.");
			if(msg == "WRITE_ERR") alert("잠시 후 다시 시도해주세요");
			if(msg == "MOD_FAIL") alert("게시글 수정에 실패했습니다. 잠시 후 다시 시도해주세요.");
			if(msg == "MOD_ERR") alert("잠시 후 다시 시도해주세요");
	   
		 
		let nullCheck = function() {
			let form = document.getElementById("form")
			let contents = document.getElementById("contents").value;
				if(form.club_article_title.value==""||form.club_article_title.value==null){
					alert("제목을 입력해주세요.");
					form.club_article_title.focus();
					return false;
				}// summernote는 기본적으로 공백이 존재하므로 해당 공백을 제거하는 trim()메서드 사용 후 nullcheck진행
				if(contents.trim() == ""|| contents.trim() == null) {
					alert("내용을 입력해 주세요.");
					$('.summernote').summernote('focus');
					return false;
				}
				return true;
		}
			
		$("#postBtn").on("click", function() {
			let form = $("#form");
			form.attr("action", "<c:url value='/club/board/write' />")
			form.attr("method", "post")
			
			if(nullCheck()){
				form.submit();
			}
		})
		
		$("#modiBtn").on("click", function() {
			let form = $("#form");
			form.attr("action", "<c:url value='/club/board/edit' />")
			form.attr("method", "post")
			
			if(nullCheck()){
				form.submit();
			}
		})
		
		$("#cBtn").on("click", function() {
			if(!confirm("취소하시겠습니까?")) return;
			location.href ="<c:url value='/club/board/view?id=${list[0].club_id}&article_id=${list[0].club_article_id }' />";
		})
		
  	})
  
  </script>
    <!-- footer inlcude -->

    <!-- footer inlcude -->
<%@include file="/WEB-INF/views/footer.jsp"%>

</body>

</html>