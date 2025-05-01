<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理者会員登録</title> <!-- 페이지 제목: 관리자 회원 등록 -->

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
            justify-content: center;
            align-items: center;
            height: 100vh; /* 화면 중앙에 위치 */
        }

        /* 등록 박스 스타일 */
        .register-box {
            background: #ffffff; /* 흰색 배경 */
            padding: 30px 40px;
            border-radius: 10px; /* 모서리 둥글게 처리 */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* 그림자 효과 */
            width: 350px; /* 너비 설정 */
        }

        /* 텍스트 입력 필드 스타일 */
        .register-box input[type="text"], .register-box input[type="password"] {
            width: 100%; /* 너비 100%로 설정 */
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px; /* 테두리 둥글게 처리 */
            box-sizing: border-box;
        }

        /* 제출 버튼 스타일 */
        .register-box input[type="submit"] {
            width: 100%; /* 너비 100%로 설정 */
            padding: 10px;
            background-color: black;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold; /* 글자 두껍게 */
        }

        /* 버튼 호버 스타일 */
        .register-box input[type="submit"]:hover {
            background-color: #0056b3; /* 호버 시 배경색 변경 */
        }

        /* 오류 메시지 스타일 */
        .register-box span {
            color: red; /* 빨간색으로 오류 메시지 표시 */
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <div class="register-box">
        <h2>管理者会員登録</h2> <!-- 페이지 제목: 관리자 회원 등록 -->

        <!-- 관리자 등록 폼 -->
        <form action="/kanri/managerJoin.do" method="post">
            
            <!-- 관리자 ID 입력 필드 -->
            <input type="text" name="manager_Id" placeholder="ID" required> <!-- ID 입력 -->
            <c:if test="${errors.manager_Id}">
                <span>IDを入力してください</span> <!-- ID 입력 필수 오류 메시지 -->
            </c:if>
            <c:if test="${errors.duplicateId}">
                <span>すでに登録されているIDです</span> <!-- 이미 등록된 ID 오류 메시지 -->
            </c:if>

            <!-- 비밀번호 입력 필드 -->
            <input type="password" name="password" placeholder="パスワード" required> <!-- 비밀번호 입력 -->
            <c:if test="${errors.password}">
                <span>パスワードを入力してください</span> <!-- 비밀번호 입력 필수 오류 메시지 -->
            </c:if>

            <!-- 비밀번호 확인 입력 필드 -->
            <input type="password" name="confirmPassword" placeholder="パスワード確認" required> <!-- 비밀번호 확인 입력 -->
            <c:if test="${errors.confirmPassword}">
                <span>パスワード確認</span> <!-- 비밀번호 확인 필수 오류 메시지 -->
            </c:if>
            <c:if test="${errors.notMatch}">
                <span>パスワードが一致しません</span> <!-- 비밀번호 불일치 오류 메시지 -->
            </c:if>
            
            <!-- 관리자 등록 코드 입력 필드 -->
            <input type="text" name="registerCode" placeholder="管理者登録コード入力" required> <!-- 관리자 등록 코드 입력 -->
            <c:if test="${errors.registerCode}">
                <span>登録コードが一致しません</span><br> <!-- 등록 코드 불일치 오류 메시지 -->
            </c:if>
            
            <div>
                <br>
                <!-- 제출 버튼 -->
                <input type="submit" value="管理者登録"> <!-- 관리자 등록 버튼 -->
            </div>
        </form>
    </div>
</body>
</html>
