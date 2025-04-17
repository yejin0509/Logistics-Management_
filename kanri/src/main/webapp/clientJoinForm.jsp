<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <h2>회원가입</h2>

    <form action="/kanri/clientJoin.do" method="post"> 
        <p>
            아이디:<br>
            <input type="text" name="client_Id" placeholder="영문 입력" required>
            <c:if test="${errors.client_Id}">
                <span style="color:red;">ID를 입력하세요.</span>
            </c:if>
            <c:if test="${errors.duplicateId}">
                <span style="color:red;">이미 사용중인 아이디입니다.</span>
            </c:if>
        </p>

        <p>
            비밀번호 입력:<br>
            <input type="password" name="password" placeholder="숫자 입력" required>
            <c:if test="${errors.password}">
                <span style="color:red;">비밀번호를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            비밀번호 재입력:<br>
            <input type="password" name="confirmPassword" placeholder="한번 더 입력해 주세요" required>
            <c:if test="${errors.confirmPassword}">
                <span style="color:red;">비밀번호 확인을 입력하세요.</span>
            </c:if>
            <c:if test="${errors.notMatch}">
                <span style="color:red;">비밀번호가 일치하지 않습니다.</span>
            </c:if>
        </p>

        <p>
            회사 연락처:<br>
            <select name="c_Number1" style="width:50px; height:24px;">
                <option value="">선택</option>
                <option value="010">010</option>
                <option value="011">011</option>
                <option value="016">016</option>
                <option value="017">017</option>
                <option value="018">018</option>
                <option value="019">019</option>
                <option value="02">02</option>
                <option value="031">031</option>
                <option value="032">032</option>
                <option value="051">051</option>
                <option value="053">053</option>
                <option value="062">062</option>
                <option value="042">042</option>
                <option value="052">052</option>
                <option value="055">055</option>
                <option value="064">064</option>
            </select>
            -
            <input type="text" name="c_Number2" maxlength="4" size="4" placeholder="앞자리" required>
            -
            <input type="text" name="c_Number3" maxlength="4" size="4" placeholder="뒷자리" required>

            <c:if test="${errors.c_Number}">
                <br><span style="color:red;">연락처를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            회사명:<br>
            <input type="text" name="company" placeholder="회사명을 입력하세요" required>
            <c:if test="${errors.company}">
                <span style="color:red;">회사명을 입력하세요.</span>
            </c:if>
        </p>

        <p>
            주소:<br>
            <input type="text" name="address" placeholder="회사주소를 입력하세요" required>
            <c:if test="${errors.address}">
                <span style="color:red;">주소를 입력하세요.</span>
            </c:if>
        </p>

        <p>
            <label><input type="checkbox" name="tosAgree" required> 이용약관 동의 (필수)</label><br>
            <label><input type="checkbox" name="privacyAgree" required> 개인정보 수집 및 이용 동의 (필수)</label><br>
            <c:if test="${errors.tosAgree}">
                <span style="color:red;">약관 동의가 필요합니다.</span>
            </c:if>
            <c:if test="${errors.privacyAgree}">
                <span style="color:red;">개인정보 동의가 필요합니다.</span>
            </c:if>
        </p>

        <p>
            <input type="submit" value="회원 가입하기">
        </p>
    </form>
</body>
</html>
