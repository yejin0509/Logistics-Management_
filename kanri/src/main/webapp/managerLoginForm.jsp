<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ログイン</title>
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
        <h2>管理者ログイン</h2>
        <form action="/kanri/managerLogin.do" method="post">
            <c:if test="${errors.idOrPwNotMatch}">
                <span> IDまたはパスワードが一致しません</span>
                <br/><br/>
            </c:if>
	<p>
            <br/><input type="text" name="manager_Id" placeholder="ID" value="${param.manager_Id}">
            <c:if test="${errors.manager_Id}">
                <span>IDを入力してください</span>
            </c:if>

            <br/><input type="password" name="password" placeholder="パスワード">
            <c:if test="${errors.password}">
                <span>パスワードを入力してください</span>
            </c:if>
</p>
<p>
            <input type="submit" value="ログイン">
</p>
            <div class="login-links">
                <a href="/kanri/managerJoin.do">会員登録</a>
            </div>
        </form>
    </div>
</body>
</html>
