<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 상세 페이지</title>
</head>
<body>

	<table>
<!-- 필터 달아줘야 함 -->	
	<div>
		<!--상품 id로 검색하기  -->
		<input type="text" id="" name=""/>
		<!-- query where id=? 기준으로 검색 -->
		<submit action=""/>
		<br>
		<select>
			<option>분류1</option>
			<option>분류2</option>
		</select>
		
		<select></select>
		<select></select>
		<select></select>
		
		<!-- 돋보기 이미지 부여 후 눌러서 직접 찾아볼 수 있음 -->
		<a class="" href=""></a>
	</div>

		<tr>
			<!--총 8개 -->
			<td>상품 id</td>
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
		<c:forEach var="inventoryList" items="${'어디'에 있는 '무엇'}">
			<!-- 반복적으로 정보 불러오기 -->
			<tr>
				<td>${product.product_id}</td>
				<td>${product.product_type}</td>
				<td>${product.product_name}</td>
				<td>${product.company}</td>
				<td>${product.price}</td>
				<td>${product.content}</td>
				<td>${inventory.location}</td>
				<td>${inventory.stock}</td>
			</tr>
		</c:forEach>

		<!--"5"개씩 끊었을 때 밑에 몇 페이지 있는지 표시  -->
		<c:if test="${articlePage.hasArticles() }">
			<tr>
				<td colspan="4"><c:if test="${articlePage.startPage>5 }">
						<a href="list.do?pageNo=${articlePage.startPage -5 }">[이전]</a>
					</c:if> <c:forEach var="pNo" begin="${articlePage.startPage }"
						end="${articlePage.endPage }">
						<a href="list.do?pagNo=${pNo }">[${pNo}]</a>
					</c:forEach> <c:if test="${articlePage.endPage < articlePage.totalPages}">
						<a href="list.do?pageNo=${articlePage.startPage +5 }">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>

	</table>

</body>
</html>