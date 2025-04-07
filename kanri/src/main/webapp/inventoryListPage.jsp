<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 상세 페이지</title>
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


	<!--검색 필터 -->


	<!--상품 id로 검색하기 -->
	<form action="SearchByIdHandler" method="post">

		<input type="text" id="search_product_id" name="search_product_id"
			placeholder="Search by Product Name" />
		<!-- query문 "where product_id=?" 기준으로 검색 -->
		<input type="submit" id="search1" name="search" value="ID 검색" />
	</form>


	<!-- 그 외의 것으로 검색하기 -->
	<form style="display: flex;" action="SearchByElseHandler" method="post">

		<!-- 분류 / product_type으로 검색 // 나중에 forEach로 있는 값 반복해서 뽑아내기 쓸 듯 -->
		<div>
			<a>분류 : </a> <select id="search_product_type"
				name="search_product_type">
				<option>All</option>
				<option>product_type1</option>
				<option>product_type2</option>
				<option>product_type3</option>
			</select>
		</div>


		<!-- product_name으로 검색 -->
		<div>
			<a>상품명 : </a> <input type="text" id="search_product_name"
				name="search_product_name" />
		</div>



		<!-- 제조사 검색 // 나중에 forEach로 있는 값 반복해서 뽑아내기 쓸 듯-->
		<div>
			<a>제조사 : </a> <select id="search_company" name="search_company">
				<option>All</option>
				<option>company1</option>
				<option>company2</option>
				<option>company3</option>
			</select>
		</div>


		<!-- 저장 위치 검색 // 나중에 forEach로 있는 값 반복해서 뽑아내기 쓸 듯 -->
		<div>
			<a>저장 위치 : </a> <select id="search_location" name="search_location">
				<option>All</option>
				<option>location1</option>
				<option>location2</option>
				<option>location3</option>
			</select>
		</div>
		<div>
			<a>금액:</a> <input type="range" id="search_amount"
				name="search_amount" min="0" max="1000000" step="10000" value="0"
				oninput="updateValue(this.value)">
			<output id="output">10000</output>
			원
		</div>
		<!-- 조건 모아서 검색하기, 앞에 값 모아서 검색 조건 where 절에 삽입 -->
		<input type="submit" id="search2" name="search" value="검색" />
	</form>
	<!-- 슬라이더 작동 스크립트 -->
	<script>
		function updateValue(value) {
			document.getElementById('output').textContent = value;
		}
	</script>


	<!-- 검색 하면 나오는 List -->
	<table>

		<!-- 필드명 -->
		<tr>
			<!--총 8개 -->
			<td>상품 ID</td>
			<td>분류</td>
			<td>상품명</td>
			<td>제조사</td>
			<td>상품가격</td>
			<td>저장 위치</td>
			<td>재고 수량</td>
			<td>설명</td>
		</tr>


		<!-- 안에 항목이 없는 경우 -->
		<c:if test="${articlePage.hasNoList() }">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${product != null}">
			<tr>
				<td>${product.product_id}</td>
				<td>${product.product_type}</td>
				<td>${product.product_name}</td>
				<td>${product.company}</td>
				<td>${product.price}</td>
				<td>${product.location}</td>
				<td>${product.stock}</td>
				<td>${product.description}</td>
			</tr>
		</c:if>


		<!--"5"개씩 끊었을 때 밑에 몇 페이지 있는지 표시  -->
		<c:if test="${articlePage.hasArticles() }">
			<tr>
				<td colspan="4"><c:if test="${articlePage.startPage>10 }">
						<a href="list.do?pageNo=${articlePage.startPage -10 }">[이전]</a>
					</c:if> <c:forEach var="pNo" begin="${articlePage.startPage }"
						end="${articlePage.endPage }">
						<a href="list.do?pagNo=${pNo }">[${pNo}]</a>
					</c:forEach> <c:if test="${articlePage.endPage < articlePage.totalPages}">
						<a href="list.do?pageNo=${articlePage.startPage +10 }">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>

	</table>

</body>
</html>