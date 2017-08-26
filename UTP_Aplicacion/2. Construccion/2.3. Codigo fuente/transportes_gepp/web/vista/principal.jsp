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
        <script src="<%= request.getContextPath()%>/js/principal.js" type="text/javascript"></script>
        <title>Transportes :: Inicio</title>
    </head>
    <body ng-app='PrincipalApp' ng-controller='PrincipalController'>
        <div class="container-fluid">
            <div class="row clearfix">
                <header>
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
                    </aside>
                </header>
            </div>
            <div class='row clearfix'>
                <div class="col-sm-2">
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                    </aside>
                </div>
                <div class="col-sm-10">
                    <section>
                    </section>
                </div>
            </div>
            <div class='row clearfix'>
                <footer>
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
                    </aside>
                </footer>
            </div>
        </div>
    </body>
</html>
