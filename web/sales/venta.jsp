<%--
  Created by IntelliJ IDEA.
  User: Geunsu
  Date: 2021-06-24
  Time: 오전 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Venta.jsp / 판매현황</title>
</head>
<body>
고객 판매 리스트 <br>
<form  action="venta.vnt" method="post" name="frm" >
    아이디 검색: <input type="text" name="memId"/> <br/>
    <input type = "submit" value="검색" />
</form>
    <table border="1">
        <tr>
            <td>고객아이디</td> <td>고객명</td> <td>상품명</td> <td>수량</td> <td>판매일</td>
            <!-- [ 고객명:member , 상품명:product, 주문수량:purchase, 판매일:purchase_list ] 에 있음 -->
        </tr>


        <c:forEach items="${list}" var="dto">
        <tr>

            <td>
                <c:if test="${dto.memId == null}"> 비회원 </c:if>
                <c:if test="${dto.memId != null}"> ${dto.memId} </c:if>
            </td>

            <td>
                <c:if test="${dto.memName == null}"> 비회원 </c:if>
                <c:if test="${dto.memName != null}"> ${dto.memName} </c:if>
            </td>

            <td>${dto.memId}</td> <td>${dto.memName}</td> <td>${dto.prodName}</td> <td>${dto.purchaseQty}</td> <td>${dto.purchaseDate}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
