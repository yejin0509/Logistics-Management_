<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tester for Everything</title> <!-- 페이지 제목: 모든 것을 테스트하는 페이지 -->

<!-- 스타일 정의 -->
<style>
table {
	width: 100%; /* 테이블 너비 100%로 설정 */
	border-collapse: collapse; /* 테이블 셀 경계 합침 */
}

th, td {
	border: 1px solid #ddd; /* 셀 경계 스타일 설정 */
	padding: 8px; /* 셀 내 여백 */
	text-align: center; /* 텍스트 중앙 정렬 */
}

tr:hover {
	background-color: #ddd; /* 마우스를 올리면 배경색 변경 */
}

div {
	margin-bottom: 15px; /* div 하단에 여백 추가 */
}
</style>
</head>
<body>
	<!-- 상품 ID로 검색하는 입력 필드 -->
	<div>
		<input type="text" name="search_product_id" placeholder="Enter Product ID" /> <!-- 상품 ID 입력 필드 -->
		<input type="submit" name="search" value="Search by ID" /> <!-- 상품 ID로 검색 버튼 -->
	</div>
	<div>
		<!-- 상품 분류 선택 -->
		<a>분류 : </a> <select> <!-- 상품 분류 선택 드롭다운 -->
			<option>All</option> <!-- 모든 상품 -->
			<option>Product Type 1</option> <!-- 상품 타입 1 -->
			<option>Product Type 2</option> <!-- 상품 타입 2 -->
			<option>Product Type 3</option> <!-- 상품 타입 3 -->
		</select>
		<!-- 상품명 검색 -->
		<a>상품명 : </a> <input type="text" placeholder="Search by Product Name" /> <!-- 상품명 입력 필드 -->

		<!-- 제조사 선택 -->
		<a>제조사 : </a> <select> <!-- 제조사 선택 드롭다운 -->
			<option>All</option> <!-- 모든 제조사 -->
			<option>Company 1</option> <!-- 회사 1 -->
			<option>Company 2</option> <!-- 회사 2 -->
			<option>Company 3</option> <!-- 회사 3 -->
		</select>
		<!-- 저장 위치 선택 -->
		<a>저장 위치 : </a> <select> <!-- 저장 위치 선택 드롭다운 -->
			<option>All</option> <!-- 모든 위치 -->
			<option>Location 1</option> <!-- 위치 1 -->
			<option>Location 2</option> <!-- 위치 2 -->
			<option>Location 3</option> <!-- 위치 3 -->
		</select>
		<!-- 금액 범위 설정 -->
		<a>금액:</a> <input type="range" name="search_amount" min="0" max="1000000" step="10000" value="0" oninput="updateValue(this.value)"> <!-- 금액 범위 슬라이더 -->
		<output id="output">10000</output> 원 <!-- 현재 선택된 금액 출력 -->

	</div>
	<script>
		// 슬라이더 값 업데이트 함수
		function updateValue(value) {
			document.getElementById('output').textContent = value; // 선택된 값을 output 요소에 표시
		}
	</script>

	<!-- 검색 버튼 -->
	<div align="right">
		<input type="submit" name="search" value="Search" /> <!-- 검색 버튼 -->
	</div>

	<!-- 상품 목록을 나타내는 테이블 -->
	<table>
		<tr>
			<td>상품 ID</td> <!-- 상품 ID -->
			<td>분류</td> <!-- 상품 분류 -->
			<td>상품명</td> <!-- 상품명 -->
			<td>제조사</td> <!-- 제조사 -->
			<td>상품가격</td> <!-- 상품 가격 -->
			<td>저장 위치</td> <!-- 저장 위치 -->
			<td>재고 수량</td> <!-- 재고 수량 -->
			<td>설명</td> <!-- 상품 설명 -->
		</tr>
		<tr>
			<td>001</td>
			<td>Electronics</td> <!-- 전자제품 -->
			<td>Smartphone</td> <!-- 스마트폰 -->
			<td>Samsung</td> <!-- 삼성 -->
			<td>₩999,000</td> <!-- 가격 -->
			<td>Warehouse A3</td> <!-- 저장 위치 -->
			<td>120</td> <!-- 재고 수량 -->
			<td>Latest model, supports fast charging</td> <!-- 최신 모델, 고속 충전 지원 -->
		</tr>
		<tr>
			<td>002</td>
			<td>Household Goods</td> <!-- 가전 제품 -->
			<td>Electric Toothbrush</td> <!-- 전기 칫솔 -->
			<td>Philips</td> <!-- 필립스 -->
			<td>₩59,900</td> <!-- 가격 -->
			<td>Warehouse B1</td> <!-- 저장 위치 -->
			<td>80</td> <!-- 재고 수량 -->
			<td>Efficient dental care</td> <!-- 효율적인 구강 관리 -->
		</tr>
		<tr>
			<td>003</td>
			<td>Kitchenware</td> <!-- 주방용품 -->
			<td>Blender</td> <!-- 블렌더 -->
			<td>LG</td> <!-- LG -->
			<td>₩78,000</td> <!-- 가격 -->
			<td>Warehouse C2</td> <!-- 저장 위치 -->
			<td>45</td> <!-- 재고 수량 -->
			<td>Multi-function, powerful motor</td> <!-- 다기능, 강력한 모터 -->
		</tr>
		<tr>
			<td>004</td>
			<td>Clothing</td> <!-- 의류 -->
			<td>Winter Coat</td> <!-- 겨울 코트 -->
			<td>Nike</td> <!-- 나이키 -->
			<td>₩120,000</td> <!-- 가격 -->
			<td>Warehouse D5</td> <!-- 저장 위치 -->
			<td>30</td> <!-- 재고 수량 -->
			<td>Insulated, lightweight material</td> <!-- 단열 처리, 가벼운 소재 -->
		</tr>
		<tr>
			<td>005</td>
			<td>Books</td> <!-- 도서 -->
			<td>Programming Basics</td> <!-- 프로그래밍 기초 -->
			<td>Hanbit Media</td> <!-- 한빛 미디어 -->
			<td>₩25,000</td> <!-- 가격 -->
			<td>Warehouse E4</td> <!-- 저장 위치 -->
			<td>150</td> <!-- 재고 수량 -->
			<td>A guide for beginners</td> <!-- 초보자를 위한 가이드 -->
		</tr>
	</table>
</body>
</html>
