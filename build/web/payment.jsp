<%-- 
    Document   : payment
    Created on : 26.07.2012, 1:16:00
    Author     : laory
--%>
<%@page import="models.Account"%>
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
        Account remittee = (Account) session.getAttribute("remittee");
        pageContext.setAttribute("remittee", remittee);
        Account account = (Account) session.getAttribute("account");
        pageContext.setAttribute("account", account);
    %>

    <body><center><table id="main" cellspacing="0">
            <tr><td colspan="7" align="center">
                    <table id="mytable" cellspacing="0" class="test">
                        <caption><c:out value="${name}"/>, please make payment</caption>
                        <tr>
                            <th scope="col" class="nobg">Entity</th>
                            <th scope="col" >Accounts</th>
                            <th scope="col" >Your balance</th>
                            <th scope="col" >Exchange</th>
                            <th scope="col" >Amount of payment</th>
                            <th scope="col" >Your password</th>
                            <th scope="col" >Action</th>
                        </tr>
                        <tr>
                            <th scope="row" class="spec">Payer</th>
                            <td>
                                <c:out value="${account.id}"/>
                            </td>
                            <td rowspan="2"><c:out value="${account.cash}"/></td>
                            <td><c:out value="${account.exchange}"/></td>
                            <td rowspan="2">
                                <form method="post" action="general_controller">
                                    <input type="text" name="aop"/>
                                    <input type="hidden" name="command" value="pay"/>
                                </td>
                                <td rowspan="2">
                                    <input type="password" name="password"/>
                                    </td>
                                <td rowspan="2">
                                    <input type="submit" value="Pay"/><br>
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="spec">Remittee</th>
                            <td>
                                <c:out value="${remittee.id}"/>
                            </td>
                            <td><c:out value="${remittee.exchange}"/></td>
                        </tr>
                        <tr>
                            <td colspan="7">
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