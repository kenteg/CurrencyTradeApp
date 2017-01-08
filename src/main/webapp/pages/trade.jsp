<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
    <link href="<c:url value="/pages/css/bootstrap-select.css" />" rel="stylesheet">
    <script src="pages/js/bootstrap-select.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <script src="pages/js/createLink.js"></script>
    <title>Trade</title>
</head>
<body>
<h3>Ваш логин: <sec:authentication property="principal.username" /></h3>
<p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
<p><b>User accounts:</b></p>

    <table class="table-bordered">
        <thead>
        <tr>
            <th>Currency</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
        <td><c:out value="${account.balance.currencyUnit}"/></td>
        <td><c:out value="${account.balance.amount}"/></td>
    </tr>
        </c:forEach>
        </tbody>
    </table>

<form onSubmit="createLink(this);return false">
<select class="bootstrap-select" name="selected_cur">
    <c:forEach var="cur" items="${currencies}">
    <option value="${cur}">${cur}</option>
    </c:forEach>
</select>
<input type="submit" class="btn btn-success" name="sb" value="buy">
</form>

<p><c:out value="${exception}"/> </p>

<br>
<p><b>Exchange Rates: </b></p>

    <table class="table">
        <thead>
        <tr>
            <c:forEach var="rate" items="${rates}">
                <c:set var="color" value="${rate.currency1}/${rate.currency2}color" />
            <th bgcolor="<c:out value="${color}"/>"><c:out value="${rate.currency1}/${rate.currency2}"/> </th>
            </c:forEach>
        </tr>
        </thead>

        <tbody>
        <tr>
            <c:forEach var="rate" items="${rates}">
        <td><c:out value="${rate.rate}"/></td>
            </c:forEach>
        </tr>
        </tbody>

    </table>


<table class="table table-bordered">
    <caption class="caption">Operations History(Last 20):</caption>
    <thead>
    <tr>
        <th>#</th>
        <th>Currency Buy</th>
        <th>Currency Sell</th>
        <th>Amount Buy </th>
        <th>Amount Sell</th>
        <th>Rate</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="operation" items="${page.content}">
        <tr>
            <td><c:out value="${operation.id}"/></td>
            <td><c:out value="${operation.currencyBuy}"/></td>
            <td><c:out value="${operation.currencySell}"/></td>
            <td><c:out value="${operation.amountBuy}"/></td>
            <td><c:out value="${operation.amountSell}"/></td>
            <td><c:out value="${operation.rate}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:forEach begin="1" end="${total}" var="i">
    <c:choose>
        <c:when test="${currentpage eq i}">
            <button type="button" onclick="location.href='?page=${i}';"><b>${i}</b></b></button>
        </c:when>
        <c:otherwise>
    <button type="button" onclick="location.href='?page=${i}';">${i}</button>
        </c:otherwise>
    </c:choose>
</c:forEach>

</body>
</html>