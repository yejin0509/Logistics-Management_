<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Login</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #eaeaea;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-box {
	background: #ffffff;
	padding: 30px 40px;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
	width: 350px;
}



.login-box input[type="text"], .login-box input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
}

.login-box input[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: black;
	color: #ffffff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-weight: bold;
}

.login-box input[type="submit"]:hover {
	background-color: #0056b3;
}

.login-box .login-links {
	margin-top: 15px;
	text-align: center;
}

.login-box .login-links a {
	text-decoration: none;
	font-size: 14px;
}

.login-box span {
	color: red;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<div class="login-box">
		<h2>Client Login</h2>
		<form action="/kanri/clientLogin.do" method="post">

			<c:if test="${errors.idOrPwNotMatch}">
				<span>아이디와 암호가 일치하지 않습니다.</span>
				<br />
				<br />
			</c:if>

			<p>
				<br /> <input type="text" name="client_Id" placeholder="아이디 입력"
					value="${param.client_Id}">
				<c:if test="${errors.client_Id}">
					<span>ID를 입력하세요.</span>
				</c:if>

				<br /> <input type="password" name="password"
					placeholder="비밀번호 입력">
				<c:if test="${errors.password}">
					<span>암호를 입력하세요.</span>
				</c:if>
			</p>

			<p>
				<input type="submit" value="로그인하기">
			</p>

			<div class="login-links">
				<a href="/kanri/clientJoin.do">회원가입</a>
			</div>
		</form>
	</div>
</body>
</html>
