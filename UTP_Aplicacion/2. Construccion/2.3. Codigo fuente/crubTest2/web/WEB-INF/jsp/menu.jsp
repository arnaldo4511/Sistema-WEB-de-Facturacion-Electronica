<%-- 
    Document   : menu
    Created on : 13/08/2017, 06:49:36 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${usuarioAttrib}" var="p">
                <tr>
                    <td>${usuarioAttrib.getUsuNombre()}</td>
                    <td>${p.usuNombre}</td>
                    

                    
                    <td>
                        <a href="edit.html?id=${p.usuId}">
                            Edit </a>
                        <a href="remove.html?id=${p.usuId}" onclick="return confirm('Are you sure?')">Remove</a>
                    </td>
                </tr>
            </c:forEach>
    </body>
</html>
