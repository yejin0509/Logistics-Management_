<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者登録完了</title> <!-- 페이지 제목: 관리자 등록 완료 -->

    <!-- 일본어 폰트 링크 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

    <style>
        * {
            font-family: "Kosugi Maru", sans-serif; /* 일본어 폰트 설정 */
            font-weight: 400;
            font-style: normal;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #eaeaea; /* 배경색 설정 */
            display: flex;
            justify-content: center; /* 수평 중앙 정렬 */
            align-items: center; /* 수직 중앙 정렬 */
            height: 100vh; /* 화면 전체 높이 사용 */
        }

        /* 완료 박스 스타일 */
        .complete-box {
            background: #ffffff; /* 흰색 배경 */
            padding: 30px 40px;
            border-radius: 10px; /* 모서리 둥글게 처리 */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* 그림자 효과 */
            width: 350px; /* 너비 설정 */
            text-align: center; /* 텍스트 중앙 정렬 */
        }

        /* 제목 스타일 */
        .complete-box h2 {
            margin-bottom: 20px; /* 제목과 다른 요소 간 간격 설정 */
        }

        /* 문단 스타일 */
        .complete-box p {
            margin-bottom: 30px; /* 문단과 다른 요소 간 간격 설정 */
        }

        /* 버튼 스타일 */
        .complete-box button {
            width: 100%; /* 버튼 너비 100%로 설정 */
            padding: 10px;
            background-color: black; /* 배경색 설정 */
            color: #ffffff; /* 글자 색 흰색 */
            border: none;
            border-radius: 5px; /* 모서리 둥글게 처리 */
            cursor: pointer; /* 마우스 커서 손 모양으로 변경 */
            font-weight: bold; /* 글자 두껍게 */
        }

        /* 버튼 호버 스타일 */
        .complete-box button:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
        }
    </style>
</head>
<body>
    <!-- 관리자 등록 완료 메시지 박스 -->
    <div class="complete-box">
        <h2>管理者登録完了</h2> <!-- 관리자 등록 완료 제목 -->

        <!-- 완료 메시지 -->
        <p>ようこそ！ <br> <br>これで管理者アカウントでログインできます。</p> <!-- 환영 메시지 및 로그인 안내 -->

        <!-- 로그인 버튼 -->
        <form action="/kanri/managerLogin.do" method="get">
            <button type="submit">ログイン</button> <!-- 로그인 버튼 -->
        </form>
    </div>
</body>
</html>
