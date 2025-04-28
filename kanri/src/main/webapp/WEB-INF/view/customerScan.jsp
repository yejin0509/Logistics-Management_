<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入庫履歴</title>
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
	<h2>入庫履歴</h2>
	<div style="display: flex; gap: 20px;">
				<form action="/kanri/date-filter.do" method="get">
				<label for="in_Date">注文履歴照会:</label> <input type="date"
					id="in_Date" name="in_Date" value="${param.in_Date}">
				<button type="submit">日付照会</button>
			</form>
		<form action="/kanri/list.do" method="get">
		</form>

	</div>
	<table>
		<tr>
			<th>No.</th>
			<th>管理者 ID</th>
			<th>商品 ID</th>
			<th>注文日</th>
			<th>合計金額</th>
			<th>合計数量</th>
		</tr>

		<c:forEach var="InBound" items="${InBoundPage.content}">
			<tr>
				<td></td>
				<td>${InBound.manager_Id }</td>
				<td>${InBound.product_Id }</td>
				<td><fmt:formatDate value="${InBound.in_Date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${InBound.in_Count }</td>
				<td>${InBound.price }</td>
			</tr>
		</c:forEach>
		
								
</table>
		<form style="text-align: right; margin-top: 10px">
    <button type="button" onclick="history.back();" 
        style="width: 150px; height: 50px; font-size: 18px; padding: 10px;">
        戻る
    </button>
</form>

</body>
</html>