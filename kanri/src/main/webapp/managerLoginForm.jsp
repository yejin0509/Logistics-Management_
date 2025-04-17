<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 로그인</title>
</head>
<body>
    <h2>관리자 로그인</h2>

    <form action="${pageContext.request.contextPath}/managerLogin.do" method="post">
        <c:if test="${errors.idOrPwNotMatch}">
            <span style="color:red;">아이디 또는 비밀번호가 일치하지 않습니다.</span>
        </c:if>

        <p>
            아이디:<br>
            <input type="text" name="manager_Id" placeholder="아이디 입력" value="${param.manager_Id}">
            <c:if test="${errors.manager_Id}">
                <span style="color:red;">아이디를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            비밀번호:<br>
            <input type="password" name="password" placeholder="비밀번호 입력">
            <c:if test="${errors.password}">
                <span style="color:red;">비밀번호를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            <input type="submit" value="로그인">
        </p>

        <!-- 회원가입 링크 -->
        <div class="login-links">
            <a href="${pageContext.request.contextPath}/managerJoin.do">회원가입</a>
        </div>
    </form>
</body>
</html>
