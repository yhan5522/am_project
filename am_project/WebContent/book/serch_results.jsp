<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>검색결과조회</title>
	<link rel="stylesheet" href="serch_results.css" type="text/css" media="screen" />
	<script type="text/javascript" src="book_list.js"></script>
</head>
<body>
	<header id="main_header">
		<div id="title">
			<img src="Img/logo_1.png" style="float:left">
			<a href="main.jsp"><span>인천광역시</span>
			<span>미추홀도서관</span></a>
			<span>Incheon Metropolitan City Michuhol Library</span>
		</div>
		<nav id="main_gnb">
			<ul>
				<li><a href="book_list.jsp">관리자페이지</a></li>
			</ul>
		</nav>
		<nav id="main_lnb">
			<ul>
				<li class="menu"><a href="#">자료 검색</a>
					<div class="article">
						<a href="#">통합 검색</a>
						<a href="#">추천 도서</a>
					</div>
				</li>
        	    <li class="menu"><a href="#">이용 안내</a>
					<div class="article">
						<a href="#">도서관 위치</a>
						<a href="#">도서관 안내</a>
						<a href="#">열람실 안내</a>
					</div>
				</li>
           		<li><a href="#">전자 도서관</a></li>
         		<li class="menu"><a href="#">도서관 행사</a>
					<div class="article">
						<a href="#">문화 행사</a>
						<a href="#">영화 상영</a>
					</div>
				</li>
        	</ul>
		</nav>
		<nav id="route">
	    	<ul>
				<a href="main.jsp"><li>HOME</li></a>
				<li> ▶ </li>
	            <li>자료 검색</li>
	            <li> ▶ </li>
	            <li style="font-weight: bold;">통합 검색</li>
			</ul>
		</nav>
	</header>
	<div id="content">
		<aside id="main_aside">
        	<article id="sub_menu">
				<ul>
					<li>자료 검색</li>
				</ul>
				<ul>
					<li style="font-weight: bold;">통합 검색</li><hr>
					<a href="#"><li>추천 도서</li></a><hr>
				</ul>
            </article>
		</aside>
		<section id="main_section">
	        <article id="main_article">
				<ul id="article_title">
					<li>통합 검색</li>
				</ul>
				<hr>
				<form name="form1" method="post" action="BooksController">
					<input type="hidden" name="action" value="list">
					<input type="hidden" name="bookNumber" value=0>
					<input type="text" id="searchBookName" align="left " name="searchBookName" value=${bookDTO.searchBookName}>
	    			<input type="button" id="list" align=right value="조회" onClick="retrivalcheck()">
					<table>
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
			</article>
		</section>
	</div>
	<footer id="main_footer">
		<hr>
		<a href="#"><h4>개인정보처리방침</h4></a>
	    |
	    <a href="#"><h4>이메일무단수집거부정책</h4></a>
	    |
	    <a href="#"><h4>도서관서비스현장</h4></a>
	    <address>(21588) 인천광역시 남동구 인주대로776번길 53 (구월동 610-4)</address>
	</footer>
</body>
</html>