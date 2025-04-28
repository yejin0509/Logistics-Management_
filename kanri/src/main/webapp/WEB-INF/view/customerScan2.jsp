<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出庫履歴</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<h2>出庫履歴</h2>
	<div style="display: flex; gap: 20px;">
				<form action="/kanri/date-filter2.do" method="get">
				<label for="out_Date">注文履歴照会:</label> <input type="date"
					id="out_Date" name="out_Date" value="${param.out_Date}">
				<button type="submit">日付照会</button>
			</form>
		<form action="/kanri/list2.do" method="get">
		</form>

	</div>
	<table>
		<tr>
			<th>No.</th>
			<th>注文者 ID</th>
			<th>商品 ID</th>
			<th>注文日</th>
			<th>合計金額</th>
			<th>合計数量</th>
		</tr>

		<c:forEach var="OutBound" items="${OutBoundPage.content}">
			<tr>
				<td></td>
				<td>${OutBound.client_Id }</td>
				<td>${OutBound.product_Id }</td>
				<td><fmt:formatDate value="${OutBound.out_Date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${OutBound.out_Count }</td>
				<td>${OutBound.price }</td>
			</tr>
		</c:forEach>
	</table>

	<form style="text-align: right; margin-top: 10px">
		<button type="button" onclick="history.back();"
			style="width: 150px; height: 50px; font-size: 18px; padding: 10px;">
			戻る</button>
	</form>

</body>
</html>