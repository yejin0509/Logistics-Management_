<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録</title>
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

.register-box input[type="text"], .register-box input[type="password"],
	.register-box select {
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

.number-class {
	display: flex;
	justify-content: space-between;
	gap: 10px;
	align-items: center;
}

.number-class input[type="text"], .number-class select {
	width: 32%;
}
</style>
</head>
<body>
	<div class="register-box">
		<h2>会員登録</h2>
		<form action="/kanri/clientJoin.do" method="post">

			<p style="margin-top: 0px; margin-bottom: 0px;">
				<br> <input type="text" name="client_Id" placeholder="ID"
					required>
				<c:if test="${errors.client_Id}">
					<span>IDを入力してください</span>
				</c:if>
				<c:if test="${errors.duplicateId}">
					<span>すでに使われているIDです</span>
				</c:if>
				<br> <input type="password" name="password" placeholder="パスワード"
					required>
				<c:if test="${errors.password}">
					<span>パスワードを入力してください</span>
				</c:if>

				<br> <input type="password" name="confirmPassword"
					placeholder="パスワード確認" required>
				<c:if test="${errors.confirmPassword}">
					<span>パスワード確認</span>
				</c:if>
				<c:if test="${errors.notMatch}">
					<span>パスワードが一致しません</span>
				</c:if>
</p>

			<div class="number-class">
				<select name="c_Number1">
					<option value="">選択</option>
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
					<option value="018">018</option>
					<option value="019">019</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="032">032</option>
					<option value="051">051</option>
					<option value="053">053</option>
					<option value="062">062</option>
					<option value="042">042</option>
					<option value="052">052</option>
					<option value="055">055</option>
					<option value="064">064</option>
				</select> - <input type="text" name="c_Number2" maxlength="4" size="4"
					placeholder="最初の番号" required> - <input type="text"
					name="c_Number3" maxlength="4" size="4" placeholder="最後の番号" required>

				<c:if test="${errors.c_Number}">
					<br>
					<span>連絡先を入力してください</span>
				</c:if>
			</div>
			<input type="text" name="company" placeholder="会社名"
				required>
			<c:if test="${errors.company}">
				<span>会社名を入力してください</span>
			</c:if>
			<br> <input type="text" name="address" placeholder="会社住所"
				required>
			<c:if test="${errors.address}">
				<span>住所を入力してください</span>
			</c:if>
<p>
			<label><input type="checkbox" name="tosAgree" required>
				利用規約に同意(必須)</label><br> <label><input type="checkbox"
				name="privacyAgree" required> 個人情報の収集および利用同意 (必須)</label><br>
			<c:if test="${errors.tosAgree}">
				<span>利用規約への同意が必要です</span>
			</c:if>
			<c:if test="${errors.privacyAgree}">
				<span>個人情報への同意が必要です</span>
			</c:if>
</p>

			<p>
				<input type="submit" value="会員登録">
			</p>
		</form>
	</div>
</body>
</html>
