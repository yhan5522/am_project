<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>도서목록조회</title>
	<link rel="stylesheet" href="book.css" type="text/css" media="screen" />
	<script type="text/javascript" src="book_list.js"></script>
</head>
<body>
<div align=center>
	<a href="main.jsp">메인페이지</a>
	<H2>도서 목록 조회</H2>

	<!-- 계좌이체 목록 조회폼 -->
	<form name="form1" method="post" action="BookController">
		<input type="hidden" name="action" value="list">
		<input type="hidden" name="bookNumber" value=0>
	
		<table border=1>
			<tr>
				<td colspan=7 align=right id="table_title">
        			<span>도서명</span>
    				<input type="text" align="left " name="searchBookName" value=${bookDTO.searchBookName}>
    				<input type="button" id="list" align=right value="검색" onClick="retrivalcheck()">
    				<input type="button" align=right value="추가" onClick="addcheck()">
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>도서명</th>
				<th>저자</th>
				<th>출판사</th>
				<th>분류</th>
				<th>청구기호</th>
				<th>상태</th>
			</tr>
			<c:forEach var="i" items="${bookList}">
				<tr>
					<td><a href="javascript:editcheck(${i.bookNumber})">${i.bookNumber}</a></td>
					<td>${i.bookName}</td>
					<td>${i.author}</td>
					<td>${i.publishingHouse}</td>
					<td>${i.category}</td>
					<td>${i.symbol}</td>
					<td>${i.status}</td>
				</tr>
			</c:forEach>			
		</table>
	</form>
	</div>
</body>
</html>