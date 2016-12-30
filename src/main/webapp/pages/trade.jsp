<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: omsk16
  Date: 12/29/2016
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <title>Trade</title>
</head>
<body>
<p>Ваш логин: <sec:authentication property="principal.username" /></p>
<p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
<p><b>User accounts:</b></p>
<c:forEach var="account" items="${accounts}">
    <table >
        <tr>
            <th>Amount</th>
            <th>Currency</th>
        </tr>
    <tr>
        <td ><c:out value="${account.balance.amount}"/></td>
        <td><c:out value="${account.balance.currencyUnit}"/></td>
    </tr>
    </table>
</c:forEach>

<p>${rurtousd}</p>
</body>
</html>