<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
    <meta charset="UTF-8">
    <title>고객 로그인</title>
</head>
<body>
    <div class="login-box">
        <h3>고객 로그인</h3>
        <form action="${pageContext.request.contextPath}/clientLogin.do" method="post"> 
           
        <c:if test="${errors.idOrPwNotMatch}">
            <span style="color:red;">아이디와 암호가 일치하지 않습니다.</span>
        </c:if>

        <p>
            아이디:<br />
            <input type="text" name="client_Id" value="${param.client_Id}">
            <c:if test="${errors.client_Id}">
                <span style="color:red;">ID를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            암호:<br />
            <input type="password" name="password">
            <c:if test="${errors.password}">
                <span style="color:red;">암호를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            <input type="submit" value="로그인하기">
        </p>

        <div class="login-links">
    		<a href="${pageContext.request.contextPath}/clientjoin.do">회원가입</a> <!-- 경로 수정된 부분 -->
		</div>
        </form>
    </div>
</body>
</html>
