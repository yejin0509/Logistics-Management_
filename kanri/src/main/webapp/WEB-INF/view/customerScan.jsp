<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入庫履歴</title> <!-- 페이지 제목: 입고 이력 -->

<!-- Bootstrap CSS 불러오기 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Google Fonts 불러오기 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

<style>
* {
    font-family: "Kosugi Maru", sans-serif; /* 기본 폰트 설정 */
    font-weight: 400; /* 기본 폰트 두께 */
    font-style: normal; /* 기본 폰트 스타일 */
}

header {
    background-color: #fff; /* 헤더 배경색 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 헤더 그림자 */
    padding: 20px 0; /* 패딩 설정 */
}

footer {
    border-top: 1px solid #ddd; /* 푸터 위 경계선 */
    margin-top: 50px; /* 푸터와 본문 간 여백 */
    padding: 20px 0; /* 푸터 패딩 */
    text-align: center; /* 텍스트 가운데 정렬 */
    color: #777; /* 글자 색 */
    font-size: 14px; /* 글자 크기 */
}

.logo {
    position: fixed; /* 로고 고정 */
    max-width: 6.2%; /* 로고 최대 너비 */
    height: auto; /* 로고 비율 유지 */
    z-index: 90; /* 다른 요소들 위로 표시 */
}

table {
    width: 100%; /* 테이블 너비 100% */
    border-collapse: collapse; /* 테이블 셀 경계 합침 */
}

thead th {
    border-bottom: 2px solid #000; /* 테이블 헤더 아래 경계선 */
    background-color: #f2f2f2; /* 테이블 헤더 배경색 */
}

tr:hover {
    background-color: #f9f9f9; /* 마우스를 올리면 배경색 변경 */
}

td, th {
    text-align: center; /* 텍스트 중앙 정렬 */
    padding: 8px; /* 셀 내 여백 */
    border: 1px solid #ccc; /* 셀 경계선 */
}

.mb-4 {
    margin-bottom: 2rem; /* 하단 여백 */
}

.text-end {
    text-align: right; /* 오른쪽 정렬 */
}

body {
    background-color: #f8f9fa; /* 페이지 배경색 */
}

</style>

</head>
<body>

	<!-- 로고 이미지 -->
	<a class="logo" href="managerMenu.jsp">
		<img alt="logo" src="./images/logo.png?ver=2" class="logo"> <!-- 로고 이미지 -->
	</a>

	<!-- 헤더 -->
	<header>
		<div class="container">
			<h1 class="text-center text-dark">入庫履歴</h1> <!-- 입고 이력 제목 -->
		</div>
	</header>

	<!-- 본문 내용 -->
	<main class="container my-4">

		<!-- 날짜 필터 -->
		<div class="mb-4">
			<form action="/kanri/date-filter.do" method="get" class="d-flex align-items-center gap-2">
				<label for="in_Date" class="form-label mb-0" style="white-space: nowrap;">注文履歴照会</label> <!-- 주문 이력 조회 -->
				<input type="date" id="in_Date" name="in_Date" value="${param.in_Date}" class="form-control" style="max-width: 200px;"> <!-- 날짜 선택 -->
				<button type="submit" class="btn btn-dark" style="white-space: nowrap;">日付照会</button> <!-- 날짜 조회 버튼 -->
			</form>
		</div>

		<!-- 입고 이력을 표시할 테이블 -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>No.</th> <!-- 번호 -->
					<th>管理者 ID</th> <!-- 관리자 ID -->
					<th>商品 ID</th> <!-- 상품 ID -->
					<th>注文日</th> <!-- 주문일 -->
					<th>数量</th> <!-- 수량 -->
					<th>合計金額</th> <!-- 합계 금액 -->
				</tr>
			</thead>
			<tbody>
				<!-- 서버에서 받은 입고 이력 데이터를 반복하여 출력 -->
				<c:forEach var="InBound" items="${InBoundPage.content}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td> <!-- 번호 -->
						<td>${InBound.manager_Id}</td> <!-- 관리자 ID -->
						<td>${InBound.product_Id}</td> <!-- 상품 ID -->
						<td><fmt:formatDate value="${InBound.in_Date}" pattern="yyyy年MM月dd日" /></td> <!-- 일본어 날짜 포맷 -->
						<td>${InBound.in_Count}</td> <!-- 수량 -->
						<td>${InBound.price}</td> <!-- 합계 금액 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 뒤로가기 버튼 -->
		<div class="text-end mt-3">
			<button type="button" onclick="history.back();" class="btn btn-secondary" style="width: 150px; height: 50px; font-size: 18px;">
				戻る <!-- 뒤로 가기 버튼 -->
			</button>
		</div>

	</main>

	<!-- 푸터 -->
	<footer>
		<div class="container">
			&copy; <%=java.time.Year.now()%> 物流管理 <!-- 저작권 표시 -->
		</div>
	</footer>

</body>
</html>
