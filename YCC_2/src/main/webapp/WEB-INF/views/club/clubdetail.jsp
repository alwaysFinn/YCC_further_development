
 <!-- 작성자 : alwaysFinn(김지호)
 	  최초 작성일 : '23.01.09
 	  마지막 업데이트 : '23.01.14
 	  업데이트 내용 : 동아리 생성 페이지 javascript 추가
 	  기능 : 동아리 생성 페이지 view 파일 
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication property="principal" var="pinfo"/>

<!DOCTYPE html>
<html>
	<head>
	    <!-- head & meta tag include -->
    <%@include file="/WEB-INF/views/metahead.jsp"%>
<meta charset="UTF-8">
<title>동아리 상세보기</title>
</head>
<body>
	    <!-- include header -->
	<%@include file="/WEB-INF/views/header.jsp"%>
	
	<div class="container mt-3">
		<div class="Section_title_inner">
			<div class="section_title_inner">
				<h2 class=title">동아리 만들기</h2>
				<p class="txt">나와 같은 관심사를 가진 멤버를 모집하고 열심히 운영하여 동아리를 성장시켜보세요.</p>
				<hr>
			</div>
		</div>
		<div class="Section_Create_Club">
			<form id="form" method="post" action="" >
		    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<table class="table container-fluid">
					<tbody>
						<tr>
		        			<th class="col" style="vertical-align: middle !important;">동아리 이름</th>
		          				<td class="col-auto px-3"> 
		          					<div class="row">
							         	<input type="text" class="form-control onlyAlphabetAndNumber" id="club_title"  name="club_title"
							              placeholder="4~15자, 영문+숫자 입력" maxlength="20" style="width: 340px;">
							          	<input type="button" id="clubIdCheckBtn" name="clubIdCheckBtn" class="btn btn-outline-primary mx-1" value="중복확인" style="width: 100px;">
							          	<!-- 중복체크 검사결과  -->
							          	<span id="result"></span>  
		          					</div>
					         	 </td>
						</tr>
						<!-- 동아리 설명 -->
		      			<tr>
		        			<th class="col" style="vertical-align: middle !important;">동아리 소개</th>
		          				<td>
		          					<textarea id="club_info" name="club_info" style="resize: none; min-width: 100%" maxlength="100" placeholder="동아리 소개글 작성"></textarea>
						        </td>
						</tr>
						
						<!-- 동아리 배너 이미지 -->
						<tr>
							<th class="col" style="vertical-align: middle !important;">동아리 배너 이미지</th>
								<td>
									<input type="file" id ="fileItem" name='uploadFile' style="height: 30px;" multiple>
		     						<div id="uploadResult"></div>
								</td>
						</tr>
						
					</tbody>
				</table>
					<button id="submitBtn" type="submit" class="btn btn-primary">등록</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function () {
		$("#submitBtn").on("click", function(){
			
			
        	let form = $("#form")
			form.attr("action", "<c:url value='/club/create' />")
			form.attr("method", "post")
			form.submit()
    	
		})	
		
		
		
	})
	</script>
	
	<!-- footer include -->
<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>