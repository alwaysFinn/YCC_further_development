 <!-- 작성자 : alwaysFinn(김지호)
 	  최초 작성일 : '23.01.11
 	  마지막 업데이트 : '23.01.11
 	  업데이트 내용 : 동아리 생성 파일
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
<title>동아리 생성</title>
</head>
<body>
	    <!-- include header -->
	<%@include file="/WEB-INF/views/header.jsp"%>
	<form id="form" method="post" action="" >
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<table class="table container-fluid border">
			<tbody>
				<tr>
					<th scope="row" class="col-sm-4">동아리 이름</th>
						<td>
							<input type="text" name="club_title">
						</td>
				</tr>
				<tr>
					<th scope="row" class="col-sm-4">동아리 설명</th>
						<td>
							<textarea name="club_info"></textarea>
						</td>
				</tr>
			</tbody>
		</table>
			<button id="submitBtn" type="submit" class="btn btn-primary">등록</button>
	</form>
	<script type="text/javascript">
	$(document).ready(function () {
		$("#submitBtn").on("click", function(){
        	let form = $("#form")
			form.attr("action", "<c:url value='/club/create' />")
			form.attr("method", "post")
			form.submit()
    	
		})	
	}
	</script>
	
	<!-- footer include -->
<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>