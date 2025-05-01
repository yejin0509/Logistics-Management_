<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="jdbc.connection.ConnectionProvider" %> <!-- JDBC 연결을 위한 클래스 import -->
<%@ page import="java.sql.*" %> <!-- SQL 관련 클래스를 import -->

<html>
<head><title>연결 테스트</title></head> <!-- 페이지 제목: "연결 테스트" -->
<body>
<%
	try (Connection conn = ConnectionProvider.getConnection()) { 
		// 데이터베이스 커넥션을 가져오기 위한 try-with-resources 구문 사용
		// ConnectionProvider에서 getConnection() 메서드를 호출하여 커넥션 객체 conn을 가져옴
		out.println("커넥션 연결 성공함");  // 커넥션 연결이 성공하면 출력
	} catch(SQLException ex) {  
		// SQL 예외 처리: 커넥션 연결에 실패한 경우
		out.println("커넥션 연결 실패함 : " + ex.getMessage());  // 에러 메시지를 출력
		application.log("커넥션 연결 실패", ex);  // 에러 발생 시 로그를 기록
	}
%>
</body>
</html>
