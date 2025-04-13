<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tester for Everything</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

tr:hover {
	background-color: #ddd;
}

div {
	margin-bottom: 15px;
}
</style>
</head>
<body>
	<div>
		<input type="text" name="search_product_id"
			placeholder="Enter Product ID" /> <input type="submit" name="search"
			value="Search by ID" />
	</div>
	<div>
		<a>분류 : </a> <select>
			<option>All</option>
			<option>Product Type 1</option>
			<option>Product Type 2</option>
			<option>Product Type 3</option>
		</select> <a>상품명 : </a> <input type="text" placeholder="Search by Product Name" />

		<a>제조사 : </a> <select>
			<option>All</option>
			<option>Company 1</option>
			<option>Company 2</option>
			<option>Company 3</option>
		</select> <a>저장 위치 : </a> <select>
			<option>All</option>
			<option>Location 1</option>
			<option>Location 2</option>
			<option>Location 3</option>
		</select> <a>금액:</a> <input type="range" name="search_amount" min="0"
			max="1000000" step="10000" value="0"
			oninput="updateValue(this.value)">
		<output id="output">10000</output>
		원

	</div>
	<script>
		function updateValue(value) {
			document.getElementById('output').textContent = value;
		}
	</script>




	<div align="right">
		<input type="submit" name="search" value="Search" />
	</div>
	<table>
		<tr>
			<td>상품 ID</td>
			<td>분류</td>
			<td>상품명</td>
			<td>제조사</td>
			<td>상품가격</td>
			<td>저장 위치</td>
			<td>재고 수량</td>
			<td>설명</td>
		</tr>
		<tr>
			<td>001</td>
			<td>Electronics</td>
			<td>Smartphone</td>
			<td>Samsung</td>
			<td>₩999,000</td>
			<td>Warehouse A3</td>
			<td>120</td>
			<td>Latest model, supports fast charging</td>
		</tr>
		<tr>
			<td>002</td>
			<td>Household Goods</td>
			<td>Electric Toothbrush</td>
			<td>Philips</td>
			<td>₩59,900</td>
			<td>Warehouse B1</td>
			<td>80</td>
			<td>Efficient dental care</td>
		</tr>
		<tr>
			<td>003</td>
			<td>Kitchenware</td>
			<td>Blender</td>
			<td>LG</td>
			<td>₩78,000</td>
			<td>Warehouse C2</td>
			<td>45</td>
			<td>Multi-function, powerful motor</td>
		</tr>
		<tr>
			<td>004</td>
			<td>Clothing</td>
			<td>Winter Coat</td>
			<td>Nike</td>
			<td>₩120,000</td>
			<td>Warehouse D5</td>
			<td>30</td>
			<td>Insulated, lightweight material</td>
		</tr>
		<tr>
			<td>005</td>
			<td>Books</td>
			<td>Programming Basics</td>
			<td>Hanbit Media</td>
			<td>₩25,000</td>
			<td>Warehouse E4</td>
			<td>150</td>
			<td>A guide for beginners</td>
		</tr>
	</table>
</body>
</html>