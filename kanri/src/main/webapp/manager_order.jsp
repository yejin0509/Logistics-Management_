<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고 신청</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 10px;
}

th, td {
	padding: 8px;
	border: 1px solid #ddd;
	text-align: center;
}

button {
	padding: 5px 10px;
}

.total-price {
	font-weight: bold;
	margin-top: 15px;
}
</style>
</head>

<body>

	<h2>📦입고 신청 폼</h2>

	<form action="InOrderHandler" method="post" id="manager_order">

		<table id="orderTable">
			<thead>
				<tr>
					<th>상품명</th>
					<th>상품가격</th>
					<th>수량</th>
					<th>금액</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody id="orderBody">
				<!-- 첫 상품 행 -->
				<tr>
					<td><select name="productId" onchange="updatePrice(this)">
							<option value="">-- 상품 선택 --</option>
							<c:forEach var="item" items="${productList}">
								<option value="${item.product_Id}" data-price="${item.price}">
									${item.product_Name}</option>
							</c:forEach>
					</select></td>
					<td><input type="text" name="productPrice" readonly /></td>
					<td><input type="number" name="quantity" min="1" value="1"
						onchange="calculateRowTotal(this)" /></td>
					<td><input type="text" name="rowTotal" readonly /></td>
					<td><button type="button" onclick="removeRow(this)">삭제</button></td>
				</tr>
			</tbody>
		</table>

		<!-- 행 추가 버튼 -->
		<button type="button" onclick="addRow()">상품 추가</button>

		<!-- 전체 총액 표시 -->
		<div class="total-price">
			총 발주 금액: <span id="grandTotal">0</span> 원
		</div>

		<!-- 발주 신청 -->
		<br /> <input type="submit" value="발주 신청" />
		
	</form>

	<script>
    function updatePrice(selectElement) {
        const price = selectElement.options[selectElement.selectedIndex].dataset.price || 0;
        const row = selectElement.closest('tr');
        row.querySelector('input[name="productPrice"]').value = price;
        calculateRowTotal(row.querySelector('input[name="quantity"]'));
    }

    function calculateRowTotal(quantityInput) {
        const row = quantityInput.closest('tr');
        const price = parseInt(row.querySelector('input[name="productPrice"]').value || 0);
        const quantity = parseInt(quantityInput.value || 0);
        const total = price * quantity;
        row.querySelector('input[name="rowTotal"]').value = total;
        updateGrandTotal();
    }

    function updateGrandTotal() {
        let total = 0;
        document.querySelectorAll('input[name="rowTotal"]').forEach(input => {
            total += parseInt(input.value || 0);
        });
        document.getElementById('grandTotal').textContent = total;
    }

    function addRow() {
        const tbody = document.getElementById('orderBody');
        const firstRow = tbody.querySelector('tr');
        const newRow = firstRow.cloneNode(true);

        // 초기화
        newRow.querySelector('select').selectedIndex = 0;
        newRow.querySelector('input[name="productPrice"]').value = '';
        newRow.querySelector('input[name="quantity"]').value = 1;
        newRow.querySelector('input[name="rowTotal"]').value = '';

        tbody.appendChild(newRow);
    }

    function removeRow(button) {
        const row = button.closest('tr');
        const tbody = document.getElementById('orderBody');
        if (tbody.rows.length > 1) {
            row.remove();
            updateGrandTotal();
        } else {
            alert("최소 한 개의 상품은 있어야 합니다.");
        }
    }
</script>

</body>
</html>
