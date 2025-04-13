<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 전체 재고 보기 및 필터로 검색하기 -->
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

<!-- 상품 id로 검색하기 -->
<form action="${pageContext.request.contextPath}/searchById.do" method="post">
    <input type="text" id="product_Id" name="product_Id" placeholder="Search by Product Name" />
    <input type="submit" value="검색하기">
</form>

<!-- 검색 필터 초기화 -->
<form action="nothingInclude.do" method="post">
    <input type="submit" value="검색 초기화" />
</form>

<!-- 기타 검색 옵션 -->
<form style="display: flex;" action="${pageContext.request.contextPath}/searchByElse.do" method="post">

    <div>
        <a>분류 : </a>
        <select id="product_Type" name="product_Type">
            <option value="all">--상품 분류 선택--</option>
            <c:forEach var="type" items="${product_Type}">
                <option value="${type}" <c:if test="${param.product_Type == type}">selected</c:if>>${type}</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <a>상품명 : </a>
        <input type="text" id="product_Name" name="product_Name" />
    </div>

    <div>
        <a>분류 : </a>
        <select id="company" name="company">
            <option value="all">--제조사 선택--</option>
            <c:forEach var="company" items="${company}">
                <option value="${company}">${company}</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <a>분류 : </a>
        <select id="location" name="location">
            <option value="all">--저장 위치 선택--</option>
            <c:forEach var="location" items="${location}">
                <option value="${location}">${location}</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <a>금액:</a>
        <input type="range" id="max_Amount" name="max_Amount" min="0" max="100000" step="1000" value="100000" oninput="updateValue(this.value)">
        <output id="output">10000</output> 원
    </div>

    <input type="submit" id="search2" name="search" value="검색" />
</form>

<script>
    function updateValue(value) {
        document.getElementById('output').textContent = value;
    }
</script>

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

    <!-- 검색 결과가 있는 경우 -->
    <c:if test="${not empty inventory_Data}">
        <c:forEach var="item" items="${inventory_Data}">
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

    <!-- 검색 결과가 없는 경우 -->
    <c:if test="${empty inventory_Data}">
        <tr>
            <td colspan="8">검색 결과가 없습니다.</td>
        </tr>
    </c:if>
</table>

</body>
</html>