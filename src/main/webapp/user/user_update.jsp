
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/Header.jsp" %>
	<div align="center" class="div_center">
	<form action="updateForm.user" method="post" >
		<h3>회원정보 수정 페이지</h3>
		<hr>
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id" value="${requestScope.user_id}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
					<td>
						<input type="password" name="pw" placeholder="비밀번호는 5자 이상 입력" required>
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input type="password" name="pw_check" placeholder="비밀번호는 5자 이상 입력" required>
					</td>
				</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" value="${requestScope.user_name}">
				</td>
				
			</tr>

			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email" value="${requestScope.user_email}">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="address" value="${requestScope.user_address}">
				</td>
			</tr>
			
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" ${requestScope.user_gender == 'M' ? 'checked' : ''} value="M">남자
					<input type="radio" name="gender" ${requestScope.user_gender == 'F' ? 'checked' : ''} value="F">여자
				</td>
			</tr>		
		</table>
		<br>
		
		<input type="submit" value="수정" class="btn btn-default">&nbsp;&nbsp;
		<input type="button" value="취소" class="btn btn-default" onclick="">    
		
	</form>	
	</div>



<%@ include file="../include/Footer.jsp"%>