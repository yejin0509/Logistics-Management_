<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出庫履歴</title>
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
    padding: 8px;
    border: 1px solid #ccc;
}

.mb-4 {
    margin-bottom: 2rem;
}

.text-end {
    text-align: right;
}
</style>

</head>
<body>

    <!-- Logo -->
    <a class="logo" href="clientMenu.jsp">
        <img alt="logo" src="./images/logo.png?ver=2" class="logo">
    </a>

    <!-- Header -->
    <header>
        <div class="container">
            <h1 class="text-center text-dark">出庫履歴</h1>
        </div>
    </header>

    <!-- Main Content -->
    <main class="container my-4">

        <!-- 날짜 필터 -->
        <div class="mb-4">
            <form action="/kanri/date-filter2.do" method="get" class="d-flex align-items-center gap-2">
                <label for="out_Date" class="form-label mb-0" style="white-space: nowrap;">注文履歴照会</label>
                <input type="date" id="out_Date" name="out_Date" value="${param.out_Date}" class="form-control" style="max-width: 200px;">
                <button type="submit" class="btn btn-dark" style="white-space: nowrap;">日付照会</button>
            </form>
        </div>

        <!-- 테이블 -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>注文者 ID</th>
                    <th>商品 ID</th>
                    <th>注文日</th>
                    <th>合計金額</th>
                    <th>合計数量</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="OutBound" items="${OutBoundPage.content}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${OutBound.client_Id}</td>
                        <td>${OutBound.product_Id}</td>
                        <td><fmt:formatDate value="${OutBound.out_Date}" pattern="yyyy年MM月dd日" /></td> <!-- 일본어 날짜 포맷 -->
                        <td>${OutBound.price}</td>
                        <td>${OutBound.out_Count}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 뒤로가기 버튼 -->
        <div class="text-end mt-3">
            <button type="button" onclick="history.back();" class="btn btn-secondary" style="width: 150px; height: 50px; font-size: 18px;">
                戻る
            </button>
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
