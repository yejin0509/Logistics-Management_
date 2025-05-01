<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title> <!-- 페이지 제목: 관리자 메뉴 -->

<!-- Bootstrap CSS 라이브러리 연결 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- 일본어 폰트 설정 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

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
    justify-content: center; /* 수평 중앙 정렬 */
    align-items: center; /* 수직 중앙 정렬 */
    height: 100vh;
}

.container1 {
    position: relative;
    width: 50%; /* 컨테이너 너비 설정 */
    height: 700px; /* ✅ frame 높이보다 큰 고정 높이 지정 (임시값) */
    top: -15%; /* 위치 조정 */
}

/* frame 이미지를 맨 위로 시각적으로 배치, 클릭 무시 */
.frame {
    width: 100%; /* 프레임 너비 */
    height: auto;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 100; /* 이미지가 다른 요소들 위에 위치하도록 설정 */
    pointer-events: none; /* 클릭 이벤트 무시 */
}

/* 박스 공통 스타일 */
.box1, .box2, .box3, .box4 {
    position: absolute;
    width: 40%; /* 박스 너비 설정 */
    z-index: 10; /* 박스가 프레임 위에 오도록 설정 */
    background-size: cover; /* 배경 이미지 덮어쓰기 */
    background-position: center; /* 배경 이미지 중앙 정렬 */
}

/* 박스1 스타일 */
.box1 {
    top: 10%; /* 박스1의 상단 위치 설정 */
    left: 7%; /* 박스1의 좌측 위치 설정 */
    background-image: url('./images/inventory_list_box.png'); /* 기본 배경 이미지 */
    display: block;
    width: 55%; /* 부모 요소의 크기에 맞게 크기 설정 */
    height: auto;
    padding-top: 56.25%; /* 16:9 비율 유지 */
    transition: background-image 0.5s ease; /* 배경 이미지 전환 효과 */
    background-size: contain; /* 이미지가 잘리지 않도록 contain 사용 */
    background-repeat: no-repeat; /* 이미지 반복 방지 */
    background-position: center; /* 이미지가 중앙에 위치하도록 설정 */
}

/* 박스1에 마우스를 올리면 이미지 변경 */
.box1:hover {
    background-image: url('./images/inventory_list_box_open.png'); /* 마우스를 올리면 열림 이미지로 변경 */
}

/* 박스2 스타일 */
.box2 {
    top: 10%; /* 박스2의 상단 위치 설정 */
    left: 40%; /* 박스2의 좌측 위치 설정 */
    background-image: url('./images/order_box.png'); /* 기본 배경 이미지 */
    display: block;
    width: 55%; /* 부모 요소의 크기에 맞게 크기 설정 */
    height: auto;
    padding-top: 56.25%; /* 16:9 비율 유지 */
    transition: background-image 0.5s ease; /* 배경 이미지 전환 효과 */
    background-size: contain; /* 이미지가 잘리지 않도록 contain 사용 */
    background-repeat: no-repeat; /* 이미지 반복 방지 */
    background-position: center; /* 이미지가 중앙에 위치하도록 설정 */
}

/* 박스2에 마우스를 올리면 이미지 변경 */
.box2:hover {
    background-image: url('./images/order_box_open.png'); /* 마우스를 올리면 열림 이미지로 변경 */
}

/* 박스3 스타일 */
.box3 {
    top: 46.3%; /* 박스3의 상단 위치 설정 */
    left: 7%; /* 박스3의 좌측 위치 설정 */
    background-image: url('./images/inbound_history_box.png'); /* 기본 배경 이미지 */
    display: block;
    width: 55%; /* 부모 요소의 크기에 맞게 크기 설정 */
    height: auto;
    padding-top: 56.25%; /* 16:9 비율 유지 */
    transition: background-image 0.5s ease; /* 배경 이미지 전환 효과 */
    background-size: contain; /* 이미지가 잘리지 않도록 contain 사용 */
    background-repeat: no-repeat; /* 이미지 반복 방지 */
    background-position: center; /* 이미지가 중앙에 위치하도록 설정 */
}

/* 박스3에 마우스를 올리면 이미지 변경 */
.box3:hover {
    background-image: url('./images/inbound_history_box_open.png'); /* 마우스를 올리면 열림 이미지로 변경 */
}

/* 박스4 스타일 */
.box4 {
    top: 46.3%; /* 박스4의 상단 위치 설정 */
    left: 40%; /* 박스4의 좌측 위치 설정 */
    background-image: url('./images/outbound_history_box.png'); /* 기본 배경 이미지 */
    display: block;
    width: 55%; /* 부모 요소의 크기에 맞게 크기 설정 */
    height: auto;
    padding-top: 56.25%; /* 16:9 비율 유지 */
    transition: background-image 0.5s ease; /* 배경 이미지 전환 효과 */
    background-size: contain; /* 이미지가 잘리지 않도록 contain 사용 */
    background-repeat: no-repeat; /* 이미지 반복 방지 */
    background-position: center; /* 이미지가 중앙에 위치하도록 설정 */
}

/* 박스4에 마우스를 올리면 이미지 변경 */
.box4:hover {
    background-image: url('./images/outbound_history_box_open.png'); /* 마우스를 올리면 열림 이미지로 변경 */
}

/* 로고 스타일 */
.logo {
    position: fixed;
    max-width: 6.2%; /* 로고 크기 설정 */
    height: auto;
    z-index: 101; /* 로고가 다른 요소들 위에 오도록 설정 */
}

/* 헤더 스타일 */
header {
    background-color: #fff; /* 헤더 배경색 흰색 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
    padding: 20px 0; /* 상하 패딩 */
}

/* 푸터 스타일 */
footer {
    border-top: 1px solid #ddd; /* 상단에 선 추가 */
    margin-top: 50px; /* 위쪽 간격 설정 */
    padding: 20px 0; /* 상하 패딩 */
    text-align: center; /* 텍스트 중앙 정렬 */
    color: #777; /* 글자 색 회색 */
    font-size: 14px; /* 글자 크기 설정 */
}
</style>
</head>
<body>

    <!-- 로고 이미지 -->
    <img alt="logo" src="./images/logo.png?ver=2" class="logo"> <!-- 로고 이미지 (버전 관리 포함) -->
    
    <!-- Header 섹션 -->
	<header>
		<div class="container">
			<h1 class="text-center text-dark">MENU</h1> <!-- 메뉴 제목 -->
		</div>
	</header>

    <div class="body">
        <div class="container1">

            <!-- 박스 링크들 -->
            <a href="nothingInclude.do">
                <div class="box1"></div> <!-- 첫 번째 박스 (이미지로 처리) -->
            </a>
            <a href="manager_order">
                <img class="box2"> <!-- 두 번째 박스 (이미지로 처리) -->
            </a>
            <a href="list.do">
                <img class="box3"> <!-- 세 번째 박스 (이미지로 처리) -->
            </a>
            <a href="list2.do">
                <img class="box4"> <!-- 네 번째 박스 (이미지로 처리) -->
            </a>

            <!-- 프레임 이미지 -->
            <img src="./images/frame.png" alt="frame" class="frame"> <!-- 배경 프레임 이미지 -->

        </div>
    </div>
</body>
</html>
