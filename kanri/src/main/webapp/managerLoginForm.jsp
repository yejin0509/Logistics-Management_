<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者ログイン</title> <!-- 페이지 제목: 관리자 로그인 -->

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

        /* 로그인 박스 스타일 */
        .login-box {
            background: #ffffff; /* 흰색 배경 */
            padding: 30px 40px;
            border-radius: 10px; /* 모서리 둥글게 처리 */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* 그림자 효과 */
            width: 350px; /* 너비 설정 */
        }

        /* 입력창 스타일 */
        .login-box input[type="text"], .login-box input[type="password"] {
            width: 100%; /* 입력창 너비 100%로 설정 */
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc; /* 테두리 색상 설정 */
            border-radius: 5px; /* 모서리 둥글게 처리 */
            box-sizing: border-box;
        }

        /* 로그인 버튼 스타일 */
        .login-box input[type="submit"] {
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
        .login-box input[type="submit"]:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
        }

        /* 로그인 링크 스타일 */
        .login-box .login-links {
            margin-top: 15px; /* 상단 간격 설정 */
            text-align: center; /* 텍스트 중앙 정렬 */
        }

        /* 로그인 링크 스타일 */
        .login-box .login-links a {
            text-decoration: none; /* 링크 밑줄 제거 */
            font-size: 14px; /* 글자 크기 설정 */
        }

        /* 오류 메시지 스타일 */
        .login-box span {
            color: red; /* 오류 메시지 빨간색 */
            font-size: 0.9em; /* 글자 크기 설정 */
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>管理者ログイン</h2> <!-- 관리자 로그인 제목 -->

        <!-- 로그인 폼 -->
        <form action="/kanri/managerLogin.do" method="post">
            <!-- ID 또는 비밀번호가 맞지 않으면 오류 메시지 표시 -->
            <c:if test="${errors.idOrPwNotMatch}">
                <span> IDまたはパスワードが一致しません</span> <!-- 오류 메시지: ID 또는 비밀번호 불일치 -->
                <br/><br/>
            </c:if>

            <p>
                <!-- 관리자 ID 입력란 -->
                <br/><input type="text" name="manager_Id" placeholder="ID" value="${param.manager_Id}">
                <c:if test="${errors.manager_Id}">
                    <span>IDを入力してください</span> <!-- 오류 메시지: ID를 입력하세요 -->
                </c:if>

                <!-- 비밀번호 입력란 -->
                <br/><input type="password" name="password" placeholder="パスワード">
                <c:if test="${errors.password}">
                    <span>パスワードを入力してください</span> <!-- 오류 메시지: 비밀번호를 입력하세요 -->
                </c:if>
            </p>

            <p>
                <!-- 로그인 버튼 -->
                <input type="submit" value="ログイン">
            </p>

            <!-- 회원 가입 링크 -->
            <div class="login-links">
                <a href="/kanri/managerJoin.do">会員登録</a> <!-- 회원가입 페이지로 이동하는 링크 -->
            </div>
        </form>
    </div>
</body>
</html>
