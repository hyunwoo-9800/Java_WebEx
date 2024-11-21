<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>

	test 테이블의 내용
	<table border="1">
		<tr>
			<td>번호</td><td>이름</td><td>나이</td>
		</tr>
		
		<!-- JDBC 로딩 -->
		
		<%
		
		Class.forName("org.postgresql.Driver");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String jdbcDriver = "jdbc:postgresql://localhost:5432/postgres";
			String dbUser = "postgres";
			String dbPass = "dev";
			
			String query = "select * from test order by number";
			
			/* 데이터베이스 커넥션 생성 */
			conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			
			/* Statement 생성 */
			stmt = conn.createStatement();
			
			/* 쿼리 실행 */
			rs = stmt.executeQuery(query);
			
			/* 쿼리 실행 결과 출력 */
			while(rs.next()) {
				
		%>
		<tr>
		
		<td><%= rs.getInt("number") %></td>
		<td><%= rs.getString("name") %></td>
		<td><%= rs.getInt("age") %></td>
		<%
		
			}
		} catch(SQLException ex) {
			
			out.println(ex.getMessage());
			ex.printStackTrace();
			
		} finally {
			
			// 사용한 Statement 종료
			
			if(rs != null) {
				
				try {
					
					rs.close();
					
				} catch(SQLException ex) {
					
				}
				
			}
			
			if(stmt != null) {
				
				try {
					
					stmt.close();
					
				} catch(SQLException ex) {
					
				}
				
			}
			
			// 커넥션 종료
			
			if(conn != null) {
				
				try {
					
					conn.close();
					
				} catch(SQLException ex) {
					
				}
				
			}
			
		}
		
		%>
		</tr>
	</table>

</body>
</html>