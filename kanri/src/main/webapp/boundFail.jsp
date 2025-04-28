<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文失敗</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
	
	<!-- 일본어 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">
<style>
<!-- 스타일 지정 -->
* {
 font-family: "Kosugi Maru", sans-serif;
  font-weight: 400;
  font-style: normal;
}
.card-header {
	background-color: transparent !important;
	border-bottom: none !important;
	box-shadow: none !important;
	padding: 50px 0;
}

.card-body {
	flex: 1 1 auto;
	padding: 30px 0;
	color: var(--bs-card-color);
}

thead th {
	border-bottom: 2px solid #000;
}

body {
	background-color: #f8f9fa;
}

header {
	background-color: #fff;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	padding: 20px 0;
}

footer {
	border-top: 1px solid #ddd;
	margin-top: 50px;
	padding: 20px 0;
	text-align: center;
	color: #777;
	font-size: 14px;
}

.total-price {
	font-size: 1.25rem;
	font-weight: bold;
	text-align: right;
}
h1,h3{
font-weight:bold;
}
* {
 font-family: "Kosugi Maru", sans-serif;
  font-weight: 400;
  font-style: normal;
}
</style>
</head>
<body>

	<!-- Header -->
	<header>
		<div class="container">
			<h1 class="text-center text-dark">失敗</h1>
		</div>
	</header>

	<!-- Main -->
	<main class="container my-4">
		<div class="card">
			<div class="card-header text-center">
				<h3 class="mb-0">注文に失敗しました</h3>
			</div>
			<div class="card-body">
				<div class="text-center">
					<button onclick="history.back()" class="btn btn-dark">戻る</button>
				</div>
			</div>
		</div>
	</main>

	<!-- Footer -->
	<footer>
		<div class="container">
			&copy;
			<%=java.time.Year.now()%>
			物流管理
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>