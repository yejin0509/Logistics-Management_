<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%
	// 캐시 방지 설정
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	List fullList = (List) request.getAttribute("inventory_Data");
	int pageSize = 10;
	int currentPage = 1;

	String pageParam = request.getParameter("page");
	if (pageParam != null) {
		try {
			currentPage = Integer.parseInt(pageParam);
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
	}

	int totalPages = 0;

	if (fullList != null && !fullList.isEmpty()) {
		int totalProductCount = fullList.size();
		totalPages = (int) Math.ceil((double) totalProductCount / pageSize);

		int start = (currentPage - 1) * pageSize;
		int end = Math.min(start + pageSize, totalProductCount);
		if (start < end) {
			List pageList = fullList.subList(start, end);
			pageContext.setAttribute("pagedList", pageList);
		}
	}
%>

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

	<!-- 검색 필터 -->
	<form action="/kanri/searchById.do" method="post">
		<input type="text" id="product_Id" name="product_Id" placeholder="Search by Product Id" />
		<input type="submit" value="검색하기">
	</form>

	<form action="/kanri/searchByName.do" method="post">
		<a>상품 이름 : </a>
		<input type="text" id="product_Name" name="product_Name" placeholder="Search By Product Name">
		<input type="submit" value="검색하기">
	</form>

	<form action="/kanri/nothingInclude.do" method="post">
		<input type="submit" value="검색 초기화" />
	</form>

	<form style="display: flex;" action="/kanri/searchByElse.do" method="post">
		<div>
			<a>상품 분류 : </a>
			<select id="product_Type" name="product_Type">
				<option value="all">--상품 분류 선택--</option>
				<c:forEach var="type" items="${product_Type}">
					<option value="${type}">${type}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<a>제조사 : </a>
			<select id="company" name="company">
				<option value="all">--제조사 선택--</option>
				<c:forEach var="company" items="${company}">
					<option value="${company}">${company}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<a>저장 위치 : </a>
			<select id="location" name="location">
				<option value="all">--저장 위치 선택--</option>
				<c:forEach var="location" items="${location}">
					<option value="${location}">${location}</option>
				</c:forEach>
			</select>
		</div>

		<input type="submit" id="search2" name="search" value="검색" />
	</form>

	<!-- 검색 결과 출력 -->
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

		<c:if test="${not empty pagedList}">
			<c:forEach var="item" items="${pagedList}">
				<tr>
					<td>${item.product_Id}</td>
					<td>${item.product_Type}</td>
					<td>${item.product_Name}</td>
					<td>${item.company}</td>
					<td>${item.price}</td>
					<td>${item.location}</td>
					<td>${item.stock}</td>
					<td>${item.content}</td>
				</tr>
			</c:forEach>
		</c:if>

		<c:if test="${empty pagedList}">
			<tr>
				<td colspan="8">검색 결과가 없습니다.</td>
			</tr>
		</c:if>
	</table>

	<!-- 페이지네이션 -->
	<div style="margin-top: 20px; text-align: center;">
		<c:if test="${not empty pagedList}">
			<%
				for (int i = 1; i <= totalPages; i++) {
					if (i == currentPage) {
			%>
						<strong>[<%= i %>]</strong>
			<%
					} else {
			%>
						<a href="?page=<%= i %>">[<%= i %>]</a>
			<%
					}
					out.print("&nbsp;");
				}
			%>
		</c:if>
	</div>

</body>
</html>
