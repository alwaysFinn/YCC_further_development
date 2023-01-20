
 <!-- 작성자 : alwaysFinn(김지호)
 	  최초 작성일 : '23.01.06
 	  마지막 업데이트 : '23.01.17
 	  업데이트 내용 : login을 했는지, 만든 동아리가 있는지 없는지에 분기를 줘서 출력되는 문구 차별화
 	  기능 : 동아리 main페이지 view 파일 
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication property="principal" var="pinfo"/>

<c:choose>
	<c:when test="${pinfo != 'anonymousUser' }">
		<c:set var="loginId" value="${pinfo.member.user_id }" />
		<c:set var="userName" value="${pinfo.member.user_name }" />
	</c:when>
	<c:otherwise>
		<c:set var="loginId" value="null" />
		<c:set var="userName" value="null" />
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
<head>

<!-- head & meta tag include -->
<%@include file="/WEB-INF/views/metahead.jsp"%>

<style>
.club-info {
	background-color: rgba(0, 0, 0, 0.5);
	color: aliceblue;
	text-align: left;
	position: absolute;
	bottom: 0%;
	width: 100%;
}
</style>

<title>YOUNG문화센터 - 동아리 메인</title>
</head>
<body>
	<!-- header include -->
	<%@include file="/WEB-INF/views/header.jsp"%>

	<!-- 인기동아리, 동아리 추가 부분 -->
	<div class="container text-center">
		<h1 class="text-start mt-3">인기 동아리</h1>
		<hr>
		<div class="row">
			<!--인기동아리1 그리드-->
			<div class="col-md-4">
				<!-- 이미지 부분 -->
				<div style="position: relative;">
					<img class="img-fluid" src="/ycc/resources/img/club/catclub.jpg">
					<!-- 겹쳐지는 텍스트 부분 -->
					<div class="club-info">
						<div class="club-info px-2">
							<h2 style="font-size: 2vw">고양이 매니아</h2>
							<p style="font-size: 0.8vw">
								동아리장 : 최선혜 | 멤버 수 : 50명 |<br>생성일 : 2022-10-23
							</p>
						</div>
					</div>
				</div>
			</div>
			<!--인기동아리2 그리드-->
			<div class="col-md-4">
				<div style="position: relative;">
					<img class="img-thumbnail"
						src="/ycc/resources/img/club/swimclub.jpg">
					<div class="club-info">
						<img class="img-fluid" src="/ycc/resources/img/club/swimclub.jpg">
						<div class="club-info px-2">
							<h2 style="font-size: 2vw">수영 동아리</h2>
							<p style="font-size: 0.8vw">
								동아리장 : 최나리 | 멤버 수 : 10명 |<br>생성일 : 2022-10-24
							</p>
						</div>
					</div>
				</div>
			</div>
			<!--동아리 추가 그리드-->
			<div class="col-md-4 h-100">
				<div style="position: relative;">
					<img class="img-fluid" src="/ycc/resources/img/club/add_club.png">
					<div class="club-info" style="text-align: center">
						<h2 style="font-size: 3vw">동아리 만들기</h2>
					</div>
				</div>
			</div>
		</div>

		<!-- 내 동아리 -->
		<h1 class="text-start mt-5">내 동아리</h1>
		<hr>
		<p class="text-start">내가 만든 동아리</p>
		<c:choose>
			<c:when test="${myMsList eq '[]'}">
				<div class="text-center mb-5">만든 동아리가 없습니다.</div>
			</c:when>
			<c:when test="${loginId eq 'null'}">
				<div class="text-center mb-5">로그인을 해주세요.</div>
			</c:when>
			<c:otherwise> 
				<c:forEach var='myMsList' items="${myMsList }">
					<div class="text-start px-4">
						<div class="d-flex me-auto">
							<img src="/ycc/resources/img/club/ycc_logo.png"
							class="img-thumbnail rounded-2 me-3"
							style="height: 150px; width: 150px;">
					<!-- 동아리 이동(제목클릭) -->
							<div class="text-truncate">
								<a href="#" style="text-decoration: none; text-decoration-color: none;"></a>
								<h4>${myMsList.club_title }</h4>
								<small class="text-muted">동아리장 : ${myMsList.club_master_id } | 멤버수 : ${myMsList.club_member } | 생성일 : ${myMsList.club_create_time }</small>
								<a href="#" class="text-reset text-decoration-none"><p class="mb-2">게시글1</p></a>
								<a href="#" class="text-reset text-decoration-none"><p class="mb-2">게시글2</p></a>
								<a href="#" class="text-reset text-decoration-none"><p class="mb-2">게시글3</p></a>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<hr>
		<p class="text-start">내가 가입한 동아리</p>
		<c:choose>
			<c:when test="${myList eq '[]'}">
				<div class="text-center mb-5">가입된 동아리가 없습니다.</div>
			</c:when>
			<c:when test="${loginId eq 'null'}">
				<div class="text-center mb-5">로그인을 해주세요.</div>
			</c:when>
			<c:otherwise>
				<c:forEach var='myList' items="${myList }">
					<div class="text-start px-4">
						<div class="d-flex me-auto">
							<img src="/ycc/resources/img/club/ycc_logo.png"
								class="img-thumbnail rounded-2 me-3"
								style="height: 150px; width: 150px;">
						<!-- 동아리 이동(제목클릭) -->
							<div class="text-truncate">
								<a href="#" style="text-decoration: none; text-decoration-color: none;"></a>
								<h4>${myList.club_title }</h4>
								<small class="text-muted">동아리장 : ${myList.club_master_id } | 멤버수 : ${myList.club_member } | 생성일 : ${myList.club_create_time }</small>
								<a href="#" class="text-reset text-decoration-none"><p class="mb-2">게시글1</p></a>
								<a href="#" class="text-reset text-decoration-none"><p class="mb-2">게시글2</p></a>
								<a href="#" class="text-reset text-decoration-none"><p class="mb-2">게시글3</p></a>
							</div>
						</div>
						<hr>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<!-- 전체 동아리 -->
		<h1 class="text-start mt-5">전체 동아리</h1>
		<hr>
		
	    <!--게시판 부분-->
	    <form id="frm" action="<c:url value="/club" />" class="search-form" method="get">
		    <div class=" p-3">
		      <table class="table table-hover mt-3" style="table-layout: fixed;">
		      	<colgroup>
		      		<col width = 46%>
		      		<col width = 17%>
		      		<col width = 15%>
		      		<col width = 22%>
		      	</colgroup>
		        <thead>
		          <tr>
		            <th scope="col">동아리명</th>
		            <th scope="col">동아리장</th>
		            <th scope="col">멤버수</th>
		            <th scope="col">생성일</th>
		          </tr>
		        </thead>
		        <tbody>
			        <c:forEach var="list" items="${list }">
			        	<tr>
				            <td class="text-start text-truncate">
				            	<a name="club_title" style="text-decoration: none; color: black;" href="<c:url value="/club/detail?id=${list.club_id }"/> ">${list.club_title }</a>
				            </td>	
			  	            <td>${list.club_master_id }</td>
				            <td>${list.club_member }</td>
				            <td>${list.club_create_time }</td>
			          	</tr>
			        </c:forEach>
		        </tbody>
		      </table>
		    </div>
	    </form>
	    
	    
    <!-- 페이지 네비게이션 -->
    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
    <button type="button" class="btn btn-primary " id="clubCreateBtn">동아리 생성</button>
    
    <!-- 검색 영역 -->
	    <div class="d-flex flex-row mx-auto w-75">
	      <select class="form-select form-select-sm mx-2 w-25" aria-label=".form-select-sm example">
	        <option value="1">동아리 이름</option>
	        <option value="2">동아리장</option>
	      </select>
	      <input type="text" class="form-control mx-2 w-50" aria-label="title" aria-describedby="basic-addon1">
	      <button type="button" class="btn btn-primary mx-2" style="width: 80px">검색</button>
	    </div>
    </div>
   
  
	</div> <!-- 컨테이너 end -->
	
	<script type="text/javascript">
		$(document).ready(function() {

			$("#clubCreateBtn").on("click", function(){
				if(${myMsList eq '[]'}){
					location.href = "<c:url value='/club/create' />"
				}else if(${myMsList ne '[]'}){
					alert("계정당 1개의 동아리만 만들 수 있습니다.")
				}
			})
		
		})
	</script>

	<!-- footer include -->
	<%@include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>