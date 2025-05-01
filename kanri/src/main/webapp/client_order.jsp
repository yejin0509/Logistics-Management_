<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注依頼</title> <!-- 페이지 제목: 발주 요청 / ページタイトル：発注依頼 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<!-- 일본어 폰트 / 日本語フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">

<!-- 스타일 지정 / スタイル設定 -->

<style>

* {
	font-family: "Kosugi Maru", sans-serif; /* 폰트 설정 / フォント設定 */
	font-weight: 400;
	font-style: normal;
}

.card-header {
	background-color: transparent !important; /* 카드 헤더 배경 투명 설정 / カードヘッダーの背景を透明に設定 */
	border-bottom: none !important;
	box-shadow: none !important;
	padding: 25px 0px 0px 30px;
}

thead th {
	border-bottom: 2px solid #000; /* 테이블 헤더 하단 경계선 설정 / テーブルヘッダー下の境界線を設定 */
}

body {
	background-color: #f8f9fa; /* 배경 색상 설정 / 背景色の設定 */
}

td button {
	display: block;
	margin: 0 auto;
}

header {
	background-color: #fff; /* 헤더 배경 색상 / ヘッダーの背景色 */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 헤더 그림자 효과 / ヘッダーのシャドウ効果 */
	padding: 20px 0;
}

footer {
	border-top: 1px solid #ddd; /* 푸터 상단 경계선 설정 / フッター上の境界線を設定 */
	margin-top: 50px;
	padding: 20px 0;
	text-align: center;
	color: #777;
	font-size: 14px;
}

.total-price {
	font-size: 1.25rem;
	font-weight: bold;
	text-align: right; /* 총 가격 우측 정렬 / 総価格右寄せ */
}

h1, h3 {
	font-weight: bold;
}

.logo {
	position: fixed;
	max-width: 6.2%; /* 로고 위치 설정 / ロゴ位置設定 */
	height: auto;
	z-index: 90;
}
</style>
</head>
<body>
	<!-- Logo / ロゴ -->
<a class="logo" href="clientMenu.jsp">
    <img alt="logo" src="./images/logo.png?ver=2" class="logo">
</a>
	<!-- Header / ヘッダー -->
	<header>
		<div class="container">
			<h1 class="text-center text-dark">発注依頼</h1> <!-- 제목: 발주 요청 / タイトル：発注依頼 -->
		</div>
	</header>

	<!-- Main / メイン -->
	<main class="container my-4">
		<div class="card">
			<div class="card-header text-left">
				<h3 class="mb-0">Order Form</h3> <!-- 주문 양식 제목 / 注文フォームタイトル -->
			</div>
			<div class="card-body">
				<form action="OrderHandler" method="post" id="client_order"  onsubmit ="return validateForm()">
					<table class="table">
						<thead>
							<tr class="text-center">
								<th>商品名</th> <!-- 상품명 / 商品名 -->
								<th>商品価格</th> <!-- 상품 가격 / 商品価格 -->
								<th>数量 </th> <!-- 수량 / 数量 -->
								<th>金額</th> <!-- 금액 / 金額 -->
								<th>削除</th> <!-- 삭제 / 削除 -->
							</tr>
						</thead>
						<tbody id="orderBody">
							<!-- 첫 상품 행 / 最初の商品行 -->
							<tr>
								<td><select name="productId"
									class="form-select select-no-border"
									onchange="updatePrice(this)">
										<option value="">-- 商品選択 --</option> <!-- 상품 선택 / 商品選択 -->
										<c:forEach var="item" items="${productList}">
											<option value="${item.product_Id}" data-price="${item.price}">
												${item.product_Name}</option> <!-- 상품 이름 / 商品名 -->
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
									<button type="button" class="btn" onclick="removeRow(this)">🗑️</button> <!-- 삭제 버튼 / 削除ボタン -->
								</td>
							</tr>
						</tbody>
					</table>

					<div class="d-flex justify-content-between align-items-center my-3">
						<button type="button" class="btn btn-outline-primary"
							onclick="addRow()">Add</button> <!-- 행 추가 버튼 / 行追加ボタン -->
						<div class="total-price">
							総発注金額: <span id="grandTotal">0</span> 円 <!-- 총 발주 금액 / 総発注金額 -->
						</div>
					</div>

					<div class="text-end">
						<input type="submit" value="Submit Order" class="btn"
							style="background-color: black; color: white;" /> <!-- 주문 제출 버튼 / 注文提出ボタン -->
					</div>
				</form>
			</div>
		</div>
	</main>

	<!-- Footer / フッター -->
	<footer>
		<div class="container">
			&copy;
			<%=java.time.Year.now()%> <!-- 현재 연도 / 現在の年 -->
			物流管理 <!-- 사이트명 / サイト名 -->
		</div>
	</footer>

	<!-- Bootstrap 라이브러리 / Bootstrapライブラリ -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        // 가격 업데이트 함수 / 価格更新関数
        function updatePrice(selectElement) {
            const price = selectElement.options[selectElement.selectedIndex].dataset.price || 0;
            const row = selectElement.closest('tr');
            row.querySelector('input[name="productPrice"]').value = price;
            calculateRowTotal(row.querySelector('input[name="quantity"]'));
        }

        // 행의 총액 계산 함수 / 行の合計額計算関数
        function calculateRowTotal(quantityInput) {
            const row = quantityInput.closest('tr');
            const price = parseInt(row.querySelector('input[name="productPrice"]').value || 0);
            const quantity = parseInt(quantityInput.value || 0);
            const total = price * quantity;
            row.querySelector('input[name="rowTotal"]').value = total;
            updateGrandTotal();
        }

        // 총액 업데이트 함수 / 総額更新関数
        function updateGrandTotal() {
            let total = 0;
            document.querySelectorAll('input[name="rowTotal"]').forEach(input => {
                total += parseInt(input.value || 0);
            });
            document.getElementById('grandTotal').textContent = total.toLocaleString();
        }

        // 행 추가 함수 / 行追加関数
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

        // 행 삭제 함수 / 行削除関数
        function removeRow(button) {
            const row = button.closest('tr');
            const tbody = document.getElementById('orderBody');
            if (tbody.rows.length > 1) {
                row.remove();
                updateGrandTotal();
            } else {
                alert("少なくとも1つの商品をご用意ください。"); // 상품을 하나 이상 추가해야 함 / 商品を少なくとも1つ追加してください。
            }
        }
        
        // 폼 검증 함수 / フォーム検証関数
        function validateForm() {
            const rows = document.querySelectorAll("#orderBody tr");
            for (const row of rows) {
                const productId = row.querySelector("select[name='productId']").value;
                const productPrice = row.querySelector("input[name='productPrice']").value;
                const quantity = row.querySelector("input[name='quantity']").value;

                if (!productId) {
                    alert("商品を選択してください。"); // 상품을 선택해야 함 / 商品を選択してください。
                    return false;
                }
                if (!productPrice) {
                    alert("商品価格が未入力です。"); // 가격을 입력해야 함 / 価格が未入力です。
                    return false;
                }
                if (!quantity || parseInt(quantity) < 1) {
                    alert("数量を正しく入力してください。"); // 수량을 올바르게 입력해야 함 / 数量を正しく入力してください。
                    return false;
                }
            }
            return true;
        }
    </script>
</body>
</html>
