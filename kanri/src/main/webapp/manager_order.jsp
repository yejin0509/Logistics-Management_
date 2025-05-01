<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>入庫申請</title> <!-- 페이지 제목: 입고 신청 -->

    <!-- 부트스트랩 CSS 링크 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- 일본어 폰트 링크 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

    <style>
        /* 카드 헤더 스타일 */
        .card-header {
            background-color: transparent !important;
            border-bottom: none !important;
            box-shadow: none !important;
            padding: 25px 0px 0px 30px;
        }

        thead th {
            border-bottom: 2px solid #000; /* 테이블 헤더에 아래 테두리 추가 */
        }

        body {
            background-color: #f8f9fa; /* 배경색 설정 */
        }

        /* 헤더 스타일 */
        header {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
            padding: 20px 0;
        }

        /* 푸터 스타일 */
        footer {
            border-top: 1px solid #ddd;
            margin-top: 50px;
            padding: 20px 0;
            text-align: center;
            color: #777;
            font-size: 14px;
        }

        /* 총 금액 스타일 */
        .total-price {
            font-size: 1.25rem;
            font-weight: bold;
            text-align: right;
        }

        h1, h3 {
            font-weight: bold;
        }

        /* 전체 폰트 설정 */
        * {
            font-family: "Kosugi Maru", sans-serif;
            font-weight: 400;
            font-style: normal;
        }

        /* 로고 스타일 */
        .logo {
            position: fixed;
            max-width: 6.2%;
            height: auto;
            z-index: 90;
        }
    </style>
</head>
<body>
    <!-- 로고 링크 -->
    <a class="logo" href="managerMenu.jsp">
        <img alt="logo" src="./images/logo.png?ver=2" class="logo"> <!-- 로고 이미지 -->
    </a>

    <!-- Header 섹션 -->
    <header>
        <div class="container">
            <h1 class="text-center text-dark">入庫申請</h1> <!-- 페이지 제목: 입고 신청 -->
        </div>
    </header>

    <!-- Main 섹션 -->
    <main class="container my-4">
        <div class="card">
            <div class="card-header text-left">
                <h3 class="mb-0">Order Form</h3> <!-- 주문 양식 제목 -->
            </div>
            <div class="card-body">
                <!-- 주문 폼 -->
                <form action="InOrderHandler" method="post" id="manager_order" onsubmit="return validateForm()">
                    <table class="table">
                        <thead>
                            <tr class="text-center">
                                <th>商品名</th> <!-- 상품명 -->
                                <th>商品価格</th> <!-- 상품 가격 -->
                                <th>数量</th> <!-- 수량 -->
                                <th>金額</th> <!-- 금액 -->
                                <th>削除</th> <!-- 삭제 -->
                            </tr>
                        </thead>
                        <tbody id="orderBody">
                            <tr>
                                <!-- 상품 선택 -->
                                <td><select name="productId" class="form-select select-no-border" onchange="updatePrice(this)">
                                    <option value="">-- 商品選択 --</option> <!-- 상품 선택 옵션 -->
                                    <c:forEach var="item" items="${productList}">
                                        <option value="${item.product_Id}" data-price="${item.price}">
                                            ${item.product_Name}</option> <!-- 상품 리스트 표시 -->
                                    </c:forEach>
                                </select></td>

                                <!-- 상품 가격 입력 필드 -->
                                <td><input type="text" name="productPrice" readonly class="form-control no-border-input" /></td>

                                <!-- 수량 입력 필드 -->
                                <td><input type="number" name="quantity" min="1" value="1" class="form-control no-border-input" onchange="calculateRowTotal(this)" /></td>

                                <!-- 금액 계산 결과 -->
                                <td><input type="text" name="rowTotal" readonly class="form-control no-border-input" /></td>

                                <!-- 삭제 버튼 -->
                                <td class="text-center">
                                    <button type="button" class="btn" onclick="removeRow(this)">🗑️</button> <!-- 삭제 아이콘 -->
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <!-- 총 금액 및 추가 버튼 -->
                    <div class="d-flex justify-content-between align-items-center my-3">
                        <button type="button" class="btn btn-outline-primary" onclick="addRow()">Add</button> <!-- 행 추가 버튼 -->
                        <div class="total-price">
                            総入荷金額: <span id="grandTotal">0</span>円 <!-- 총 입고 금액 -->
                        </div>
                    </div>

                    <!-- 제출 버튼 -->
                    <div class="text-end">
                        <input type="submit" value="Submit Order" class="btn" style="background-color: black; color: white;" />
                    </div>
                </form>
            </div>
        </div>
    </main>

    <!-- Footer 섹션 -->
    <footer>
        <div class="container">
            &copy; <%=java.time.Year.now()%> 物流管理 <!-- 연도 출력 및 로고 -->
        </div>
    </footer>

    <!-- 부트스트랩 JS 링크 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // 가격 업데이트 함수
        function updatePrice(selectElement) {
            const price = selectElement.options[selectElement.selectedIndex].dataset.price || 0;
            const row = selectElement.closest('tr');
            row.querySelector('input[name="productPrice"]').value = price;
            calculateRowTotal(row.querySelector('input[name="quantity"]'));
        }

        // 각 행의 금액 계산 함수
        function calculateRowTotal(quantityInput) {
            const row = quantityInput.closest('tr');
            const price = parseInt(row.querySelector('input[name="productPrice"]').value || 0);
            const quantity = parseInt(quantityInput.value || 0);
            const total = price * quantity;
            row.querySelector('input[name="rowTotal"]').value = total;
            updateGrandTotal();
        }

        // 총 금액 업데이트 함수
        function updateGrandTotal() {
            let total = 0;
            document.querySelectorAll('input[name="rowTotal"]').forEach(input => {
                total += parseInt(input.value || 0);
            });
            document.getElementById('grandTotal').textContent = total.toLocaleString(); // 천 단위 쉼표 표시
        }

        // 행 추가 함수
        function addRow() {
            const tbody = document.getElementById('orderBody');
            const firstRow = tbody.querySelector('tr');
            const newRow = firstRow.cloneNode(true);

            newRow.querySelector('select').selectedIndex = 0;
            newRow.querySelector('input[name="productPrice"]').value = '';
            newRow.querySelector('input[name="quantity"]').value = 1;
            newRow.querySelector('input[name="rowTotal"]').value = '';

            tbody.appendChild(newRow);
        }

        // 행 삭제 함수
        function removeRow(button) {
            const row = button.closest('tr');
            const tbody = document.getElementById('orderBody');
            if (tbody.rows.length > 1) {
                row.remove();
                updateGrandTotal();
            } else {
                alert("少なくとも1つの商品をご用意ください。"); // 최소한 하나의 상품은 추가해야 한다는 경고 메시지
            }
        }

        // 폼 유효성 검사 함수
        function validateForm() {
            const rows = document.querySelectorAll("#orderBody tr");
            for (const row of rows) {
                const productId = row.querySelector("select[name='productId']").value;
                const productPrice = row.querySelector("input[name='productPrice']").value;
                const quantity = row.querySelector("input[name='quantity']").value;

                if (!productId) {
                    alert("商品を選択してください。"); // 상품을 선택해야 한다는 경고 메시지
                    return false;
                }
                if (!productPrice) {
                    alert("商品価格が未入力です。"); // 상품 가격이 입력되지 않았다는 경고 메시지
                    return false;
                }
                if (!quantity || parseInt(quantity) < 1) {
                    alert("数量を正しく入力してください。"); // 수량을 올바르게 입력해야 한다는 경고 메시지
                    return false;
                }
            }
            return true;
        }
    </script>
</body>
</html>
