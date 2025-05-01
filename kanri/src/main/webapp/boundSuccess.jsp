<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文成功</title> <!-- 페이지 제목: 주문 성공 / ページタイトル：注文成功 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 일본어 폰트 / 日本語フォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">

<style>
<!-- 스타일 지정 / スタイル設定 -->
* {
	font-family: "Kosugi Maru", sans-serif; /* 폰트 설정 / フォント設定 */
	font-weight: 400;
	font-style: normal;
}

.card-header {
	background-color: transparent !important; /* 카드 헤더 배경 투명 설정 / カードヘッダーの背景を透明に設定 */
	border-bottom: none !important;
	box-shadow: none !important;
	padding: 50px 0;
}

.card-body {
	flex: 1 1 auto;
	padding: 30px 0;
	color: var(--bs-card-color);
}

thead th {
	border-bottom: 2px solid #000; /* 테이블 헤더 하단 경계선 설정 / テーブルヘッダー下の境界線を設定 */
}

body {
	background-color: #f8f9fa; /* 배경 색상 설정 / 背景色の設定 */
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
	text-align: right;
}

h1, h3 {
	font-weight: bold;
}

* {
	font-family: "Kosugi Maru", sans-serif; /* 폰트 설정 / フォント設定 */
	font-weight: 400;
	font-style: normal;
}
</style>
</head>
<body>

	<!-- Header / ヘッダー -->
	<header>
		<div class="container">
			<h1 class="text-center text-dark">成功</h1> <!-- 제목: 성공 / タイトル：成功 -->
		</div>
	</header>

	<!-- Main / メイン -->
	<main class="container my-4">
		<div class="card">
			<div class="card-header text-center">
				<h3 class="mb-0">注文が完了しました。</h3> <!-- 주문 완료 메시지 / 注文が完了しました -->
			</div>
			<div class="card-body">
				<div class="text-center">

					<button type="button" id="btn" class="btn btn-dark">ホームページ</button> <!-- '홈페이지' 버튼 / 'ホームページ'ボタン -->

				</div>
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
	// 세션 변수 Id를 제대로 전달하기 위해 작은 따옴표를 추가 / セッション変数Idを正しく渡すためにシングルクォートを追加
    var Id = "<%=session.getAttribute("Id")%>"; 
    console.log("Id:", Id); // 세션 값 확인 / セッション値確認

    $("#btn").click(function() {
        if (Id === "managerId") {
            location.href = "managerMenu.jsp";  // 관리자 페이지로 이동 / 管理者ページに移動
        } else if (Id === "clientId") {
            location.href = "clientMenu.jsp";   // 클라이언트 페이지로 이동 / クライアントページに移動
        } else {
            alert("잘못된 ID입니다."); // 잘못된 ID에 대한 경고 메시지 / 無効なIDに対する警告メッセージ
        }
    });
	</script>

</body>
</html>
