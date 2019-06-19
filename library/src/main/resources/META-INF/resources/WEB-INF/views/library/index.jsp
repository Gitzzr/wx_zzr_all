<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 引入JSTL里面的core标签库，它里面包含了forEach、if、choose等标签，代替JSP的脚本和表达式。 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书馆首页</title>
<link href="/zzr_1/library/css/main.css" rel="stylesheet" />
</head>
<body>
	<form action="" method="get">
		<%-- ${} 这种叫做EL表达式 --%>
		<%-- 大括号里面可以写各种表达式，包括数学运算 --%>
		<%-- param是EL表达式的内置对象，表示请求参数 --%>
		<%-- param.keyword 表示获取请求参数里面名为keyword的参数的值 --%>
		<input name="keyword" value="${param.keyword }" placeholder="关键字" />
		<button>搜索</button>
	</form>

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
	<c:forEach items="${page.content }" var="book">
		<div class="book">
			<div class="col-1">
				<img class="image" src="/zzr_1/library/images/${book.image }" />
			</div>
			<span class="col-10 name">${book.name }</span>
			<span class="col-1" style="text-align: right;">
				<span class="button">+</span>
			</span>
		</div>
	</c:forEach>

	<%-- 分页按钮 --%>
	<div>
		<c:if test="${page.number > 0 }">
			<a href="?pageNumber=${page.number -1 }&keyword=${param.keyword}">上一页</a>
		</c:if>
		<c:if test="${page.number <= 0 }">
			<a>上一页</a>
		</c:if>
		<c:choose>
			<%-- 为什么要减一？因为页码从0开始，但是totalPages是总页数，从1开始。 --%>
			<c:when test="${page.number >= page.totalPages - 1 }">
				<a>下一页</a>
			</c:when>
			<c:otherwise>
				<a href="?pageNumber=${page.number + 1 }&keyword=${param.keyword}">下一页</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>