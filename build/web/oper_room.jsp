<%-- 
    Document   : oper_room
    Created on : 26.07.2012, 1:16:00
    Author     : laory
--%>
<%@page import="models.Card"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tlds/add_buttons.tld" prefix="addbuttons" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Bank</title>
        <style type="text/css">
            <!--
            @import url("CSS/operations.css");
            -->
        </style>
    </head>
    <%
        String user = (String) session.getAttribute("name");
        pageContext.setAttribute("name", user);
        Vector<Card> cards = (Vector<Card>) session.getAttribute("cards");
        pageContext.setAttribute("cards", cards);
    %>

    <body>
    <center>
        <table id="main" cellspacing="0">
            <tr><td colspan="6" align="center">
                    <table id="mytable" cellspacing="0" class="test">
                        <caption><c:out value="${name}"/>, please select one of your accounts</caption>
                        <tr>
                            <th scope="col" class="nobg">Accounts</th>
                            <th scope="col" >Card SN</th>
                            <th scope="col" >Balance</th>
                            <th scope="col" >Exchange</th>
                            <th scope="col" >Status</th>
                        </tr>
                        <c:forEach items="${cards}" var="card">
                            <tr>
                                <th scope="row" class="
                                    <c:choose>
                                        <c:when test="${card.account.status == true}">spec</c:when>
                                        <c:otherwise>specalt</c:otherwise>
                                    </c:choose>
                                    ">
                                    <a href="general_controller?command=open_account&account=<c:out 
                                           value="${card.account.id}"/>">
                                       <c:out value="${card.account.id}"/>
                                    </a>
                                </th>
                                <td><c:out value="${card.serialNumber}"/></td>
                                <td><c:out value="${card.account.cash}"/></td>
                                <td><c:out value="${card.account.exchange}"/></td>
                                <td><c:choose>
                                        <c:when test="${card.account.status == true}">active</c:when>
                                        <c:otherwise>not active</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </td>
            </tr>
            
        </table>   
    </center>
</body>
</html>
