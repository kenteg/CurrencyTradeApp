<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kente
  Date: 31.12.2016
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TradeForm</title>
</head>
<body>
<p>Current buying currency: <c:out value="${selected_cur}"/> </p>
<form action="trade_form" method="post">
    <input type="hidden" name="currentbuy" value="${selected_cur}">
    <input type="text" name="amount">
    <select name="selectPayCur">
        <c:forEach var="cur" items="${currencies}">
        <option value="${cur}">${cur}</option>
        </c:forEach>
    </select>
    <input type="submit" name="sb" value="trade">
</form>
</body>
</html>
