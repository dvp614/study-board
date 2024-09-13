<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록조회</title>
</head>
<body>
	<h3><%=request.getRequestURI()%></h3>
	<hr>

	<div>
		<h3>Board List</h3>

		<table border="1">
			<tr>
				<th>ID</th>
				<th>TITLE</th>
				<th>WRITER</th>
				<th>CNT</th>
				<th>CREATEDATE</th>
				<th>UPDATEDATE</th>
			</tr>


			<c:forEach var="board" items="${__CURR_PAGE__.content}">
				<tr>
					<td>${board.id}</td>
					<td>${board.title}</td>
					<td>${board.writer}</td>
					<td>${board.cnt}</td>
					<td>${board.createDate}</td>
					<td>${board.updateDate}</td>
				</tr>
			</c:forEach>
		</table>
		
		<br>
		
		<ul>
			<li><a href="getBoardList?currPage=0">F</a></li>
			<li><a href="getBoardList?currPage=${__CURR_PAGE__.number - 1}">P</a></li>
			
			<c:forEach var="counter" begin="${1}" end="${__CURR_PAGE__.totalPages-1}" step="1">
				<li><a href="getBoardList?currPage=${counter}">${counter}</a></li>
			</c:forEach>
			<li><a href="getBoardList?currPage=${__CURR_PAGE__.number + 1}">N</a></li>
			<li><a href="getBoardList?currPage=${__CURR_PAGE__.totalPages -1}">L</a></li>
		</ul>
		
		<hr>

		<a href="registerBoard?currPage=${param.currPage}">REGISTER</a>
	</div>

</body>
</html>