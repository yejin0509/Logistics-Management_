<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録完了</title>
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

.complete-box {
	background: #ffffff;
	padding: 30px 40px;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
	width: 350px;
	text-align: center;
}

.complete-box h2 {
	margin-bottom: 20px;
}

.complete-box p {
	margin-bottom: 30px;
}

.complete-box button {
	width: 100%;
	padding: 10px;
	background-color: black;
	color: #ffffff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-weight: bold;
}

.complete-box button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="complete-box">
		<h2>会員登録完了</h2>
		<p>
			ようこそ！ <br> <br>ログインしてサービスをご利用いただけます。
		</p>
		<form action="/kanri/clientLogin.do" method="get">
			<button type="submit">ログイン</button>
		</form>
	</div>
</body>
</html>
