<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 회원가입</title>
</head>
<body>
    <h2>관리자 회원가입</h2>

    <form action="${pageContext.request.contextPath}/managerJoin.do" method="post">
        <p>
            관리자 ID:<br>
            <input type="text" name="manager_Id" placeholder="아이디 입력" required>
            <c:if test="${errors.manager_Id}">
                <span style="color:red;">아이디를 입력하세요.</span>
            </c:if>
            <c:if test="${errors.duplicateId}">
                <span style="color:red;">이미 등록된 아이디입니다.</span>
            </c:if>
        </p>

        <p>
            비밀번호:<br>
            <input type="password" name="password" placeholder="비밀번호 입력" required>
            <c:if test="${errors.password}">
                <span style="color:red;">비밀번호를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            비밀번호 재입력:<br>
            <input type="password" name="confirmPassword" placeholder="비밀번호 확인" required>
            <c:if test="${errors.confirmPassword}">
                <span style="color:red;">비밀번호 확인을 입력하세요.</span>
            </c:if>
            <c:if test="${errors.notMatch}">
                <span style="color:red;">비밀번호가 일치하지 않습니다.</span>
            </c:if>
        </p>

        <p>
            관리자 등록 코드:<br>
            <input type="text" name="registerCode" placeholder="등록코드 입력" required>
            <c:if test="${errors.registerCode}">
                <span style="color:red;">등록코드가 일치하지 않습니다.</span>
            </c:if>
        </p>

        <p>
            <input type="submit" value="관리자 가입">
        </p>
    </form>
</body>
</html>
