<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>도서목록 등록</title>
	
	<link rel="stylesheet" href="book.css" type="text/css" media="screen" />
	
    <script type="text/javascript" src="book_view.js"></script>
	

</head>

<body>

<div align="center">
	<H2>도서 목록 등록</H2>

	<!-- 계좌이체 등록용 -->
	<form name="form1" method="post" action="BookController">
	
		<input type="hidden" name="action" value=${action}>
		<input type="hidden" name="bookNumber" value=${bookDTO.bookNumber}>
		
		<table>
			<tr>
				<th>도서명</th>
				<td><input type=text size=30 name=bookName value=${bookDTO.bookName}></td>
			</tr>
			<tr>
				<th>저자</th>
				<td><input type=text size=30 name=author value=${bookDTO.author}></td>
			</tr>
			<tr>
				<th>출판사</th>
				<td><input type=text size=30 name=publishingHouse value=${bookDTO.publishingHouse}></td>
			</tr>
			<tr>
				<th>분류</th>
				<td><input type=text size=30 name=category value=${bookDTO.category}></td>
			</tr>
			<tr>
				<th>청구기호</th>
				<td><input type=text size=30 name=symbol value=${bookDTO.symbol}></td>
			</tr>
			<tr>
				<th>상태</th>
				<td><input type=text size=30 name=status value=${bookDTO.status}></td>
			</tr>
			<tr>
				<td colspan=2 align=center>
					<input type="button" id="insert" value="입력" onClick="insertcheck()">
					<input type="button" id="update" value="수정" onClick="updatecheck()">
					<input type="button" id="delete" value="삭제" onClick="deletecheck()">
					<input type="button" id="back" value="돌아가기" onClick="backcheck()">
				</td>
			</tr>
		</table>
	</form>
	</div>


</body>
</html>