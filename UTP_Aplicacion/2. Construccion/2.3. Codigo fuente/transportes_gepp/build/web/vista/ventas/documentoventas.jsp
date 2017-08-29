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
        <title>Transportes :: Ventas</title>
        <style>
            .active{
                border:2px solid;
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
                                    <button class="btn btn-default btn-lg" ng-click="descargarDocumentoVenta()"><span class="glyphicon glyphicon-save-file"></span></button>
                                    <button class="btn btn-default btn-lg"><span class="glyphicon glyphicon-scissors"> Anular</span></button>
                                    <button class="btn btn-default btn-lg" ng-click="nuevaNotificacion()" ng-disabled="documentoVentaSeleccionado==={}"><span class="glyphicon glyphicon-envelope" data-toggle="modal" data-target="#modalNotificacion"> Notificar</span></button>
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
                                        <tr ng-repeat="item in documentoVentas" ng-click="setSelected($index, item)" ng-class="{active:$index == selectedRow}">
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
                    <section>

                        <!-- Modal -->
                        <div class="modal fade" id="modalNotificacion" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Notificación</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formNotificacion">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Tipo:</label>
                                                <div class="col-sm-9">
                                                    <select class="form-control" name="tipoNotificacion"  ng-model="tipoNotificacion" ng-options="tipoNotificacion.nombre for tipoNotificacion in tipoNotificacions track by tipoNotificacion.codigo"  required>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Email:</label>
                                                <div class="col-sm-9" ng-class="{ 'has-error' : formNotificacion.notificacion.email.$invalid && !formNotificacion.notificacion.email.$pristine }">
                                                    <input type="email" name="notificacion.email"  ng-model="notificacion.email"  class="form-control" ng-disabled="tipoNotificacion.codigo === 'CLI'" ng-required="tipoNotificacion.codigo === 'PER'"/>
                                                    <p ng-show="formNotificacion.notificacion.email.$invalid && !formNotificacion.notificacion.email.$pristine" class="help-block">Introduzca un correo electrónico válido.</p>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="notificar(documentoVenta)" <!--ng-disabled="formNotificacion.$invalid"-->>Notificar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="modalAnulacion" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Anulación</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formNotificacion">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Tipo:</label>
                                                <div class="col-sm-9">
                                                    <select class="form-control" name="tipoNotificacion"  ng-model="tipoNotificacion" ng-options="tipoNotificacion.nombre for tipoNotificacion in tipoNotificacions track by tipoNotificacion.codigo"  required>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Motivo:</label>
                                                <div class="col-sm-9" ng-class="{ 'has-error' : formNotificacion.email.$invalid && !formNotificacion.email.$pristine }">
                                                    <textarea class="form-control"></textarea>
                                                    <input type="email" name="email"  ng-model="email"  class="form-control" ng-disabled="tipoNotificacion.codigo === 'CLI'" ng-required="tipoNotificacion.codigo === 'PER'"/>
                                                    <p ng-show="formNotificacion.email.$invalid && !formNotificacion.email.$pristine" class="help-block">Introduzca un correo electrónico válido.</p>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="notificar(documentoVenta)" ng-disabled="formNotificacion.$invalid">Notificar</button>
                                    </div>
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
