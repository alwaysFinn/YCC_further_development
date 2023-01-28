<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- head & meta tag include -->
    <%@include file="/WEB-INF/views/metahead.jsp"%>
  	<!--summernote-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js">	</script>
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
    			<h3 class="posttitle pt-3">글쓰기</h3>
   				<hr>
   					<input type="text" class="form-control mb-3" id="title" name="club_article_title"
   					 placeholder="제목을 입력해주세요">
    				<textarea class="summernote mb-5" id="contents" name="club_article_contents">
    				</textarea>
    				
				<!-- summernote 업로드 -->
<!--    				<div class="input-group mb-3 mt-3"> -->
<!--       				<input type="file" class="form-control" id="inputGroupFile02"> -->
<!--       				<label class="input-group-text" for="inputGroupFile02">Upload</label> -->
<!--     			</div> -->
    			<!-- 게시글 등록, 취소 버튼 -->
    			
    			
	    		<div class="m-5" style="text-align: center;">
	      			<button type="button" class="btn btn-primary mx-3" id="postBtn">등록하기</button>
	      			<input class="btn btn-secondary" type="button" value="취소하기">
	    		</div>
	    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  			</div><!--container end-->
  		</form>

  <script type="text/javascript">
  	$(document).ready(function() {

		 //summernot 
		$('.summernote').summernote({
			placeholder:"내용을 입력하세요.",
		    height: 600,
		    lang: "ko-KR",
		    disableResizeEditor: true
		  });
	   
		 
		let nullCheck = function() {
		let form = document.getElementById("form")
			if(form.club_article_title.value==""||form.club_article_title.value==null){
				alert("제목을 입력해주세요.");
				form.club_article_title.focus();
				return false;
			}
			if(document.getElementById("contents").value==""||document.getElementById("contents").value==null) {
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
				//form.submit()
				console.log(document.getElementById("contents").value)
			}
		})
		
  	})
  
  </script>
    <!-- footer inlcude -->

    <!-- footer inlcude -->
<%@include file="/WEB-INF/views/footer.jsp"%>

</body>

</html>