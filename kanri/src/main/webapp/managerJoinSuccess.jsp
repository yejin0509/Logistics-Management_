<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 가입 완료</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #eaeaea;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .complete-box {
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            width: 350px;
            text-align: center;
        }

        .complete-box h2 {
            margin-bottom: 20px;
        }

        .complete-box p {
            margin-bottom: 30px;
        }

        .complete-box button {
            width: 100%;
            padding: 10px;
            background-color: black;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        .complete-box button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="complete-box">
        <h2>관리자 회원가입 완료</h2>
        <p>환영합니다! <br>이제 관리자 계정으로 로그인하실 수 있습니다.</p>
        <form action="/kanri/managerLogin.do" method="get">
        <br>
            <button type="submit">로그인 하기</button>
        </form>
    </div>
</body>
</html>
