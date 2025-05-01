<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>

<!-- 캐시 방지 설정 -->
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 방지 헤더 설정
response.setHeader("Pragma", "no-cache"); // 프래그마 헤더 설정
response.setDateHeader("Expires", 0); // 만료일 설정

// 재고 데이터를 페이지네이션 처리하기 위한 변수 설정
List fullList = (List) request.getAttribute("inventory_Data"); // 재고 데이터 리스트
int pageSize = 10; // 한 페이지에 표시할 항목 수
int currentPage = 1; // 현재 페이지 번호

String pageParam = request.getParameter("page"); // 페이지 파라미터 확인
if (pageParam != null) {
    try {
        currentPage = Integer.parseInt(pageParam); // 페이지 번호 설정
    } catch (NumberFormatException e) {
        currentPage = 1; // 페이지 파라미터가 잘못된 경우 1 페이지로 설정
    }
}

int totalPages = 0; // 전체 페이지 수

// 전체 리스트가 비어있지 않다면 페이지네이션을 적용
if (fullList != null && !fullList.isEmpty()) {
    int totalProductCount = fullList.size(); // 전체 제품 수
    totalPages = (int) Math.ceil((double) totalProductCount / pageSize); // 전체 페이지 수 계산

    int start = (currentPage - 1) * pageSize; // 페이지 시작 인덱스
    int end = Math.min(start + pageSize, totalProductCount); // 페이지 끝 인덱스
    if (start < end) {
        List pageList = fullList.subList(start, end); // 현재 페이지에 맞는 리스트 추출
        pageContext.setAttribute("pagedList", pageList); // 페이지 리스트를 페이지 컨텍스트에 저장
    }
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫詳細ページ</title> <!-- 페이지 제목: 재고 상세 페이지 -->

<!-- Bootstrap CSS 불러오기 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Google Fonts 불러오기 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

<style>
* {
    font-family: "Kosugi Maru", sans-serif; /* 폰트 설정 */
    font-weight: 400;
    font-style: normal;
}

header {
    background-color: #fff; /* 헤더 배경색 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 헤더 그림자 */
    padding: 20px 0; /* 헤더 패딩 */
}

footer {
    border-top: 1px solid #ddd; /* 푸터 위 경계선 */
    margin-top: 50px; /* 푸터와 본문 간 여백 */
    padding: 20px 0; /* 푸터 패딩 */
    text-align: center; /* 푸터 텍스트 중앙 정렬 */
    color: #777; /* 푸터 텍스트 색상 */
    font-size: 14px; /* 푸터 텍스트 크기 */
}

h1, h3 {
    font-weight: bold; /* 제목 글자 굵기 */
}

.logo {
    position: fixed; /* 로고 고정 위치 */
    max-width: 6.2%; /* 로고 너비 */
    height: auto; /* 로고 비율 유지 */
    z-index: 90; /* 다른 요소들 위에 표시 */
}

table {
    width: 100%; /* 테이블 너비 100% */
    border-collapse: collapse; /* 테이블 셀 경계 합침 */
}

thead th {
    border-bottom: 2px solid #000; /* 테이블 헤더 밑 경계선 */
    background-color: #f2f2f2; /* 테이블 헤더 배경색 */
}

tr:hover {
    background-color: #f9f9f9; /* 테이블 행에 마우스를 올리면 배경색 변경 */
}

td, th {
    text-align: center; /* 셀 내 텍스트 중앙 정렬 */
    padding: 10px; /* 셀 패딩 */
    border: 1px solid #ccc; /* 셀 경계선 */
}

.mb-4 {
    margin-bottom: 2rem; /* 하단 여백 */
}

.text-end {
    text-align: right; /* 오른쪽 정렬 */
}

.search-form {
    display: flex;
    align-items: center;
    gap: 15px;
    flex-wrap: wrap;
    margin-bottom: 20px; /* 검색 폼 여백 */
}

.search-form form {
    display: flex;
    align-items: center;
    gap: 5px;
}

.search-form form input[type="text"], .search-form form select {
    width: auto;
    max-width: 200px;
}

.search-form form input[type="submit"] {
    background-color: black;
    color: white;
}

.pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px; /* 페이지네이션 여백 */
}

.pagination a, .pagination strong {
    padding: 5px 10px; /* 페이지네이션 버튼 패딩 */
    margin: 0 5px; /* 페이지네이션 버튼 간격 */
    text-decoration: none;
    color: black;
}

body {
    background-color: #f8f9fa; /* 배경색 설정 */
}
</style>

</head>
<body>

<!-- 로고 -->
<a class="logo" href="managerMenu.jsp">
    <img alt="logo" src="./images/logo.png?ver=2" class="logo"> <!-- 로고 이미지 -->
</a>

<!-- 헤더 -->
<header>
    <div class="container">
        <h1 class="text-center text-dark">在庫確認</h1> <!-- 재고 확인 제목 -->
    </div>
</header>

<!-- 본문 내용 -->
<main class="container my-4">
    <div class="search-form">
        <!-- 상품 ID 검색 폼 -->
        <form action="/kanri/searchById.do" method="post">
            <label for="product_Id">ID:</label> <!-- 상품 ID -->
            <input type="text" id="product_Id" name="product_Id" placeholder="製品ID" /> <!-- 상품 ID 입력 필드 -->
            <input type="submit" value="検索" /> <!-- 검색 버튼 -->
        </form>

        <!-- 상품 이름 검색 폼 -->
        <form action="/kanri/searchByName.do" method="post">
            <label for="product_Name">製品名前:</label> <!-- 상품 이름 -->
            <input type="text" id="product_Name" name="product_Name" placeholder="製品名前" /> <!-- 상품 이름 입력 필드 -->
            <input type="submit" value="検索" /> <!-- 검색 버튼 -->
        </form>

        <!-- 저장 위치 검색 폼 -->
        <form action="/kanri/searchByElse.do" method="post">
            <label for="location">位置:</label> <!-- 위치 검색 -->
            <select id="location" name="location" class="form-select select-no-border"> <!-- 위치 선택 -->
                <option value="all">--選択--</option> <!-- 선택 옵션 -->
                <c:forEach var="location" items="${location}"> <!-- 저장 위치 목록을 반복 출력 -->
                    <option value="${location}">${location}</option> <!-- 위치 값 출력 -->
                </c:forEach>
            </select>
            <input type="submit" value="検索" /> <!-- 검색 버튼 -->
        </form>

        <!-- 초기화 버튼 -->
        <form action="/kanri/nothingInclude.do" method="post" style="margin-left: auto; margin-right: 3%;"> <!-- 검색 리셋 폼 -->
            <input type="submit" class="btn" style="background-color: black; color: white;" value="検索のリセット" /> <!-- 리셋 버튼 -->
        </form>
    </div>

    <!-- 검색 결과 출력 -->
    <table class="table">
        <thead>
            <tr class="text-center">
                <td>ID</td> <!-- ID -->
                <td>分類</td> <!-- 분류 -->
                <td>商品名</td> <!-- 상품명 -->
                <td>製造元</td> <!-- 제조사 -->
                <td>価格</td> <!-- 가격 -->
                <td>位置</td> <!-- 위치 -->
                <td>数量</td> <!-- 수량 -->
                <td>説明</td> <!-- 설명 -->
            </tr>
        </thead>
        <tbody id="orderBody" class="text-center">
            <c:if test="${not empty pagedList}"> <!-- 페이지에 데이터가 있을 경우 -->
                <c:forEach var="item" items="${pagedList}"> <!-- 페이지 리스트에서 데이터 출력 -->
                    <tr>
                        <td>${item.product_Id}</td> <!-- 상품 ID -->
                        <td>${item.product_Type}</td> <!-- 상품 유형 -->
                        <td>${item.product_Name}</td> <!-- 상품 이름 -->
                        <td>${item.company}</td> <!-- 제조사 -->
                        <td>${item.price}</td> <!-- 가격 -->
                        <td>${item.location}</td> <!-- 위치 -->
                        <td>${item.stock}</td> <!-- 재고 수량 -->
                        <td>${item.content}</td> <!-- 설명 -->
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${empty pagedList}"> <!-- 페이지에 데이터가 없을 경우 -->
                <tr>
                    <td colspan="8">検索結果がありません</td> <!-- 검색 결과가 없다는 메시지 -->
                </tr>
            </c:if>
        </tbody>
    </table>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <c:if test="${not empty pagedList}"> <!-- 페이지에 데이터가 있을 경우 -->
            <%
            for (int i = 1; i <= totalPages; i++) { // 페이지 번호 반복 출력
                if (i == currentPage) { // 현재 페이지
            %>
            <strong>[<%=i%>]</strong> <!-- 현재 페이지는 강조 표시 -->
            <%
                } else {
            %>
            <a href="?page=<%=i%>">[<%=i%>]</a> <!-- 페이지 번호 링크 -->
            <%
                }
                out.print("&nbsp;"); // 페이지 번호 사이에 공백 추가
            }
            %>
        </c:if>
    </div>
</main>

<!-- 푸터 -->
<footer>
    <div class="container">
        &copy; <%=java.time.Year.now()%> 物流管理 <!-- 저작권 표시 -->
    </div>
</footer>

</body>
</html>
