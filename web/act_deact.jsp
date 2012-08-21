<%-- 
    Document   : act_deact
    Created on : 20.08.2012, 14:51:07
    Author     : laory
--%>

<%@page import="models.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Super Bank (Administrator)</title>
        <style type="text/css">
            <!--
            @import url("CSS/operations.css");
            -->
        </style>
    </head>
    <%
        String user = (String) session.getAttribute("name");
        pageContext.setAttribute("name", user);
        Account account = (Account) session.getAttribute("found_account");
        pageContext.setAttribute("account", account);
    %>

    <body><center><table id="main" cellspacing="0">
            <tr ><td colspan="5" align="center">
                    <table id="mytable" cellspacing="0" class="test">
                        <caption><c:out value="${name}"/>, you can activate or deactivate chosen account</caption>
                        <tr>
                            <th scope="col" class="nobg">Account</th>
                            <th scope="col" >Status</th>
                        </tr>
                        <tr>
                            <th scope="row" class="
                                <c:choose>
                                    <c:when test="${account.status == true}">spec</c:when>
                                    <c:otherwise>specalt</c:otherwise>
                                </c:choose>
                                ">
                                <c:out value="${account.id}"/>
                            </th>
                            <td><c:choose>
                                    <c:when test="${account.status == true}">active</c:when>
                                    <c:otherwise>not active</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <form method="post" action="general_controller">
                        <input type="hidden" name="command" value="act_deact"/>
                        <input type="submit" value="Activate/deactivate"/>
                    </form>
                </td>
                <td>
                    <form method="post" action="admin_room.jsp">
                        <input type="submit" value="Back to search field"/>
                    </form>
                </td>
            </tr>
        </table>
    </center>
</body>
</html>