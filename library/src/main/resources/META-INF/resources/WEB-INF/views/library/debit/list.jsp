<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 引入JSTL里面的core标签库，它里面包含了forEach、if、choose等标签，代替JSP的脚本和表达式。 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>借阅列表</title>
<link href="/zzr_1/library/css/main.css" rel="stylesheet" />
</head>
<body>
	<c:forEach items="${debitList.books }" var="book">
		<div>
			${book.name }
			<%-- 此处把路径的一部分作为参数来使用，这才符合REST规范 --%>
			<a href="/zzr_1/library/debit/remove/${book.id }">删除</a>
		</div>
	</c:forEach>
</body>
</html>