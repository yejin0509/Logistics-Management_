<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원가입</title>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #eaeaea;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.register-box {
	background: #ffffff;
	padding: 30px 40px;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
	width: 350px;
}

.register-box input[type="text"], .register-box input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
}

.register-box input[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: black;
	color: #ffffff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-weight: bold;
}

.register-box input[type="submit"]:hover {
	background-color: #0056b3;
}

.register-box span {
	color: red;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<div class="register-box">
		<h2>관리자 회원가입</h2>
		<form action="/kanri/managerJoin.do" method="post">
			<!-- 관리자 ID -->
			
			<input type="text" name="manager_Id" placeholder="아이디 입력" required>
			<c:if test="${errors.manager_Id}">
				<span>아이디를 입력하세요.</span>
			</c:if>
			<c:if test="${errors.duplicateId}">
				<span>이미 등록된 아이디입니다.</span>
			</c:if>

			<!-- 비밀번호 -->
			<input type="password" name="password" placeholder="비밀번호 입력" required>
			<c:if test="${errors.password}">
				<span>비밀번호를 입력하세요.</span>
			</c:if>

			<!-- 비밀번호 확인 -->
			<input type="password" name="confirmPassword" placeholder="비밀번호 확인" required>
			<c:if test="${errors.confirmPassword}">
				<span>비밀번호 확인을 입력하세요.</span>
			</c:if>
			<c:if test="${errors.notMatch}">
				<span>비밀번호가 일치하지 않습니다.</span>
			</c:if>
			
			
			
		
			<!-- 관리자 등록 코드 -->
			<input type="text" name="registerCode" placeholder="관리자 등록코드 입력" required>
			<c:if test="${errors.registerCode}">
				<span>등록코드가 일치하지 않습니다.</span><br>
			</c:if>
			<div>
			<br>
			<input type="submit" value="관리자 가입">
			</div>
		</form>
	</div>
</body>
</html>
