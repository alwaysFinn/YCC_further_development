<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- head & meta tag include -->
    <%@include file="/WEB-INF/views/metahead.jsp"%>
</head>
<body>
  <!-- header -->
  <%@include file="/WEB-INF/views/header.jsp"%>

  <!--start container-->
  <div class="container">
    <br>
    <h3>공지사항 게시판</h3>
    <hr /><br>
    <div class="input-group" style="width: 200px; margin-left:80%; padding-bottom: 20px;">
      <select class="form-select form-select-sm" aria-label=".form-select-sm example"
        style="width: 90px; margin-right: 10px;">
        <option value="1">최신순</option>
        <option value="2">조회순</option>
        <option value="3">관련순</option>
      </select>
    </div>
    <!--게시판 부분-->
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col" style="text-align: center;">제목</th>
          <th scope="col">글쓴이</th>
          <th scope="col">날짜</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row"><a href="<c:url value= '/board/postView' />" class="text-decoration-none">저 오늘 치과갑니다</a></th>
          <td>김지호</td>
          <td>yyyy-mm-dd</td>
          <td>12824203</td>
        </tr>
        <tr>
          <th scope="row"><a href="" class="text-decoration-none">제목222222222222222222222222222222222</a></th>
          <td>짱구</td>
          <td>yyyy-mm-dd</td>
          <td>12312</td>
        </tr>
        <tr>
          <th scope="row"><a href="" class="text-decoration-none">3</a></th>
          <td>고경희</td>
          <td>yyyy-mm-dd</td>
          <td>1231</td>
        </tr>
        <tr>
          <th scope="row"><a href="" class="text-decoration-none">제목15443534541111111111111111111111111</a></th>
          <td>이서정</td>
          <td>yyyy-mm-dd</td>
          <td>222</td>
        </tr>
        <tr>
          <th scope="row"><a href="" class="text-decoration-none">제목23432411111111111111111111111111111</a></th>
          <td>이기적</td>
          <td>yyyy-mm-dd</td>
          <td>222</td>
        </tr>

        <tr>
          <th scope="row"><a href="" class="text-decoration-none">제목1234234111111111111111111111111111</a></th>
          <td>남덕환</td>
          <td>yyyy-mm-dd</td>
          <td>222</td>
        </tr>

        <tr>
          <th scope="row"><a href="" class="text-decoration-none">제목111111111325132411111</a></th>
          <td>김정욱</td>
          <td>yyyy-mm-dd</td>
          <td>222</td>
        </tr>

        <tr>
          <th scope="row"><a href="" class="text-decoration-none">제목11111111111152311234324234111</a></th>
          <td>진경아</td>
          <td>yyyy-mm-dd</td>
          <td>222</td>
        </tr>
      </tbody>
    </table>

    <!--작성하기 버튼-->
    <a class="btn btn-primary" href="writePage" role="button">작성하기</a>

    <!--창 하단 페이지 숫자-->
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center">
        <li class="page-item disabled">
          <a class="page-link" href="#" tabindex="-1" aria-disabled="true">이전</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">4</a></li>
        <li class="page-item"><a class="page-link" href="#">5</a></li>
        <li class="page-item">
          <a class="page-link" href="#">다음</a>
        </li>
      </ul>
    </nav>
    <div class="bottomsearch" style="display: flex; margin-left: 30%; margin-top: 50px;">
      <select class="form-select form-select-sm" aria-label=".form-select-sm example"
        style="width: 90px; margin-right: 10px;">
        <option value="1">제목</option>
        <option value="2">작성자</option>
      </select>
      <input type="text" class="form-control" aria-label="title" aria-describedby="basic-addon1" style="width: 300px;">
      <button type="button" class="btn btn-primary" style="margin-left: 10px;">검색</button>
    </div>
  </div>
	  
	<!-- footer include -->
	<%@include file="/WEB-INF/views/footer.jsp"%>
  
  <!--end of container-->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
    crossorigin="anonymous"></script>
</body>

</html>