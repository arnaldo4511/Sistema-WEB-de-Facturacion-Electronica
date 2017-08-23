<%-- 
    Document   : inicio
    Created on : 14-ago-2017, 14:10:18
    Author     : octavio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/imports.jspf" %>


        <!-- Font Awesome -->
        <script src="<%= request.getContextPath()%>/js/principal.js" type="text/javascript"></script>


        <title>Transportes :: Inicio</title>

    </head>
    <body ng-app='PrincipalApp' ng-controller='PrincipalController'>
        <div class="container">
            <div class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
            </div>
            <div id="rowMenu" class='row'>
                <div id="colMenu" class="col-sm-3">
                    <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                </div>
                <div class="col-sm-9">
                </div>
            </div>
            <div id="rowPie" class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
            </div>
        </div>
    </body>
</html>
