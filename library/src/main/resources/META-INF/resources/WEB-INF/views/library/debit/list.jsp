<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 引入JSTL里面的core标签库，它里面包含了forEach、if、choose等标签，代替JSP的脚本和表达式。 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>图书馆首页</title>
		<link rel="stylesheet" type="text/css" href="../css/book.css" />
	</head>
	<body>
		<div id="box">
			<header>已借图书列表</header>
			<div class="search bar7">
				<form action="" method="get">
					<%-- ${} 这种叫做EL表达式 --%>
					<%-- 大括号里面可以写各种表达式，包括数学运算 --%>
					<%-- param是EL表达式的内置对象，表示请求参数 --%>
					<%-- param.keyword 表示获取请求参数里面名为keyword的参数的值 --%>
					<input type="text" placeholder="请输入关键字" name="keyword2" value="${param.keyword2 }">
					<button type="submit">搜索</button>
				</form>
			</div>
			<%-- 循环生成图书的列表 --%>
			<%-- 	<%
			Page<Book> page = (Page<Book>) request.getAttribute("page");
			List<Book> content = page.getContent();
			for( Book book : content ){
			%>
			<div>
				<%=book.getName() %>
			</div>
			<%} %> --%>

			<%-- 使用JSTL的forEach标签实现循环输出 --%>
			<%-- items可以处理任意数组、集合，甚至Map --%>
			<%-- ${page.content }是EL表达式，也是JSP里面非常常用的一种语法。 --%>
			<section class="main">
				<div class="book-box">
					<c:forEach items="${debitList.books }" var="book">
					<div class="book">
						<div class="book-img">
							<img src="../images/${book.image }">
						</div>
						<div class="book-right">
							<div class="book-text">
								<p class="col-10 name">${book.name }</p>
							</div>
							<a href="/zzr_1/library/debit/remove/${book.id }">
								<div class="col-1">
									删除
								</div>
							</a>
						</div>
					</div>
					</c:forEach>

				</div>

			</section>
			<footer>FOOTER</footer>
		</div>
	</body>
</html>
