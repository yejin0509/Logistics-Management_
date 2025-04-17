<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Frame and Box</title>
<style>
body {
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh; /* 화면 높이를 채우는 설정 */
	background-color: #eaeaea; /* 배경색 */
}

.container {
	position: relative; /* 자식 요소들의 위치 조정을 위해 상대 위치 */
	width: 50%; /* frame의 너비 설정 */
}

.frame {
	width: 100%; /* container에 맞춰 중앙 정렬 */
	display: block;
}

.box1 {
	position: absolute; /* frame 위에 자유롭게 배치 */
	width: 53%; /* box 크기 설정 */
	top: 14.2%; /* frame의 위쪽에서 20% 아래로 이동 */
	left: 7%; /* frame의 왼쪽에서 40% 오른쪽으로 이동 */
}

.box2 {
	position: absolute; /* frame 위에 자유롭게 배치 */
	width: 53%; /* box 크기 설정 */
	top: 14.2%; /* frame의 위쪽에서 20% 아래로 이동 */
	right: 7%; /* frame의 왼쪽에서 40% 오른쪽으로 이동 */
}

.box3 {
	position: absolute; /* frame 위에 자유롭게 배치 */
	width: 53%; /* box 크기 설정 */
	top: 41.7%; /* frame의 위쪽에서 20% 아래로 이동 */
	left: 7%; /* frame의 왼쪽에서 40% 오른쪽으로 이동 */
}

.box4 {
	position: absolute; /* frame 위에 자유롭게 배치 */
	width: 53%; /* box 크기 설정 */
	top: 41.7%; /* frame의 위쪽에서 20% 아래로 이동 */
	right: 7%; /* frame의 왼쪽에서 40% 오른쪽으로 이동 */
}

.images {
position : relative;
width: 53%;
height: auto;
 }

</style>
</head>
<body>
	<div class="container">
		<img src="./images/frame.png" alt="frame" class="frame">
		<form action="nothingInclude.do">
			<img src="./images/inventory_list_box.png" alt="box1" class="box1">
				<input type="submit" style="opacity: 0; width: 100%; height: 100%; position: absolute; top: 0; left: 0; cursor: pointer;"/>
			</form>
		<a href="manager_order.jsp">
			<img src="./images/order_box.png" alt="box1" class="box2">
		</a>
		<a href="tester.jsp">
			<img src="./images/inbound_history_box.png" alt="box1" class="box3">
		</a>
		<a href="tester.jsp">
			<img src="./images/outbound_history_box.png" alt="box1" class="box4">
		</a>
	</div>
</body>
</html>