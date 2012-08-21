<%-- 
    Document   : transaction
    Created on : 26.07.2012, 1:16:00
    Author     : laory
--%>
<%@page import="models.Transaction"%>
<%@page import="models.Account"%>
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
        String message = (String) session.getAttribute("message");
        pageContext.setAttribute("message", message);
        Vector<Transaction> transactions = (Vector<Transaction>) request.getAttribute("transactions");
        pageContext.setAttribute("transactions", transactions);
        Account account = ((Account) session.getAttribute("account"));
        pageContext.setAttribute("account", account);
    %>

    <body><center><table id="main" cellspacing="0">
            <tr><td colspan="6" align="center">
                    <table id="mytable2" cellspacing="0" class="test">
                        <caption><c:out value="${name}"/>, transactions connected with your account</caption>
                        <tr>
                            <th scope="col" >Date</th>
                            <th scope="col" >Payer</th>
                            <th scope="col" >Remittee</th>
                            <th scope="col" >Amount of payment</th>
                            <th scope="col" >Exchange</th>
                        </tr>
                        <c:forEach items="${transactions}" var="transaction">
                            <tr>
                                <td><c:out value="${transaction.date}"/></td>
                                <td><c:out value="${transaction.payer.id}"/></td>
                                <td><c:out value="${transaction.remittee.id}"/></td>
                                <td><c:out value="${transaction.amountOfPayment}"/></td>
                                <td><c:out value="${transaction.exchange}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6">
                                <c:out value="${message}"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <addbuttons:add account="${account.id}"></addbuttons:add>
        </table>

    </center>
</body>
</html>