<%-- 
    Document   : ingreso
    Created on : 14-ago-2017, 14:10:30
    Author     : octavio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/imports.jspf" %>
        <script src="<%= request.getContextPath()%>/js/ingreso.js" type="text/javascript"></script>
        <link href="<c:url value="/css/ingreso.css" />" rel="stylesheet">
        <title>Transportes :: Ingreso</title>
    </head>
<<<<<<< HEAD
    <body ng-app='IngresoApp' ng-controller='IngresoController'>
        <div class='container-fluid'>
            <div class='row clearfix'>
                    <form class="login-form" role="login" name="ingresoForm">
                        <img src="<c:url value="/images/gepp_transmap_logo.png" />" class="img-responsive" alt="" style="height: 121px;"/>
                        <input type="text" name="nombre" placeholder="Usuario" required class="form-control input-lg" ng-model="nombre"/>
                        <input type="password" name="clave" placeholder="Contraseña" required class="form-control input-lg" ng-model="clave"/>
                        <button type="submit" class="btn btn-lg btn-success btn-block" ng-click="ingresarSistema()">Ingresar</button>
                    </form>
            </div>
            <div id="rowPie" class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
            </div>

        </div>
=======
    <body ng-app='IngresoApp' ng-controller='IngresoController' style="background: url(<c:url value="/images/fondo.png" />) no-repeat center center fixed;">
        <section class="container">
            <section class="login-form">
                <form class="form-horizontal" role="login" name="ingresoForm">
                    <img src="<c:url value="/images/gepp_transmap_logo.png" />" class="img-responsive" alt="" style="
                         height: 121px;
                         "/>
                    <input type="text" name="nombre" placeholder="Usuario" required class="form-control input-lg" ng-model="nombre"/>
                    <input type="password" name="clave" placeholder="Contraseña" required class="form-control input-lg" ng-model="clave"/>
                    <button type="submit" class="btn btn-lg btn-primary btn-block" ng-click="ingresarSistema()">Ingresar</button>

                </form>
                <div class="form-links">
                    <a href="http://www.gepp.pe/" style="
                       color: #84be4d;
                       ">http://www.gepp.pe/</a>
                </div>
            </section>
        </section>

        
>>>>>>> master
    </body>
</html>
