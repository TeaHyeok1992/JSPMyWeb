<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center" class="div_center">
	<h3>게시판 글 수정 페이지</h3>
	<hr>
	
	<form action="update.board" method="post">
		
		<table border="1" width="500">
			
			<tr>
				<td>글 번호</td>
				<td><input type="hidden" name="bno" value="${requestScope.vo.bno}"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" value="${requestScope.vo.writer}" readonly></td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td>
					<input type="text" name="title" value="${requestScope.vo.title}">
				</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>
					<textarea rows="10" style="width: 95%;" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정 하기">&nbsp;&nbsp;
					<input type="button" value="목록" onclick="location.href='list.board';">        
				</td>
			</tr>
			
		</table>
	</form>
	
</div>
</body>
</html>