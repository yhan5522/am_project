<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>���������ȸ</title>
	<link rel="stylesheet" href="book.css" type="text/css" media="screen" />
	<script type="text/javascript" src="book_list.js"></script>
</head>
<body>
<div align=center>
	<a href="main.jsp">����������</a>
	<H2>���� ��� ��ȸ</H2>

	<form name="form1" method="post" action="BookController">
		<input type="hidden" name="action" value="list">
		<input type="hidden" name="bookNumber" value=0>
	
		<table border=1>
			<tr>
				<td colspan=7 align=right id="table_title">
        			<span>������</span>
    				<input type="text" align="left " name="searchBookName" value=${bookDTO.searchBookName}>
    				<input type="button" id="list" align=right value="�˻�" onClick="retrivalcheck()">
    				<input type="button" align=right value="�߰�" onClick="addcheck()">
				</td>
			</tr>
			<tr>
				<th>��ȣ</th>
				<th>������</th>
				<th>����</th>
				<th>���ǻ�</th>
				<th>�з�</th>
				<th>û����ȣ</th>
				<th>����</th>
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