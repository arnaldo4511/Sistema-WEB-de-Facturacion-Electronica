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
        <script src="<%= request.getContextPath()%>/js/ventas/reportes/venta-concar-mensual.js" type="text/javascript"></script>
        <title>Transportes :: Reportes</title>
    </head>
    <body ng-app='ventaConcarMensualApp' ng-controller='ventaConcarMensualController'>
        <div class="container-fluid">
            <div class='row clearfix'>
                <aside>
                    <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
                </aside>
            </div>
            <div class='row clearfix'>
                <div class="col-sm-12">
                    <section>
                        <form>
                            <h1 class="text-center">VENTAS CONCAR</h1>
                            <fieldset class="scheduler-border">
                                <div class="row clearfix">
                                    <div class="form-group col-sm-2">
                                        <label >Año</label>
                                        <select class="form-control"
                                                ng-model="anhio" 
                                                ng-options="anhio.codigo for anhio in anhios track by anhio.codigo"
                                                ng-change="generarUrl()">
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Mes</label>
                                        <select class="form-control"
                                                ng-model="mes" 
                                                ng-options="mes.nombre for mes in meses track by mes.codigo"
                                                ng-change="generarUrl()">
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label>Inicio Dia</label>
                                        <input type="number"
                                               class="form-control"
                                               ng-model="diaInicio"
                                               ng-change="generarUrl()">
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label>Inicio Fin</label>
                                        <input type="number"
                                               class="form-control"
                                               ng-model="diaFin"
                                               ng-change="generarUrl()">
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label>Inicio Correlativo</label>
                                        <input type="number"
                                               class="form-control"
                                               ng-model="inicioCorrelativo"
                                               ng-change="generarUrl()">
                                    </div>
                                    <div class="form-group col-sm-1">
                                        <label>Xls</label><br>
                                        <button class="btn btn-default">
                                            <a class="glyphicon glyphicon-save-file" href="{{url}}" 
                                               download="{{url}}">
                                            </a>
                                        </button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </section>
                </div>
            </div>
            <div class='row clearfix'>
                <div class="col-sm-12">
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
                    </aside>
                </div>
            </div>
        </div>
    </body>
</html>