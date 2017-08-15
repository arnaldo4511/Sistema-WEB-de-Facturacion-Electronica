<%-- 
    Document   : ingreso
    Created on : 14-ago-2017, 14:10:30
    Author     : octavio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/imports.jspf" %>
        <script src="<%= request.getContextPath()%>/js/ingreso.js" type="text/javascript"></script>
        <title>Transportes :: Ingreso</title>
    </head>
    <body ng-app='IngresoApp' ng-controller='IngresoController'>
        <div class='container'>
            <div class='row'>
                <div class="col-sm-3 pull-right">
                    <form class="form-horizontal" role="form" name="ingresoForm">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Usuario</label>
                            <input type="text" class="form-control" ng-model="nombre" >
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Contraseña</label>
                            <input type="password" class="form-control" ng-model="clave">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-secondary" ng-click="ingresarSistema()">Ingresar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
