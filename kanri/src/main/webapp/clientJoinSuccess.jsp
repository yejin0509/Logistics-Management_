<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 완료</title>
</head>
<body>
    <h2>회원가입이 완료되었습니다.</h2>

    <p>환영합니다! 이제 로그인하셔서 서비스를 이용하실 수 있습니다.</p>

    <!-- 로그인 이동 버튼 -->
    <form action="${pageContext.request.contextPath}/clientLogin.do" method="get"> <!-- 경로 수정된 부분 -->
        <button type="submit">로그인 화면으로 이동</button>
    </form>
</body>
</html>
