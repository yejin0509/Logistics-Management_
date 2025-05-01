<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホームページ</title> <!-- 페이지 제목: "홈페이지" -->
<!-- 일본어 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com"> <!-- Google Fonts에 연결 -->
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=M+PLUS+1p&display=swap" rel="stylesheet"> <!-- 폰트 설정 -->

<style>
* {
  font-family: "M PLUS 1p", sans-serif;  /* 전체 폰트 설정 */
  font-weight: 400;
  font-style: normal;
}
body {
	margin: 0;
	padding: 0;
	background-color: #525252;  /* 배경색 설정 */
}

.background {
	position: fixed;  /* 배경 이미지 고정 */
	display: block;
	margin: 0;
	padding: 0;
	max-width: 100%;  /* 최대 너비 100% */
	z-index: 5;  /* z-index로 레이어 설정 */
}

.shutter1 {
	position: fixed;  /* 왼쪽 셔터 고정 */
	display: block;
	margin: 0;
	padding: 0;
	left: 5%;  /* 왼쪽 5% */
	max-width: 50%;  /* 셔터 너비 50% */
	z-index: 1;  /* z-index로 레이어 설정 */
	top: 0; /* 초기 위치 */
	transition: top 3s ease-in-out; /* 부드러운 3초 애니메이션 */
}

.shutter2 {
	position: fixed;  /* 오른쪽 셔터 고정 */
	display: block;
	margin: 0;
	padding: 0;
	right: 5%;  /* 오른쪽 5% */
	max-width: 50%;  /* 셔터 너비 50% */
	z-index: 1;  /* z-index로 레이어 설정 */
	top: 0; /* 초기 위치 */
	transition: top 3s ease-in-out; /* 부드러운 3초 애니메이션 */
}

.logo {
	position: fixed;  /* 로고 고정 */
	max-width: 10%;  /* 로고 너비 10% */
	height: auto;
	z-index: 90;  /* 로고의 z-index 설정 */
}

.toLogin1 {
	position: absolute; /* 이미지 위에 클릭 가능한 박스를 올리기 위한 설정 */
	top: 0; /* 이미지의 맨 위에 배치 */
	left: 0; /* 왼쪽에 배치 */
	width: 50%; /* 박스의 너비 50% */
	height: 100%; /* 이미지 전체 높이 덮기 */
	z-index: 11; /* 셔터 위로 배치 */
	cursor: pointer; /* 클릭 가능한 영역을 표시 */
}

.toLogin2 {
	position: absolute; /* 이미지 위에 클릭 가능한 박스를 올리기 위한 설정 */
	top: 0; /* 이미지의 맨 위에 배치 */
	right: 0; /* 오른쪽에 배치 */
	width: 50%; /* 박스의 너비 50% */
	height: 100%; /* 이미지 전체 높이 덮기 */
	z-index: 11; /* 셔터 위로 배치 */
	cursor: pointer; /* 클릭 가능한 영역을 표시 */
}
</style>

<script>
  // 첫 번째 로그인 박스를 클릭했을 때 동작하는 스크립트
  document.addEventListener('DOMContentLoaded', function () {
    const loginLink = document.querySelector('.toLogin1');  /* 로그인 링크 선택 */
    const shutter = document.getElementById('shutter1');  /* 첫 번째 셔터 선택 */

    if (loginLink && shutter) {  /* 링크와 셔터가 있을 경우 */
      loginLink.addEventListener('click', function (event) {
        event.preventDefault(); // 링크 기본 동작 막기

        // 애니메이션 적용: 셔터를 위로 올려서 숨김
        shutter.style.transition = 'top 3s ease-in-out';
        shutter.style.top = '-90%';

        // 3초 후 페이지 이동
        setTimeout(() => {
          window.location.href = loginLink.href;  // 로그인 페이지로 이동
        }, 3000);  /* 3초 후 이동 */
      });
    }
  });

  // 두 번째 로그인 박스를 클릭했을 때 동작하는 스크립트
  document.addEventListener('DOMContentLoaded', function () {
    const loginLink = document.querySelector('.toLogin2');  /* 로그인 링크 선택 */
    const shutter = document.getElementById('shutter2');  /* 두 번째 셔터 선택 */

    if (loginLink && shutter) {  /* 링크와 셔터가 있을 경우 */
      loginLink.addEventListener('click', function (event) {
        event.preventDefault(); // 링크 기본 동작 막기

        // 애니메이션 적용: 셔터를 위로 올려서 숨김
        shutter.style.transition = 'top 3s ease-in-out';
        shutter.style.top = '-90%';

        // 3초 후 페이지 이동
        setTimeout(() => {
          window.location.href = loginLink.href;  // 로그인 페이지로 이동
        }, 3000);  /* 3초 후 이동 */
      });
    }
  });
</script>

</head>

<body>
	<!-- 관리자 로그인 박스를 클릭하면 managerLoginForm.jsp로 이동 -->
	<a href="managerLoginForm.jsp" class="toLogin1"></a>

	<!-- 고객 로그인 박스를 클릭하면 clientLoginForm.jsp로 이동 -->
	<a href="clientLoginForm.jsp" class="toLogin2"></a>

	<div>
		<!-- 로고 이미지 -->
		<img alt="logo" src="./images/logo.png?ver=2" class="logo"> 
		<!-- 배경 이미지 -->
		<img src="./images/shutter_login.png?ver=2" class="background" style="top: -10%;"> 
		
		<!-- 첫 번째 셔터 이미지 -->
		<img src="./images/shutter.png?ver=2" class="shutter1" id="shutter1"> 
		
		<!-- 두 번째 셔터 이미지 -->
		<img src="./images/shutter.png?ver=2" class="shutter2" id="shutter2">
	</div>
</body>
</html>
