<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注依頼</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<!-- 일본어 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">

<!-- 스타일 지정 -->

<style>

* {
	font-family: "Kosugi Maru", sans-serif;
	font-weight: 400;
	font-style: normal;
}
.card-header {
	background-color: transparent !important;
	border-bottom: none !important;
	box-shadow: none !important;
	padding: 25px 0px 0px 30px;
}

thead th {
	border-bottom: 2px solid #000;
}

body {
	background-color: #f8f9fa;
}

td button {
	display: block;
	margin: 0 auto;
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

.total-price {
	font-size: 1.25rem;
	font-weight: bold;
	text-align: right;
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
			<h1 class="text-center text-dark">発注依頼</h1>
		</div>
	</header>

	<!-- Main -->
	<main class="container my-4">
		<div class="card">
			<div class="card-header text-left">
				<h3 class="mb-0">Order Form</h3>
			</div>
			<div class="card-body">
				<form action="OrderHandler" method="post" id="client_order"  onsubmit ="return validateForm()">
					<table class="table">
						<thead>
							<tr class="text-center">
								<th>商品名</th>
								<th>商品価格</th>
								<th>数量 </th>
								<th>金額</th>
								<th>削除</th>
							</tr>
						</thead>
						<tbody id="orderBody">
							<!-- 첫 상품 행 -->
							<tr>
								<td><select name="productId"
									class="form-select select-no-border"
									onchange="updatePrice(this)">
										<option value="">-- 商品選択 --</option>
										<c:forEach var="item" items="${productList}">
											<option value="${item.product_Id}" data-price="${item.price}">
												${item.product_Name}</option>
										</c:forEach>
								</select></td>
								<td><input type="text" name="productPrice" readonly
									class="form-control no-border-input" /></td>
								<td><input type="number" name="quantity" min="1" value="1"
									class="form-control no-border-input"
									onchange="calculateRowTotal(this)" /></td>
								<td><input type="text" name="rowTotal" readonly
									class="form-control no-border-input" /></td>
								<td class="text-center">
									<button type="button" class="btn" onclick="removeRow(this)">🗑️</button>
								</td>
							</tr>
						</tbody>
					</table>

					<div class="d-flex justify-content-between align-items-center my-3">
						<button type="button" class="btn btn-outline-primary"
							onclick="addRow()">Add</button>
						<div class="total-price">
							総発注金額: <span id="grandTotal">0</span> 円
						</div>
					</div>

					<div class="text-end">
						<input type="submit" value="Submit Order" class="btn"
							style="background-color: black; color: white;" />
					</div>
				</form>
			</div>
		</div>
	</main>

	<!-- Footer -->
	<footer>
		<div class="container">
			&copy;
			<%=java.time.Year.now()%>
			物流管理
		</div>
	</footer>

	<!--  Bootstrap 라이브러리 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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
            document.getElementById('grandTotal').textContent = total.toLocaleString();
        }

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

        function removeRow(button) {
            const row = button.closest('tr');
            const tbody = document.getElementById('orderBody');
            if (tbody.rows.length > 1) {
                row.remove();
                updateGrandTotal();
            } else {
                alert("少なくとも1つの商品をご用意ください。");
            }
        }
        
        function validateForm() {
            const rows = document.querySelectorAll("#orderBody tr");
            for (const row of rows) {
                const productId = row.querySelector("select[name='productId']").value;
                const productPrice = row.querySelector("input[name='productPrice']").value;
                const quantity = row.querySelector("input[name='quantity']").value;

                if (!productId) {
                    alert("商品を選択してください。");
                    return false;
                }
                if (!productPrice) {
                    alert("商品価格が未入力です。");
                    return false;
                }
                if (!quantity || parseInt(quantity) < 1) {
                    alert("数量を正しく入力してください。");
                    return false;
                }
            }
            return true;
        }
    </script>
</body>
</html>