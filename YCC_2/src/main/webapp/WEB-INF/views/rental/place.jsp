<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<!-- head & meta tag include -->
	<%@include file="/WEB-INF/views/metahead.jsp" %>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<link rel="stylesheet" href="/ycc/resources/css/datepicker.css">
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script src="/ycc/resources/js/jquery-ui.js"></script>
	 
    <title>Young문화센터 - 대관신청</title>
    <style>
	    td { text-align: center; }
	    .ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight {background: #ffc107;}
	    .wrap-slider > h4 {
	        margin: 0 0em;
		    line-height: 16px;
		    font-size: 14px;
		    padding: 0px;
		    font-weight: bold;
	    }
/* 	    .place-img {
			width: 500px;
			height: 250px;
			object-fit: cover;
	    } */
    </style>
</head>

<body>
<!-- header inlcude -->
<%@include file="/WEB-INF/views/header.jsp" %>

<div class="container">
	<div class="rentalnotice  border border-dark m-5" style="margin: 20px auto 30px auto">
		<ol class="fs-7 m-3">
			<h3 class="p-3 text-center">대관 신청 시 주의사항</h3>
			<li class="py-2">대관 현황 메뉴에서 사용하시고자 하는 시간과 공간의 예약 현황을 먼저 확인하신 후 대관 신청해주십시오.</li>
			<li class="py-2">사용자가 사용예정일포함 4일 전에 계약 해지를 서면으로 통보하여 승인을 얻은 경우 납부액의 100% 환불이 가능합니다.</li>
			<li class="py-2">회비 및 참가비가 있는 수익성 사업이나 공연(1~2인 소규모 공연도 해당)이 포함된 행사 및 정치ㆍ종교성 행사는 대관할 수 없습니다.</li>
			<li class="py-2">대관료는 대관 희망일 포함 4일 전까지 입금 해주셔야 하며, 입금하지 않은 행사는 대관 취소 처리되어 사용이 불가능합니다.</li>
			<li class="py-2">행사 시 음식물이 포함된 케이터링은 불가합니다. (김밥, 샌드위치, 햄버거 등 냄새가 많이 나는 음식물 반입 불가)</li>
		</ol>
	</div>

	<div>
		<div class="mb-3">
			<h5 style="display:inline-block">대관 장소 선택</h5>
			<select id="pickplace" name="croom_id" onchange="placeSelect()">
			<option value="">장소 선택</option>
			<optgroup label="외부">
				<!-- 외부 location code = 0 -->
				<c:forEach var="result" items="${placelist }">
					<option value="${result.croom_id}" <c:if
						test="${result.croom_location ne '외부'}">hidden</c:if>
						>${result.croom_name}</option>
				</c:forEach>
			</optgroup>
			<optgroup label="1층">
				<!-- 1층 location code = 1 -->
				<c:forEach var="result" items="${placelist }">
					<option value="${result.croom_id}" <c:if
						test="${result.croom_location ne '1층'}">hidden</c:if>
						>${result.croom_name}</option>
				</c:forEach>
			</optgroup>
			<optgroup label="2층">
				<!-- 2층 location code = 2 -->
				<c:forEach var="result" items="${placelist }">
					<option value="${result.croom_id}" <c:if
						test="${result.croom_location ne '2층'}">hidden</c:if>
						>${result.croom_name}</option>
				</c:forEach>
			</optgroup>
			<optgroup label="3층">
				<!-- 3층 location code = 3 -->
				<c:forEach var="result" items="${placelist }">
					<option value="${result.croom_id}" <c:if
						test="${result.croom_location ne '3층'}">hidden</c:if>
						>${result.croom_name}</option>
				</c:forEach>
			</optgroup>
		</select>      
		
		<div> 
			<label for="datetime-local">
				<h5 style="display:inline-block">대여 일자 지정</h5>
			</label> 
			<input type="text" id="datetime-local" name="prental_de">   
			<button id="viewBtn" class="btn btn-primary btn-sm">조회하기</button>
		</div>          
	</div>
	</div>
	
	<!-- 캐러셀 -->
	<div class="row mb-3">
		<div class="col-12 col-md-6 " id="change">
			<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
				<div class="embed-responsive embed-responsive-4by3">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="<%=request.getContextPath()%>/resources/img/rental/1f.jpg"
								class="d-block w-100 embed-responsive-item" alt="1층 시설 안내">
						</div>
						<div class="carousel-item">
							<img src="<%=request.getContextPath()%>/resources/img/rental/2f.jpg"
								class="d-block w-100 embed-responsive-item" alt="2층 시설 안내">
						</div>
						<div class="carousel-item">
							<img src="<%=request.getContextPath()%>/resources/img/rental/3f.jpg"
								class="d-block w-100 embed-responsive-item" alt="3층 시설 안내">
						</div>
						<div class="carousel-item">
							<img src="<%=request.getContextPath()%>/resources/img/rental/4f.jpg"
								class="d-block w-100 embed-responsive-item" alt="4층 시설 안내">
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">이전</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">다음</span>
				</button>
		</div>
	</div>
	<!-- 시간 테이블 -->
		<div id="timeTable" class="col-12 col-md-6" style="text-align: center;">
		</div>
	</div>

	<div class="container">
		<!--대관 장소 선택 시작-->
		<br>
		<hr>
		<br>
		<!-- Button trigger modal -->
		<button type="button" id="modalBtn" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal">예약하기</button>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">정보확인 안내</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
							<div>
								<h4>대여 장소</h4>
								<img class="place-img" style="width: 100%; height: 250px;">
							</div>
							<table class="table">
							<tr>
								<th>이름</th>
								<td id="customer" colspan="3">${sessionScope.name }</td>
							</tr>
							<tr>
								<th>시설명</th> <!-- croom_name -->
								<td id="Chkplace" colspan="3">
								</td>
							</tr>
							<tr>
								<th>예약 날짜</th><!-- = prental_de -->
								<td id="Chkdate" colspan="3">
								</td>
							</tr>
							<tr>
								<th>예약 시간</th><!-- = prental_time_info -->
								<td id="Chktime" colspan="3">
								</td>
							</tr>
							<tr>
								<th>결제 금액</th>
								<td id="cost" colspan="3">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" id="renBtn">확인</button>
					<!-- 확인 버튼 누를 시 모달에서 보여준 값들 DB로 넘어감 -->
				</div>
			</div>
		</div>
	</div>
					
<script type="text/javascript">

let closedDate = []
let mergeInfo = []
let infoTimeList = []
let infoTimeObj = {}
let infoDate = []
let infoCroom = []
let filteredList = []
let total = []
let timeList = {}
let time1 = $('#time1').children().first().text()
let time2 = $('#time2').children().first().text()
let time3 = $('#time3').children().first().text()
let time4 = $('#time4').children().first().text()
let time5 = $('#time5').children().first().text()
let time6 = $('#time6').children().first().text()
let checkedTimeList = []
let checkedTime
const time = document.getElementById('Chktime')

// 장소랑 날짜 선택했는지 체크
function nullCheck() {
	let croom_id = document.getElementById("pickplace").value
	let date = document.getElementById("datetime-local").value
	if(!croom_id || !date){
		alert("선택되지 않은 값이 있습니다.")
	}
}

// 날짜선택 데이트피커
$("#datetime-local").datepicker({
 		beforeShowDay: function(date){	// 데이트피커 열리기 전에 마감된 날짜 disable시키는 기능
	       var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
	       return [ closedDate.indexOf(string) == -1 ]
		},
		dateFormat: "yy-mm-dd", // 텍스트 필드에 입력되는 날짜 형식.
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		showMonthAfterYear: true,
		yearSuffix: '년',
		minDate: 0, 
		onSelect: function() {	// 날짜 선택시
			let croom_id = document.getElementById("pickplace").value
			if(!croom_id){		// 장소 선택이 안되어 있으면 날짜 선택 못하게 함
				alert("장소를 먼저 선택해 주세요.")
				$('input#datetime-local.hasDatepicker').val(' ')	// 날짜 클릭시 해당 날짜를 출력하지 않고 빈값 출력
			}
		}
})

// 장소 선택시 
function placeSelect() {
	var croom_id = document.getElementById("pickplace").value
	console.log(croom_id)
	
	// 배열 초기화
	filteredList.length = 0
	closedDate.length = 0
	
	$.ajax({
		type: 'get',			//요청 메서드
		url: '/ycc/rental/place.select',		//요청 URI
		headers: { "content-type" : "application/json" },		//요청 헤더
		dataType: 'json',	//전송받을 데이터의 타입(서버에서 반환되는 데이터 형식)
		data: {croom_id:croom_id},		//서버로 전송할 데이터. stringify()로 직렬화 필요
		success : function(data) {		//서버로부터 응답이 도착하면 호출될 함수
			console.log(data)
 			for(var i=0; i<data.placeList.length; i++) {
				infoDate.push(data.placeList[i].prental_de)
			}
			let infoDateSet = new Set(infoDate);
			const infoDateList = [...infoDateSet];
			console.log("infoDateList = ", infoDateList)
			
			for( var i = 0; i< data.placeList.length; i++ ) {
			function find(element) {
				if(element.prental_de === infoDateList[i]) {
					return true
				}
			}
			
			let filteredDate = data.placeList.filter(find)
			filteredList.push(filteredDate)
			}

			// filteredList의 빈 값 제거
			for(var i = 0; i < filteredList.length; i++){ 
				  if (filteredList[i].length === 0) { 
				    filteredList.splice(i, 1); 
				    i--; 
				  }
			}
			
			// filteredList의 배열 길이가 6이면 closedDate 리스트에 추가
 			for(var i = 0; i < filteredList.length; i++){ 
				  if (filteredList[i].length === 6) { 
					  closedDate.push(filteredList[i][0].prental_de)
				  }
			}
			console.log("filteredList = ", filteredList)
			console.log("closedDate = ", closedDate)
		},
		error : function(data) { 
			alert("error") 
			console.log(data) 
		}
	}) 
}

// 로그인되어 있는지 체크
function loginCheck(){ 
		var id = '<%=(String)session.getAttribute("id")%>';
		var toURL = window.location.href
		if(id=="null") { 
			window.location.replace("http://localhost:8080/ycc/login?toURL=" + toURL);
			return false
		}
		return true
}

const area = document.getElementById('Chkplace')
area.innerHTML = document.getElementById("pickplace").value
const date = document.getElementById('Chkdate')
date.innerHTML = document.getElementById("datetime-local").value

// 시간테이블 출력
let toTimeList = function(data) {
	let tmp = ''
		tmp += '<table class="table table-striped">'
		tmp += '<thead>'
		tmp += '<tr>'
		tmp += '<th scope="col" style="text-align: center;">시간</th>'
		tmp += '<th scope="col" style="text-align: center;">예약</th>'
		tmp += '</tr>'
		tmp += '</thead>'
		tmp += '<tbody>'
		tmp += '<div>'
		tmp += '<tr id="time1"><td>08:10 ~ 10:00</td><td><input type="checkbox" name="time"></td></tr>'
		tmp += '<tr id="time2"><td>10:10 ~ 12:00</td><td><input type="checkbox" name="time"></td></tr>'
		tmp += '<tr id="time3"><td>12:10 ~ 14:00</td><td><input type="checkbox" name="time"></td></tr>'
		tmp += '<tr id="time4"><td>14:10 ~ 16:00</td><td><input type="checkbox" name="time"></td></tr>'
		tmp += '<tr id="time5"><td>16:10 ~ 18:00</td><td><input type="checkbox" name="time"></td></tr>'
		tmp += '<tr id="time6"><td>18:10 ~ 20:00</td><td><input type="checkbox" name="time"></td></tr>'
		tmp += '</div>'
		tmp += '</tbody>'
	return tmp += ''
}

$(document).ready(function(){
	
	$('#modalBtn').hide();	// 예약하기 버튼 숨김
	
	// 예약하기 버튼 클릭시 모달창 뜸
	$("#modalBtn").on("click", function(){
		
		let cost = 10000 * $('input:checkbox:checked').length
		// 예약 확인 모달 창의 결제 금액 부분
		let chkCost = document.getElementById('cost')
		chkCost.innerHTML = cost + '원'
		
		let swimSRC = '<%=request.getContextPath()%>/resources/img/rental/swimmingpool.jpg'
		let socSRC = '<%=request.getContextPath()%>/resources/img/rental/soccerfield.jpg'
		let futSRC = '<%=request.getContextPath()%>/resources/img/rental/futsalfield.jpg'
		let croomSRC = '<%=request.getContextPath()%>/resources/img/rental/classroom.jpg'
		let place = $('#Chkplace').text()	// 예약 확인 모달 창의 시설명
		
		if(place == '수영장') {
			$('.place-img').attr('src', swimSRC)
		} else if(place == '축구장') {
			$('.place-img').attr('src', socSRC)
		} else if(place == '풋살장') {
			$('.place-img').attr('src', futSRC)
		} else {
			$('.place-img').attr('src', croomSRC)
		}
			
		$('input:checkbox').each(function (index) {
			if($(this).is(":checked")==true){		// 선택된 체크박스
				if($('input:checkbox:checked').length == 1) {	// 선택된 체크박스가 한 개면 해당 시간을 그대로 모달창에 출력
					checkedTime = $(this).parent().prev().text()
					time.innerHTML = checkedTime
				} else {	// 선택된 체크박스가 한 개 이상이면 해당되는 시간들을 checkedTimeList 배열에 넣고 
							// startTime과 endTime을 배열에서 꺼내온 다음 모달창에 출력
					checkedTimeList.push($(this).parent().prev().text())
					let startTime = checkedTimeList[0]
					startTime = startTime.substr(0, 6)
					let endTime = checkedTimeList[checkedTimeList.length - 1]
					endTime = endTime.substr(6, 11)
					time.innerHTML = startTime + endTime
				}
			}
		})
	})  
	
	// 모달창에서 확인 버튼 눌렀을 때
	$("#renBtn").click(function(){
		
		const user_id = '<%=(String)session.getAttribute("id")%>';
		const date = document.getElementById("datetime-local").value
		const croom_id = document.getElementById("pickplace").value
		let key	
		
 		timeList = {	
			time1: time1,
			time2: time2,
			time3: time3,
			time4: time4,
			time5: time5,
			time6: time6
		}; 

		$('input:checkbox').each(function (index) {
			if($(this).is(":checked")==true){
				checkedTime = $(this).parent().prev().text()	// 체크된 시간 텍스트(08:10 ~ 10:00)를 checkedTime에 할당
				if($('input:checkbox:checked').length == 1) {	// 시간 하나만 체크했을 때
					for(var k in timeList) {
						if(checkedTime == timeList[k]) {
							timeList[k] = "true"
						} else {
							timeList[k] = "false"
						}
					}
				} 
			}

		})
		
		function getKeyByValue(obj, value) {
			return Object.keys(obj).find(key => obj[key] === value);
		}
		
		// 한 번에 시간 여러개 선택했을 때
		if($('input:checkbox:checked').length > 1) {
			for(var i = 0; i < checkedTimeList.length; i++) {
				for (var j = 0; j < Object.values(timeList).length; j++) {	// checkedTimeList의 값을 timeList의 모든 값과 비교(time1~6)
					console.log("checkedTimeList[i]", checkedTimeList[i])
					console.log("Object.values(timeList)[j]", Object.values(timeList)[j])
					
					if (checkedTimeList[i] == Object.values(timeList)[j]) {			// 그러다가 일치하면
						console.log("일치")
						key = getKeyByValue(timeList, Object.values(timeList)[j]);	// 해당 값을 가진 key를 key에 할당
						console.log("key = ", key)
						timeList[key] = "true"
						if( i != checkedTimeList.length - 1) {
							console.log("i == checkedTimeList.length - 1")
							break;
						}
					} else {
						console.log("불일치")
						key = getKeyByValue(timeList, Object.values(timeList)[j]);
						console.log("key = ", key)
						if(timeList[key] != "true") {
							timeList[key] = "false"
						}
					}
					
				}
			}
		}
		
		if (!confirm("예약하시겠습니까?")) {
			$('#exampleModal').modal('hide')
		} else {
			$.ajax({
				type: 'post',			//요청 메서드
				url: '/ycc/rental/place.do',		//요청 URI
				data: {user_id:user_id, date:date, croom_id:croom_id, timeList:JSON.stringify(timeList)},		//서버로 전송할 데이터. stringify()로 직렬화 필요
				success : function(data) {		//서버로부터 응답이 도착하면 호출될 함수
				},
				error : function(data) { 
					alert("error") 
					console.log(data) 
				}
			}) 
			alert("예약이 완료되었습니다.")
			$('#exampleModal').modal('hide')
			window.location.reload() 
		}
	})
                        	
    // 조회하기 버튼 눌렀을 때                    	
	$("#viewBtn").click(function(){
		if(!nullCheck()){
			$('#modalBtn').show();
		}
		var date = document.getElementById("datetime-local").value
		var croom_id = document.getElementById("pickplace").value
		const area = document.getElementById('Chkplace')
		console.log(date)
		$.ajax({
			type: 'GET',
			url: '/ycc/rental/place.send', //수정요망
			headers: { 'content-type': 'application/json' },
			data: {croom_id:croom_id , date:date},
			dataType: 'json',
			success: function(data){
				// 모달창의 장소 부분에 croom_id가 아닌 croom_name을 출력
				area.innerHTML = $('option[value='+croom_id+']').first().text()
				if(closedDate.includes(date)) {
					alert("예약 가능한 시간이 없습니다.")
				}
		
				$("#timeTable").html(toTimeList(data))
				
				let today = new Date();
				let month = today.getMonth()+1;
				let tdDate = today.getDate();
				
				let calDateFm = new Date(date);		// 선택된 날짜를 date 타입으로 포맷
				let calYear = calDateFm.getFullYear();
				let calMonth = calDateFm.getMonth();
				let calDate = calDateFm.getDate();
				
				let time1Chk = $("#time1").children().eq(1).children().first()
				let time2Chk = $("#time2").children().eq(1).children().first()
				let time3Chk = $("#time3").children().eq(1).children().first()
				let time4Chk = $("#time4").children().eq(1).children().first()
				let time5Chk = $("#time5").children().eq(1).children().first()
				let time6Chk = $("#time6").children().eq(1).children().first()
				
				time1 = $('#time1').children().first().text()
				time2 = $('#time2').children().first().text()
			    time3 = $('#time3').children().first().text()
			    time4 = $('#time4').children().first().text()
			    time5 = $('#time5').children().first().text()
			    time6 = $('#time6').children().first().text()
			  	
				startHours1 = time1.slice(0, 2)
				startMinutes1 = time1.slice(3, 5)
				let time1NDate = new Date(calYear, calMonth, calDate, startHours1, startMinutes1)
				const time1Month = time1NDate.getMonth()+1
				const time1Date = time1NDate.getDate()
				const elapsedMSec1 = today.getTime() - time1NDate.getTime()
				const elapsedHour1 = Math.floor(((elapsedMSec1 / (1000 * 60 * 60 )) % 24));
				const elapsedMin1 = Math.floor(((elapsedMSec1 / (1000 * 60 )) % 60 ));
				
				console.log("today=",today)
				console.log("time1NDate=",time1NDate)
				console.log("elapsedHour1=",elapsedHour1)
				console.log("elapsedMin1=",elapsedMin1)
				
 				startHours2 = time2.slice(0, 2)
				startMinutes2 = time2.slice(3, 5)
				let time2Date = new Date(calYear, calMonth, calDate, startHours2, startMinutes2)
				const elapsedMSec2 = today.getTime() - time2Date.getTime()
				const elapsedHour2 = Math.floor(((elapsedMSec2 / (1000 * 60 * 60 )) % 24));
				const elapsedMin2 = Math.floor(((elapsedMSec2 / (1000 * 60 )) % 60 ));
				
				startHours3 = time3.slice(0, 2)
				startMinutes3 = time3.slice(3, 5)
				let time3Date = new Date(calYear, calMonth, calDate, startHours3, startMinutes3)
				const elapsedMSec3 = today.getTime() - time3Date.getTime()
				const elapsedHour3 = Math.floor(((elapsedMSec3 / (1000 * 60 * 60 )) % 24));
				const elapsedMin3 = Math.floor(((elapsedMSec3 / (1000 * 60 )) % 60 ));
				
 				startHours4 = time4.slice(0, 2)
				startMinutes4 = time4.slice(3, 5)
				let time4Date = new Date(calYear, calMonth, calDate, startHours4, startMinutes4)
				const elapsedMSec4 = today.getTime() - time4Date.getTime()
				const elapsedHour4 = Math.floor(((elapsedMSec4 / (1000 * 60 * 60 )) % 24));
				const elapsedMin4 = Math.floor(((elapsedMSec4 / (1000 * 60 )) % 60 ));
				
 				startHours5 = time5.slice(0, 2)
				startMinutes5 = time5.slice(3, 5)
				let time5Date = new Date(calYear, calMonth, calDate, startHours5, startMinutes5)
				const elapsedMSec5 = today.getTime() - time5Date.getTime()
				const elapsedHour5 = Math.floor(((elapsedMSec5 / (1000 * 60 * 60 )) % 24));
				const elapsedMin5 = Math.floor(((elapsedMSec5 / (1000 * 60 )) % 60 ));
				
 				startHours6 = time6.slice(0, 2)
				startMinutes6 = time6.slice(3, 5)
				let time6Date = new Date(calYear, calMonth, calDate, startHours6, startMinutes6)
				const elapsedMSec6 = today.getTime() - time6Date.getTime()
				const elapsedHour6 = Math.floor(((elapsedMSec6 / (1000 * 60 * 60 )) % 24));
				const elapsedMin6 = Math.floor(((elapsedMSec6 / (1000 * 60 )) % 60 ));
				
				/* let testDate = new Date(2022, 11, 27, 15, 15)
				console.log("testDate=",testDate) */
			
 				if(time1Month == month && time1Date == tdDate) {
					if(elapsedHour1 > 0 || elapsedMin1 > 0) {
						time1Chk[0].disabled = true
					}			 	
 					if(elapsedHour2 > 0 || elapsedMin2 > 0) {
						time2Chk[0].disabled = true
					}	
					if(elapsedHour3 > 0 || elapsedMin3 > 0) {
						time3Chk[0].disabled = true
					} 
					if(elapsedHour4 > 0 || elapsedMin4 > 0) {
						time4Chk[0].disabled = true
					}
					if(elapsedHour5 > 0 || elapsedMin5 > 0) {
						time5Chk[0].disabled = true
					}
					if(elapsedHour6 > 0 || elapsedMin6 > 0) {
						time6Chk[0].disabled = true
					}
				}
				
				for(i in data.infoList) {
					if(data.infoList[i].time1 == true) {
						time1Chk[0].disabled = true
					}
					if(data.infoList[i].time2 == true) {
						time2Chk[0].disabled = true
					}
					if(data.infoList[i].time3 == true) {
						time3Chk[0].disabled = true
					}
					if(data.infoList[i].time4 == true) {
						time4Chk[0].disabled = true
					}
					if(data.infoList[i].time5 == true) {
						time5Chk[0].disabled = true
					}
					if(data.infoList[i].time6 == true) {
						time6Chk[0].disabled = true
					}
				}
				
				const chkDate = document.getElementById('Chkdate')
				chkDate.innerHTML = document.getElementById("datetime-local").value
				console.log(closedDate)
			}	//success
		})       
	}) //viewBtn
    
	// 로그인이 안되어 있으면 모달창이 열리는 걸 막음
	$("#exampleModal").on('show.bs.modal', function(e){
		loginCheck();
		if (loginCheck() == false) {
			e.preventDefault();
		}
		if ($('input:checkbox:checked').length == 0) {
			alert("시간을 선택해주세요.")
			e.preventDefault();
		}
	})
	
	let msg = "${msg}"
	if (msg == "REN_OK") alert("예약신청을 완료했습니다.")
	if (msg == "REN_ERR") alert("예약신청에 실패했습니다.")
})
</script>

	<!-- footer inlcude -->
	<%@include file="/WEB-INF/views/footer.jsp" %>
</body>

</html>