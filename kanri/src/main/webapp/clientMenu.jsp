<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
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
	font-family: "Kosugi Maru", sans-serif;
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
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container1 {
	position: relative;
	width: 50%;
	height: 700px; /* ✅ frame 높이보다 큰 고정 높이 지정 (임시값) */
	top: -15%;
}

/* frame 이미지를 맨 위로 시각적으로 배치, 클릭 무시 */
.frame {
	width: 100%;
	height: auto;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 100;
	pointer-events: none;
}

/* 박스 공통 */
.box1, .box2, .box3, .box4 {
	position: absolute;
	width: 100%;
	z-index: 10;
	background-size: cover;
	background-position: center;
}

.box1 {
	top: 7.6%;
	background-image: url('./images/order_box.png');
	display: block;
	width: 70%; /* 크기를 더 크게 */
	height: auto;
	padding-top: 40%; /* 비율을 수정하여 높이를 키움 */
	transition: background-image 0.5s ease;
	background-size: contain;
	background-repeat: no-repeat;
	background-position: center;
}

/* 마우스를 올리면 이미지 변경 */
.box1:hover {
	background-image: url('./images/order_box_open.png');
}

.logo {
	position: fixed;
	max-width: 6.2%;
	height: auto;
	z-index: 101;
}

header {
	background-color: #fff;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	padding: 20px 0;
}

footer {
	border-top: 1px solid #ddd;
	margin-top: 50px;
	padding: 20px 0;
	text-align: center;
	color: #777;
	font-size: 14px;
}
</style>
</head>
<body>

	<!-- 로고 -->
	<img alt="logo" src="./images/logo.png?ver=2" class="logo">

	<!-- Header -->
	<header>
		<div class="container">

			<h1 class="text-center text-dark">MENU</h1>
		</div>
	</header>


	<div class="body">
		<div class="container1">

			<!-- 박스 링크들 -->
			<a href="client_order">
				<div style="width: 95%; height: 95%;" class="box1"></div> <!-- img에서 div로 변경 -->
			</a>


			<!-- 프레임 이미지 -->
			<img src="./images/frame2.png" alt="frame" class="frame">

		</div>
	</div>
</body>
</html>
