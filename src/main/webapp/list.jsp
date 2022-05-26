<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jstl/core_rt" prefix="c" %> <!-- JSTL 선언문 -->
<c:set var="path" value="${pageContext.request.contextPath}" /> <!-- JSTL 사용해서 절대경로 -->
<c:set var="path1" value="${requestScope['javax.servlet.forward.servlet_path']}"/> <!-- JSTL 사용해서 현재 절대경로 -->



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align=center>
<h2>파일목록</h2>
	<form action="">
		<table border=1>
			<tr>
				<td>파일명</td>
				<td>파일이미지</td>
				<td>파일다운로드</td>
			</tr>
			
			<c:forEach var="board" items="${li}">
			<tr>
				<td>${board.img}</td>
				<td><img src="${path}/files/${board.img}" width=100 height=100></td>
				<td><input type=submit value=다운로드></td>
			</tr>
			</c:forEach>		
		</table>
	</form>
</div>


</body>
</html>