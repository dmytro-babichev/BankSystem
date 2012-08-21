<%-- 
    Document   : index
    Created on : 23.07.2012, 21:17:04
    Author     : laory
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Super Bank</title>
    </head>
    <style type="text/css">
        <!--
        @import url("CSS/style.css");
        -->
    </style>
</head>
<body>
<center><br><br><br><br><br>
    <table id="gradient-style" summary="Meeting Results">
        <form method="post" name="auth_form" action="general_controller">
            <thead>
                <tr>
                    <th scope="col" colspan="2">Login please</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <td colspan="2">
                        <%
                            String message = (String) request.getAttribute("message");
                            pageContext.setAttribute("message", message);
                        %>
                        <c:out value="${message}" default=""/>
                    </td>
                </tr>
            </tfoot>
            <tbody>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login"/></td>
                <tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                <tr>
                <tr align="center">
                    <td colspan="2"><input type="submit" value="Ok"/></td>
                </tr>
            <input type="hidden" name="command" value="get_accounts"/>
            </tbody>
        </form>
    </table>
</center>
</body>
</html>
