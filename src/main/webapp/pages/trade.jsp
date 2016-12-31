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
    <script src="pages/js/createLink.js"></script>
    <title>Trade</title>
</head>
<body>
<p>Ваш логин: <sec:authentication property="principal.username" /></p>
<p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
<p><b>User accounts:</b></p>

    <table class="table-bordered">
        <tr>
            <th>Currency</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="account" items="${accounts}">
    <tr>
        <td><c:out value="${account.balance.currencyUnit}"/></td>
        <td><c:out value="${account.balance.amount}"/></td>
    </tr>
        </c:forEach>
    </table>

<form onSubmit="createLink(this);return false">
<select name="selected_cur">
    <c:forEach var="cur" items="${currencies}">
    <option value="${cur}">${cur}</option>
    </c:forEach>
</select>
<input type="submit" name="sb" value="buy">
</form>


<br>
<p><b>Exchange Rates: </b></p>

    <table class="table">
        <tr>
            <c:forEach var="rate" items="${rates}">
            <th><c:out value="${rate.currency1}/${rate.currency2}"/> </th>
            </c:forEach>
        </tr>

        <tr>
            <c:forEach var="rate" items="${rates}">
        <td><c:out value="${rate.rate}"/></td>
            </c:forEach>
        </tr>


    </table>

</body>
</html>