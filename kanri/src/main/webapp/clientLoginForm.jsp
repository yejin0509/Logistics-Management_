<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客ログイン</title> <!-- 일본어로 "고객 로그인" -->
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
body {
	font-family: 'Arial', sans-serif;
	background-color: #eaeaea; /* 배경 색상 */
	display: flex;
	justify-content: center; /* 화면 중앙 정렬 */
	align-items: center; /* 화면 중앙 정렬 */
	height: 100vh; /* 화면 전체 높이 사용 */
}

.login-box {
	background: #ffffff; /* 로그인 박스 배경 색상 */
	padding: 30px 40px; /* 로그인 박스 여백 */
	border-radius: 10px; /* 로그인 박스 테두리 둥글게 처리 */
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* 그림자 효과 */
	width: 350px; /* 로그인 박스 너비 */
}

.login-box input[type="text"], .login-box input[type="password"] {
	width: 100%; /* 입력란의 너비 100%로 설정 */
	padding: 10px; /* 입력란 여백 */
	margin-top: 5px; /* 상단 여백 */
	margin-bottom: 10px; /* 하단 여백 */
	border: 1px solid #ccc; /* 테두리 색상 */
	border-radius: 5px; /* 테두리 둥글게 처리 */
	box-sizing: border-box; /* 박스 크기 계산 방식 */
}

.login-box input[type="submit"] {
	width: 100%; /* 버튼 너비 100%로 설정 */
	padding: 10px; /* 버튼 여백 */
	background-color: black; /* 버튼 배경색 */
	color: #ffffff; /* 버튼 글자 색상 */
	border: none; /* 버튼 테두리 제거 */
	border-radius: 5px; /* 버튼 테두리 둥글게 처리 */
	cursor: pointer; /* 버튼에 마우스를 올리면 커서 변경 */
	font-weight: bold; /* 버튼 글씨 굵게 설정 */
}

.login-box input[type="submit"]:hover {
	background-color: #0056b3; /* 버튼에 마우스를 올렸을 때 배경색 변경 */
}

.login-box .login-links {
	margin-top: 15px; /* 로그인 링크 위쪽 여백 */
	text-align: center; /* 로그인 링크 가운데 정렬 */
}

.login-box .login-links a {
	text-decoration: none; /* 링크 밑줄 제거 */
	font-size: 14px; /* 링크 폰트 크기 */
}

.login-box span {
	color: red; /* 오류 메시지 색상 */
	font-size: 0.9em; /* 오류 메시지 폰트 크기 */
}
</style>
</head>
<body>
	<div class="login-box">
		<h2>顧客ログイン</h2> <!-- 일본어로 "고객 로그인" -->
		<form action="/kanri/clientLogin.do" method="post">

			<!-- ID 또는 비밀번호가 일치하지 않을 때 오류 메시지 표시 -->
			<c:if test="${errors.idOrPwNotMatch}">
				<span>IDとパスワードが一致しません</span> <!-- 일본어로 "ID와 비밀번호가 일치하지 않습니다" -->
				<br />
				<br />
			</c:if>

			<p>
				<!-- ID 입력란 -->
				<br /> <input type="text" name="client_Id" placeholder="ID"
					value="${param.client_Id}"> <!-- 이미 입력된 ID 값 유지 -->
				<!-- ID가 입력되지 않았을 경우 오류 메시지 표시 -->
				<c:if test="${errors.client_Id}">
					<span>IDを入力してください</span> <!-- 일본어로 "ID를 입력해주세요" -->
				</c:if>

				<br /> <input type="password" name="password"
					placeholder="パスワード"> <!-- 일본어로 "비밀번호" -->
				<!-- 비밀번호가 입력되지 않았을 경우 오류 메시지 표시 -->
				<c:if test="${errors.password}">
					<span>パスワードを入力してください</span> <!-- 일본어로 "비밀번호를 입력해주세요" -->
				</c:if>
			</p>

			<p>
				<!-- 로그인 버튼 -->
				<input type="submit" value="ログイン"> <!-- 일본어로 "로그인" -->
			</p>

			<div class="login-links">
				<!-- 회원가입 링크 -->
				<a href="/kanri/clientJoin.do">会員登録</a> <!-- 일본어로 "회원가입" -->
			</div>
		</form>
	</div>
</body>
</html>
