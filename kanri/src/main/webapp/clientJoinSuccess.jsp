<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録完了</title> <!-- 일본어로 "회원가입 완료" -->
<!-- 일본어 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">
<style>
* {
	font-family: "Kosugi Maru", sans-serif;  /* 일본어 폰트 설정 */
	font-weight: 400;
	font-style: normal;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #eaeaea;  /* 배경색 설정 */
	display: flex;
	justify-content: center;  /* 화면 중앙에 요소 배치 */
	align-items: center;  /* 화면 중앙에 요소 배치 */
	height: 100vh;  /* 전체 화면을 채우기 */
}

.complete-box {
	background: #ffffff;  /* 배경색 흰색 */
	padding: 30px 40px;  /* 여백 설정 */
	border-radius: 10px;  /* 둥근 모서리 */
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);  /* 그림자 효과 */
	width: 350px;  /* 너비 설정 */
	text-align: center;  /* 텍스트 가운데 정렬 */
}

.complete-box h2 {
	margin-bottom: 20px;  /* 제목과 본문 사이 여백 설정 */
}

.complete-box p {
	margin-bottom: 30px;  /* 본문과 버튼 사이 여백 설정 */
}

.complete-box button {
	width: 100%;  /* 버튼의 너비를 부모 요소에 맞게 설정 */
	padding: 10px;  /* 버튼 안쪽 여백 설정 */
	background-color: black;  /* 버튼 배경색 */
	color: #ffffff;  /* 버튼 글자 색상 */
	border: none;  /* 버튼 테두리 제거 */
	border-radius: 5px;  /* 버튼 테두리 둥글게 처리 */
	cursor: pointer;  /* 버튼에 마우스를 올리면 포인터 모양으로 변경 */
	font-weight: bold;  /* 버튼 글씨를 굵게 설정 */
}

.complete-box button:hover {
	background-color: #0056b3;  /* 버튼에 마우스를 올렸을 때 배경색 변경 */
}
</style>
</head>
<body>
	<div class="complete-box">
		<h2>会員登録完了</h2> <!-- 일본어로 "회원가입 완료" -->
		<p>
			ようこそ！ <br> <br>ログインしてサービスをご利用いただけます。 <!-- 일본어로 "환영합니다! 로그인하여 서비스를 이용하실 수 있습니다." -->
		</p>
		<!-- 로그인 버튼을 클릭하면 로그인 페이지로 이동 -->
		<form action="/kanri/clientLogin.do" method="get">
			<button type="submit">ログイン</button> <!-- 일본어로 "로그인" -->
		</form>
	</div>
</body>
</html>
