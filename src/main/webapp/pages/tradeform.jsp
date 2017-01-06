<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kente
  Date: 31.12.2016
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
<link href="<c:url value="/pages/css/bootstrap-select.css" />" rel="stylesheet">
<link href="<c:url value="/pages/css/bootstrap-select.css.map" />" rel="stylesheet">
<link href="<c:url value="/pages/css/bootstrap-select.min.css" />" rel="stylesheet">
<script src="pages/js/sendForm.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TradeForm</title>



</head>
<body>
<p>Current buying currency: <c:out value="${selected_cur}"/> </p>
<form action="trade/buy" name="tradeform" method="post" >
    <input type="hidden" name="currentbuy" value="<c:out value="${selected_cur}"/>">
    <input type="text" class="form-control" name="amount">
    <select class="bootstrap-select" name="selectPayCur">
        <c:forEach var="cur" items="${currencies}">
        <option value="${cur}">${cur}</option>
        </c:forEach>
    </select>
    <input id="buy-submit" type="submit" class="btn btn-default" name="sb" value="trade">
</form>
</body>
</html>
