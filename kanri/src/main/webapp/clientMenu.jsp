<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title> <!-- 일본어로 "관리자 메뉴" -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 일본어 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">

<style>
* {
	font-family: "Kosugi Maru", sans-serif; /* 일본어 폰트 설정 */
	font-weight: 400;
	font-style: normal;
}

body, html {
	margin: 0;
	padding: 0;
	height: 100vh;
	overflow: hidden; /* ✅ 스크롤 방지 */
}

.body {
	display: flex;
	justify-content: center; /* 화면 중앙 정렬 */
	align-items: center; /* 화면 중앙 정렬 */
	height: 100vh; /* 화면 전체 높이 사용 */
}

.container1 {
	position: relative;
	width: 50%;
	height: 700px; /* ✅ frame 높이보다 큰 고정 높이 지정 (임시값) */
	top: -15%; /* 화면 위쪽으로 위치 조정 */
}

/* frame 이미지를 맨 위로 시각적으로 배치, 클릭 무시 */
.frame {
	width: 100%; /* 프레임 너비 100% */
	height: auto;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 100; /* 다른 요소들 위로 표시 */
	pointer-events: none; /* 클릭 이벤트 차단 */
}

/* 박스 공통 스타일 */
.box1, .box2, .box3, .box4 {
	position: absolute;
	width: 100%; /* 박스 너비 100% */
	z-index: 10;
	background-size: cover; /* 배경 이미지 크기 조정 */
	background-position: center; /* 배경 이미지 가운데 정렬 */
}

.box1 {
	top: 7.6%; /* 박스 위치 조정 */
	background-image: url('./images/order_box.png'); /* 기본 이미지 설정 */
	display: block;
	width: 70%; /* 박스 크기 설정 */
	height: auto;
	padding-top: 40%; /* 비율을 수정하여 높이 설정 */
	transition: background-image 0.5s ease; /* 배경 이미지 변경 시 애니메이션 효과 */
	background-size: contain; /* 배경 이미지 비율 유지 */
	background-repeat: no-repeat; /* 배경 이미지 반복 방지 */
	background-position: center; /* 배경 이미지 가운데 정렬 */
}

/* 마우스를 올리면 이미지 변경 */
.box1:hover {
	background-image: url('./images/order_box_open.png'); /* hover 시 이미지 변경 */
}

.logo {
	position: fixed; /* 고정된 위치 */
	max-width: 6.2%; /* 로고 크기 조정 */
	height: auto;
	z-index: 101; /* 다른 요소들 위로 표시 */
}

header {
	background-color: #fff; /* 헤더 배경색 */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 헤더 그림자 */
	padding: 20px 0; /* 헤더 패딩 */
}

footer {
	border-top: 1px solid #ddd; /* 푸터 상단 경계선 */
	margin-top: 50px; /* 푸터 상단 여백 */
	padding: 20px 0; /* 푸터 패딩 */
	text-align: center; /* 푸터 내용 가운데 정렬 */
	color: #777; /* 푸터 텍스트 색상 */
	font-size: 14px; /* 푸터 폰트 크기 */
}
</style>
</head>
<body>

	<!-- 로고 이미지 -->
	<img alt="logo" src="./images/logo.png?ver=2" class="logo"> <!-- 일본어로 "로고" -->

	<!-- Header -->
	<header>
		<div class="container">

			<h1 class="text-center text-dark">MENU</h1> <!-- 일본어로 "메뉴" -->
		</div>
	</header>

	<!-- 본문 영역 -->
	<div class="body">
		<div class="container1">

			<!-- 박스 링크들 -->
			<a href="client_order">
				<div style="width: 95%; height: 95%;" class="box1"></div> <!-- 이미지에서 div로 변경 -->
			</a>

			<!-- 프레임 이미지 -->
			<img src="./images/frame2.png" alt="frame" class="frame"> <!-- 프레임 이미지 표시 -->
		</div>
	</div>
</body>
</html>
