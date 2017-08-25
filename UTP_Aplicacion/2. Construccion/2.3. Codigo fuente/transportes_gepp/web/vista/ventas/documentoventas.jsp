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
        <script src="<%= request.getContextPath()%>/js/ventas/documentoventas.js" type="text/javascript"></script>
        <title>Transportes :: Rol</title>
        <style>
            .selected {
                background-color: #4cae4c;
            }
        </style>
    </head>
    <body ng-app='DocumentoVentasApp' ng-controller='DocumentoVentasController'>
        <div class="container-fluid">
            <div class='row clearfix'>
                <aside>
                    <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
                </aside>
            </div>
            <div class='row clearfix'>
                <div class="col-sm-3">
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                    </aside>
                </div>
                <div class="col-sm-9">
                    <section>
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border">Fecha de Emisión</legend>
                            <div class="row clearfix">
                                <div class="col-sm-3">
                                    <label>Desde:</label><input type="date" ng-model="fechaDesde" date-format class="form-control" ng-enter="listar()">
                                </div>
                                <div class="col-sm-3">
                                    <label>Desde:</label><input type="date" ng-model="fechaHasta" date-format class="form-control">
                                </div>
                                <div class="col-sm-3">
                                </div>
                                <div class="col-sm-3">
                                    <div class="pull-right">
                                        <button class="btn btn-default btn-lg"><span class="glyphicon glyphicon-erase"></span></button>
                                        <button class="btn btn-default btn-lg" ng-click="listar()"><span class="glyphicon glyphicon-th-list"></span></button>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border">Totales</legend>
                            <div class="row clearfix">
                                <div class="col-sm-4">
                                    <label>T. Facturas Electrónicas</label><br>
                                    <label>T. Boletas de Venta Electrónicas</label>
                                </div>
                                <div class="col-sm-2">
                                    <label>S./{{}}</label><br>
                                    <label>S./{{}}</label>
                                </div>
                                <div class="col-sm-4">
                                    <label>T. Notas de Crédito Electrónicas</label><br>
                                    <label>T. Notas de Débito Electrónicas</label>
                                </div>
                                <div class="col-sm-2">
                                    <label>S./{{}}</label><br>
                                    <label>S./{{}}</label>
                                </div>
                            </div>
                        </fieldset>
                        <div class="row clearfix">
                            <div class="col-sm-12">
                                <div class="pull-right">
                                    <button class="btn btn-default btn-lg"><span class="glyphicon glyphicon-save-file"></span></button>
                                    <button class="btn btn-default btn-lg"><span class="glyphicon glyphicon-scissors"> Anular</span></button>
                                    <button class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"> Notificar</span></button>
                                    <button class="btn btn-default btn-lg"><span class="glyphicon glyphicon-duplicate"> Hacer Nota de Crédito</span></button>
                                    <button class="btn btn-default btn-lg"><span class="glyphicon glyphicon-duplicate"> Hacer Nota de Débito</span></button>
                                    <button class="btn btn-default btn-lg" ng-click="crear()"><span class="glyphicon glyphicon glyphicon-plus"> Crear</span></button>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-sm-12">
                                <table class="table table-responsive">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>
                                                Tipo<br>

                                                <select class="form-control"
                                                        ng-init="tipoDocumentoVentas[0].value" ng-model="tipoDocumentoVenta" ng-options="tipoDocumentoVenta.nombre for tipoDocumentoVenta in tipoDocumentoVentas track by tipoDocumentoVenta.codigo"
                                                        ng-change="listar()">
                                                </select>
                                            </th>
                                            <th>Estado</th>
                                            <th>Serie</th>
                                            <th>Numero<br>
                                                <input class="form-control" ng-model="numero" ng-enter="listar()">
                                            </th>
                                            <th>Refs.</th>
                                            <th>Fecha</th>
                                            <th>Cliente</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="item in documentoVentas" ng-click="setSelected(item)" class="{{selected}}">
                                            <td>{{$index + 1}}</td>
                                            <td>{{item.tipoDocumentoVenta.nombre}}</td>
                                            <td>{{item.estadoDocumentoVenta.nombre}}</td>
                                            <td>{{item.puntoVentaSerie.codigo}}</td>
                                            <td>{{item.numero}}</td>
                                            <td></td>
                                            <td>{{item.fechaEmision}}</td>
                                            <td>{{item.cliente.entidad.documento + " " + item.cliente.entidad.nombre}}</td>
                                            <td><div class="pull-right">S/.{{item.total| number:2}}</div></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-sm-4">
                                <ul  uib-pagination boundary-links="true" total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPage" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"/>
                            </div>
                            <div class="col-sm-3">
                            </div>
                            <div class="col-sm-4">
                                <div class="pull-right">
                                    Ver <select style="width: auto;" class="feature-icon form-control"  ng-options="pagina.value for pagina in paginas track by pagina.value"  ng-model="pagina" ng-change="setItemsPerPage(pagina.value)" ng-init="paginas[0].value">
                                    </select> de <b>{{totalItems}}</b> Registro(s)
                                </div>
                            </div>
                        </div>
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
