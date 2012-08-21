<%-- 
    Document   : recharge
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
        Account account = (Account) session.getAttribute("account");
        pageContext.setAttribute("account", account);
    %>

    <body><center><table id="main" cellspacing="0">
            <tr><td colspan="6" align="center">
                    <table id="mytable" cellspacing="0" class="test">
                        <caption><c:out value="${name}"/>, please enter sum to recharge</caption>
                        <tr>
                            <th scope="col">Account</th>
                            <th scope="col">Balance</th>
                            <th scope="col">Exchange</th>
                            <th scope="col">Sum to recharge</th>
                        </tr>
                        <tr>
                            <td><c:out value="${account.id}"/></td>
                            <td><c:out value="${account.cash}"/></td>
                            <td><c:out value="${account.exchange}"/></td>
                            <td scope="row" class="spec">
                                <form method="post" action="general_controller">
                                    <input type="text" name="recharge"/>
                                    <input type="hidden" name="command" value="recharge"/>
                                    <input type="submit" value="Recharge"/>
                                </form>
                            </td>
                        </tr>
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