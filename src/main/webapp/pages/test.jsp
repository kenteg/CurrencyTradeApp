<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kente
  Date: 01.01.2017
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<body onload="refreshAndClose()">
success!

<script type="text/javascript">
    function refreshAndClose(){
        setTimeout(function()
        {
            window.opener.location.reload(false);
            window.close();
            return true;
        },1000);
    }
</script>
</body>
</html>
