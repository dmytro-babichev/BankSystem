<%-- 
    Document   : admin_room
    Created on : 20.08.2012, 14:25:53
    Author     : laory
--%>

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
        String message = (String) session.getAttribute("message");
        pageContext.setAttribute("message", message);
    %>

    <body><center><table id="main" cellspacing="0">
            <tr><td colspan="6" align="center">
                    <table id="mytable" cellspacing="0" class="test">
                        <caption><c:out value="${name}"/>, please enter the account</caption>
                        <tr>
                            <th scope="col">Account</th>
                        </tr>
                        <tr>
                            <td scope="row" class="spec">
                                <form method="post" action="general_controller">
                                    <input type="text" name="found_account"/>
                                    <input type="hidden" name="command" value="find_account"/>
                                    <input type="submit" value="Find account"/>
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <c:out value="${message}"/>
                            </td>
                        </tr>

                    </table>
                </td>
            </tr>
            
        </table>
    </center>
</body>
</html>
