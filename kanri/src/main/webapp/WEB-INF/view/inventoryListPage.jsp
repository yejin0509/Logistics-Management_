<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
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
<title>在庫詳細ページ</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">
<style>
* {
    font-family: "Kosugi Maru", sans-serif;
    font-weight: 400;
    font-style: normal;
}

header {
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 20px 0;
}

footer {
    border-top: 1px solid #ddd;
    margin-top: 50px;
    padding: 20px 0;
    text-align: center;
    color: #777;
    font-size: 14px;
}

h1, h3 {
    font-weight: bold;
}

.logo {
    position: fixed;
    max-width: 6.2%;
    height: auto;
    z-index: 90;
}

table {
    width: 100%;
    border-collapse: collapse;
}

thead th {
    border-bottom: 2px solid #000;
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #f9f9f9;
}

td, th {
    text-align: center;
    padding: 10px;
    border: 1px solid #ccc;
}

.mb-4 {
    margin-bottom: 2rem;
}

.text-end {
    text-align: right;
}

.search-form {
    display: flex;
    align-items: center;
    gap: 15px;
    flex-wrap: wrap;
    margin-bottom: 20px;
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
    margin-top: 20px;
}

.pagination a, .pagination strong {
    padding: 5px 10px;
    margin: 0 5px;
    text-decoration: none;
    color: black;
    
}
body {
    background-color: #f8f9fa;
}


</style>
</head>
<body>
<!-- Logo -->
<a class="logo" href="managerMenu.jsp">
    <img alt="logo" src="./images/logo.png?ver=2" class="logo">
</a>

<!-- Header -->
<header>
    <div class="container">
        <h1 class="text-center text-dark">在庫確認</h1>
    </div>
</header>

<!-- Main Content -->
<main class="container my-4">
    <div class="search-form">
        <!-- 상품 ID 검색 -->
        <form action="/kanri/searchById.do" method="post">
            <label for="product_Id">ID:</label>
            <input type="text" id="product_Id" name="product_Id" placeholder="製品ID" />
            <input type="submit" value="検索" />
        </form>

        <!-- 상품 이름 검색 -->
        <form action="/kanri/searchByName.do" method="post">
            <label for="product_Name">製品名前:</label>
            <input type="text" id="product_Name" name="product_Name" placeholder="製品名前" />
            <input type="submit" value="検索" />
        </form>

        <!-- 저장 위치 검색 -->
        <form action="/kanri/searchByElse.do" method="post">
            <label for="location">位置:</label>
            <select id="location" name="location" class="form-select select-no-border">
                <option value="all">--選択--</option>
                <c:forEach var="location" items="${location}">
                    <option value="${location}">${location}</option>
                </c:forEach>
            </select>
            <input type="submit" value="検索" />
        </form>

        <!-- 초기화 버튼 -->
        <form action="/kanri/nothingInclude.do" method="post" style="margin-left: auto; margin-right: 3%;">
            <input type="submit" class="btn" style="background-color: black; color: white;" value="検索のリセット" />
        </form>
    </div>

    <!-- 검색 결과 출력 -->
    <table class="table">
        <thead>
            <tr class="text-center">
                <td>ID</td>
                <td>分類</td>
                <td>商品名</td>
                <td>製造元</td>
                <td>価格</td>
                <td>位置</td>
                <td>数量</td>
                <td>説明</td>
            </tr>
        </thead>
        <tbody id="orderBody" class="text-center">
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
                    <td colspan="8">検索結果がありません</td>
                </tr>
            </c:if>
        </tbody>
    </table>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <c:if test="${not empty pagedList}">
            <%
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
            %>
            <strong>[<%=i%>]</strong>
            <%
                } else {
            %>
            <a href="?page=<%=i%>">[<%=i%>]</a>
            <%
                }
                out.print("&nbsp;");
            }
            %>
        </c:if>
    </div>
</main>

<!-- Footer -->
<footer>
    <div class="container">
        &copy; <%=java.time.Year.now()%> 物流管理
    </div>
</footer>
</body>
</html>
