<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者会員登録</title>
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
		<h2>管理者会員登録</h2>
		<form action="/kanri/managerJoin.do" method="post">
			<!-- 관리자 ID -->
			
			<input type="text" name="manager_Id" placeholder="ID" required>
			<c:if test="${errors.manager_Id}">
				<span>IDを入力してください</span>
			</c:if>
			<c:if test="${errors.duplicateId}">
				<span>すでに登録されているIDです</span>
			</c:if>

			<!-- 비밀번호 -->
			<input type="password" name="password" placeholder="パスワード" required>
			<c:if test="${errors.password}">
				<span>パスワードを入力してください</span>
			</c:if>

			<!-- 비밀번호 확인 -->
			<input type="password" name="confirmPassword" placeholder="パスワード確認" required>
			<c:if test="${errors.confirmPassword}">
				<span>パスワード確認</span>
			</c:if>
			<c:if test="${errors.notMatch}">
				<span>パスワードが一致しません</span>
			</c:if>
			
			
			
		
			<!-- 관리자 등록 코드 -->
			<input type="text" name="registerCode" placeholder="管理者登録コード入力" required>
			<c:if test="${errors.registerCode}">
				<span>登録コードが一致しません</span><br>
			</c:if>
			<div>
			<br>
			<input type="submit" value="管理者登録">
			</div>
		</form>
	</div>
</body>
</html>
