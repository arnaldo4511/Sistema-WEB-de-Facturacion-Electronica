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
        <link href="<%= request.getContextPath()%>/js/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        

        
        <title>Transportes :: Inicio</title>
        <script src="<%= request.getContextPath()%>/js/principal.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/js/custom.min.js" type="text/javascript"></script>
        <link href="<%= request.getContextPath()%>/css/custom.min.css" rel="stylesheet">
    </head>
    <body ng-app='PrincipalApp' ng-controller='PrincipalController'>
        <div class="container">
            <div class='row bg-success'>
                <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
            </div>
            <div class='row'>
                <div class="col-sm-3 left_col">
                    <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                </div>
                <div class="col-sm-9">
                </div>
            </div>
            <div class='row bg-success'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
            </div>
        </div>
    </body>
</html>
