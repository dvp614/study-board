<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세조회</title>
</head>
<body>
	<h3><%=request.getRequestURI()%></h3>
	<hr>

	<div>
		<h3>REGISTER</h3>

		<form action="registerBoard" method="POST">
			<input type="hidden" name="currPage" value="${param.currPage == ''? 1:param.currPage }">
			<table border="1">
				<tr>
					<th>Title</th>
					<td><input type="text" name="title" required></td>
				<tr>
					<th>Wrtier</th>
					<td><input type="text" name="writer" required></td>
				</tr>
				<tr>
					<th>Content</th>
					<td><textarea name ="content" rows="15" cols="80"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" name="Register">
					</td>
				</tr>
			</table>
			<a href="getBoardList?currPage=${param.currPage == ''? 1:param.currPage }">Back To List</a>
		</form>
	</div>

</body>
</html>