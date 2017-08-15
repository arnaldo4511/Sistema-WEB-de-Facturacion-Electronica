<%-- 
    Document   : rol
    Created on : 14-ago-2017, 17:57:15
    Author     : octavio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/imports.jspf" %>
        <script src="<%= request.getContextPath()%>/js/administracion/rol.js" type="text/javascript"></script>
        <title>Transportes :: Rol</title>
    </head>
    <body ng-app='RolApp' ng-controller='RolController'>
        <div class="container">
            <div class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
            </div>
            <div class='row'>
                <div class="col-sm-3">
                    <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                </div>
                <div class="col-sm-9">
                    <form>
                        <fieldset>
                            <legend>ROLES</legend>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>Administrador</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="rol in roles">
                                        <td>{{$index + 1}}</td>
                                        <td>{{rol.username}}</td>
                                        <td>{{rol.username}}</td>
                                        <td>{{rol.username}}</td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                        </fieldset>
                    </form>
                </div>
            </div>
            <div class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
            </div>
        </div>
    </body>
</html>
