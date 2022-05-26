<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 5/26/2022
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<h1>List of customer</h1>
<a href="/customers?action=create">Create customer</a>
<c:forEach items="${dskh}" var="cus">
    <h2>${cus.id} , ${cus.name} , ${cus.age},
        <a href="/customers?action=edit&id=${cus.id}">Edit</a>
        <a href="/customers?action=delete&id=${cus.id}">Delete</a>

    </h2>

</c:forEach>
</body>
</html>
