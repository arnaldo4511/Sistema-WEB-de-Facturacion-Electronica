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
    </head>
    <body ng-app='DocumentoVentasApp' ng-controller='DocumentoVentasController'>
        <div class="container-fluid">
            <div class='row clearfix'>
                <aside>
                    <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
                </aside>
            </div>
            <section>
                <div class="row clearfix">
                    <div class="col-sm-6">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border">Fecha de Emisión</legend>
                            <div class="row clearfix">
                                <div class="col-sm-4">
                                    <label>Desde:</label><input type="date" ng-model="parametro.fechaDesde"  class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <label>Hasta:</label><input type="date" ng-model="parametro.fechaHasta" class="form-control">
                                </div>
                                <div class="col-sm-4">
                                    <div class="pull-right">
                                        <button class="btn btn-default btn-lg" ng-click="limpiarParametros()"><span class="glyphicon glyphicon-erase"></span></button>
                                        <button class="btn btn-default btn-lg" ng-click="listar()"><span class="glyphicon glyphicon-th-list"></span></button>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                    <div class="col-sm-6">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border">Totales Válidos</legend>
                            <div class="row clearfix">
                                <div class="col-sm-3">
                                    <label>T. Facturas</label><br>
                                    <label>T. Boletas</label>
                                </div>
                                <div class="col-sm-3">
                                    <label>S./{{totalFacturas| number:2}}</label><br>
                                    <label>S./{{totalBoletas| number:2}}</label>
                                </div>
                                <div class="col-sm-3">
                                    <label>T. N. de Crédito</label><br>
                                    <label>T. N. de Débito</label>
                                </div>
                                <div class="col-sm-3">
                                    <label>S./{{totalNotaCreditos| number:2}}</label><br>
                                    <label>S./{{totalNotaDebitos| number:2}}</label>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
                <div class="row clearfix pull-right">
                    <div class="col-sm-12">
                        <button ng-hide="hideButtonEnviarSunat"
                                class="btn btn-default" 
                                ng-click="enviarSunat()"><span class="fa fa-share-square-o"> SUNAT</span></button>

                        <button ng-hide="hideButtonDescargar"
                                class="btn btn-default"
                                ng-click="descargarDocumentoVenta()"><span class="glyphicon glyphicon-save-file"> PDF</span></button>

                        <button ng-hide="hideButtonAnular"
                                class="btn btn-default"
                                ng-click="nuevaAnulacion()"><span class="glyphicon glyphicon-scissors" data-toggle="modal" data-target="#modalAnulacion" > Anular</span></button>

                        <button ng-hide="hideButtonNotificar"
                                class="btn btn-default"
                                ng-click="nuevaNotificacion()" ><span class="glyphicon glyphicon-envelope" data-toggle="modal" data-target="#modalNotificacion"> Notificar</span></button>

                        <button ng-hide="hideButtonHacerNotaCredito"
                                class="btn btn-default"
                                ng-click="hacerNotaCredito()" ><span class="glyphicon glyphicon-duplicate"> Hacer Nota de Crédito</span></button>

                        <button ng-hide="hideButtonHacerNotaDebito"
                                class="btn btn-default"
                                ng-click="hacerNotaDebito()"><span class="glyphicon glyphicon-duplicate"> Hacer Nota de Débito</span></button>

                        <button class="btn btn-default" ng-click="crear()"><span class="glyphicon glyphicon glyphicon-plus"> Crear</span></button>

                        <button
                            ng-class="{'btn btn-danger' : (totalPendienteEnvioSunats > 0),
                                             'btn btn-success' : (totalPendienteEnvioSunats < 1)}"
                            ng-click="listarPendienteEnvioSunat()" ><strong>({{totalPendienteEnvioSunats}})</strong> PENDIENTE ENVIO</button>

                        <button ng-class="{'btn btn-danger' : (totalPendienteComunicacionBajas > 0),
                                             'btn btn-success' : (totalPendienteComunicacionBajas < 1)}"
                                ng-click="listarPendienteComunicacionBaja()"><strong>({{totalPendienteComunicacionBajas}})</strong> PENDIENTE BAJA</button>
                        <button ng-hide="true" ng-click="enviarBloqueClientes()">ENVIAR CLIENTE BLOQUE</button>
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
                                                ng-model="parametro.tipoDocumentoVenta" ng-options="tipoDocumentoVenta.nombre for tipoDocumentoVenta in tipoDocumentoVentas track by tipoDocumentoVenta.codigo"
                                                ng-change="listar()">
                                            <option value="" selected>TODOS</option>
                                        </select>
                                    </th>
                                    <th>Estado<br>
                                        <select class="form-control"
                                                ng-model="parametro.estadoDocumentoVenta" ng-options="estadoDocumentoVenta.nombre for estadoDocumentoVenta in estadoDocumentoVentas track by estadoDocumentoVenta.codigo"
                                                ng-change="listar()">
                                            <option value="" selected>TODOS</option>
                                        </select>
                                    </th>
                                    <th>Serie</th>
                                    <th>Numero<br>
                                        <input class="form-control" ng-model="parametro.numero" ng-enter="listar()">
                                    </th>
                                    <th>Refs.
                                        <input class="form-control" ng-model="parametro.documentoRef" ng-enter="listar()">
                                    </th>
                                    <th>Fecha<br>
                                        <input class="form-control" type="date" ng-model="parametro.fechaEmision" ng-enter="listar()">
                                    </th>
                                    <th>Nro. Doc.<br>
                                        <input class="form-control" ng-model="parametro.documentoCliente" ng-enter="listar()">
                                    <th>Nombre<br>
                                        <input class="form-control" ng-model="parametro.nombreCliente" ng-enter="listar()">
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="item in documentoVentas" ng-click="setSelected($index, item)" ng-class="{activeItem:$index == selectedRow ,anulado:item.codigoEstado === 'ANU'}">
                                    <td>{{$index + 1}}</td>
                                    <td>{{item.nombreTipo}}</td>
                                    <td>{{item.nombreEstado}}</td>
                                    <td>{{item.serie}}</td>
                                    <td>{{item.numero}}</td>
                                    <td>{{item.documentoRef}}</td>
                                    <td>{{item.fechaEmision| date:"dd/MM/yyyy"}}</td>
                                    <td>{{item.documentoCliente}}</td>
                                    <td>{{item.nombreCliente}}</td>
                                    <td><div class="pull-right">S/.{{item.total| number:2}}</div></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-sm-7">
                        <ul  uib-pagination max-size="maxSize" boundary-links="true" total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPage" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;"  rotate="false"  last-text="&raquo;"/>
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
                                        <div class="col-sm-9" ng-class="{ 'has-error' : formNotificacion.email.$invalid}">
                                            <input type="email" name="email"  ng-model="notificacion.email"  class="form-control" ng-disabled="tipoNotificacion.codigo === 'CLI'" ng-required="tipoNotificacion.codigo === 'PER'"/>
                                            <p ng-show="formNotificacion.email.$invalid" class="help-block">Por favor, escriba un correo electrónico válido.</p>
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
                                <form class="form-horizontal" role="form" name="formAnulacion">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >Tipo:</label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="tipoAnulacion"  ng-model="tipoAnulacion" ng-options="tipoAnulacion.nombre for tipoAnulacion in tipoAnulacions track by tipoAnulacion.codigo"  disabled>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group" >
                                        <label class="control-label col-sm-3" >Motivo:</label>
                                        <div class="col-sm-9" ng-class="{'has-error' : formAnulacion.motivo.$invalid }">
                                            <textarea  name="motivo" class="form-control" ng-model="anulacion.motivo" required="true"></textarea>
                                            <p ng-show="formAnulacion.motivo.$invalid" class="help-block">Este campo es obligatorio.</p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="anularDocumentoVenta(documentoVenta)" ng-disabled="formAnulacion.$invalid">Anular</button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
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
