
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>idFind.jsp</title>
</head>
<body>
<c:if test="${idFail == null}" >
${dto.memName}님의 아이디는 ${dto.memId} 입니다. <br/>
</c:if>

<c:if test="${idFail != null}" >
${idFail}
</c:if>

<a href="main.sm"> 메인으로 </a>
</body>
</html>
