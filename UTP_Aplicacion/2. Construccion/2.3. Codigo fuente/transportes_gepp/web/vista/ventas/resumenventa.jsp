<%-- 
    Document   : producto
    Created on : 14-ago-2017, 17:57:15
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/imports.jspf" %>

        <title>Transportes :: Resumenes/Comunicaciones</title>
    </head>
    <script src="<%= request.getContextPath()%>/js/ventas/resumenventa.js" type="text/javascript"></script>
    <body ng-app='ResumenVentaApp' ng-controller='ResumenVentaController'>
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
                            <table class="table table-bordered">
                                <thead>
                                <th colspan="7" >
                                <h2>Resumenes / Comunicaciones
                                    <div style="
                                         float: right;
                                         ">
                                        <button type="button" class="btn btn-default" ng-click="descargarResumenVenta()"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></button>
                                        <button type="button" class="btn btn-default" ng-click="enviarSunat()"><i class="fa fa-share-square-o" aria-hidden="true"></i> Enviar a SUNAT</button>
                                        <button type="button" class="btn btn-default"><i class="fa fa-share-square-o" aria-hidden="true"></i> Consultar Ticket SUNAT</button>
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalAnular"><i class="fa fa-ban" aria-hidden="true"></i> Anular</button>
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalResumen" ng-click="limpiar()"><i class="fa fa-plus" aria-hidden="true"></i> Crear Resumen</button>
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalComunicacionBaja" ng-click="limpiar()"><i class="fa fa-plus" aria-hidden="true"></i> Crear Comunicaciones</button>
                                    </div>
                                </h2>
                                <tr>
                                    <th>#</th>
                                    <th>Tipo</th>
                                    <th>Estado<br>
                                        <select class="form-control"
                                                ng-model="parametro.estadoDocumentoVenta" ng-options="estadoDocumentoVenta.nombre for estadoDocumentoVenta in estadoDocumentoVentas track by estadoDocumentoVenta.codigo"
                                                ng-change="listar()">
                                            <option value="" selected>TODOS</option>
                                        </select>
                                    </th>
                                    <th>Número<br>
                                        <input class="form-control" ng-model="parametro.numero" ng-enter="listar()">
                                    </th>
                                    <th>Fecha<br>
                                        <input class="form-control" type="date" ng-model="parametro.fechaEmision" ng-enter="listar()">
                                    </th>
                                    <th>Ticket</th>
                                    <th>Estado Proceso en SUNAT</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="listaResumenVenta in listaResumenVentas" ng-click="setSelected($index, listaResumenVenta)" ng-class="{activeItem:$index == selectedRow}">
                                        <td>{{$index + 1}}</td>
                                        <td>{{listaResumenVenta.tipo}}</td>
                                        <td>{{listaResumenVenta.nombreEstado}}</td>
                                        <td>{{listaResumenVenta.numero}}</td>
                                        <td>{{listaResumenVenta.fechaEmision | date:"dd/MM/yyyy"}}</td>
                                        <td>{{listaResumenVenta.ticketSunat|| 0}}</td>
                                        <td>{{listaResumenVenta.estadoSunat|| 0}}</td>
                                        <!--td class="text-center">
                                            <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalProducto" ng-click="seleccionarProducto(producto)">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </button>
                                            <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalEliminarProducto" ng-click="seleccionarProducto(producto)">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </button>
                                        </td-->
                                    </tr>
                                </tbody>
                            </table>

                            <div class="row clearfix">
                                <div class="col-sm-7">
                                    <ul  uib-pagination max-size="maxSize" boundary-links="true" total-items="totalItemsListaResumenVentas" ng-model="currentPageListaResumenVentas" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPageListarResumenVentas" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;"  rotate="false"  last-text="&raquo;"/>
                                </div>
                                <div class="col-sm-4">
                                    <div class="pull-right">
                                        Ver <select style="width: auto;" class="feature-icon form-control"  ng-options="paginaListaResumenVentas.value for paginaListaResumenVentas in paginasListaResumenVentas track by paginaListaResumenVentas.value"  ng-model="paginaListaResumenVentas" ng-change="setItemsPerPage(paginaListaResumenVentas.value)" ng-init="paginasListaResumenVentas[0].value">
                                        </select> de <b>{{totalItemsListaResumenVentas}}</b> Registro(s)
                                    </div>
                                </div>
                            </div>
                            
                        </form>
                        <!-- Modal -->
                        <div class="modal fade" id="modalResumen" role="dialog">
                            <div class="modal-dialog modalMedium">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Grupos de Boletas</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formProducto">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Fecha</label>
                                                <div class="col-sm-4">
                                                    <input type="date" class="form-control" ng-model="fechaEmision"  required/>
                                                </div>
                                                <button type="button" class="btn btn-default elementRight" data-toggle="modal" data-target="#modalDocumentoVenta" ng-click="listarDocumentoVenta('03')"><i class="fa fa-plus" aria-hidden="true"></i> Agregar Grupo</button>
                                            </div>


                                            <div class="row">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Serie</th>
                                                            <th>Desde</th>
                                                            <th>Hasta</th>
                                                            <th>Grabada</th>
                                                            <th>IGV</th>
                                                            <th>Total</th>
                                                            <th>Eliminar</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr ng-repeat="listaResumenVentaGrupo in resumenVenta.resumenVentasGrupos">
                                                            <td>{{$index + 1}}</td>
                                                            <td>{{listaResumenVentaGrupo.puntoVentaSerie.codigo}}</td>
                                                            <td>{{listaResumenVentaGrupo.inicioDocumentoVenta}}</td>
                                                            <td>{{listaResumenVentaGrupo.finDocumentoVenta}}</td>
                                                            <td>{{listaResumenVentaGrupo.totalGrabado}}</td>
                                                            <td>{{listaResumenVentaGrupo.totalIgv}}</td>
                                                            <td>{{listaResumenVentaGrupo.total}}</td>
                                                            <td>
                                                                <button class="btn btn-default btn-xs" ng-click="eliminarListaResumenVentaGrupo(listaResumenVentaGrupo)">
                                                                    <span class="glyphicon glyphicon-trash"></span>
                                                                </button>
                                                            </td>
                                                            <!--td class="text-center">
                                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalProducto" ng-click="seleccionarProducto(producto)">
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                </button>
                                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalEliminarProducto" ng-click="seleccionarProducto(producto)">
                                                                    <span class="glyphicon glyphicon-trash"></span>
                                                                </button>
                                                            </td-->
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <ul  uib-pagination boundary-links="true" total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPage" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;">
                                                </ul>
                                                <div class="form-group elementRight" style="
                                                     width: 37em;
                                                     ">
                                                    <label class="control-label col-sm-4" style="
                                                           float: right;
                                                           ">Op. Gravada S/. {{totalGrabado| number:2||"0.00"}}</label>
                                                    <label class="control-label col-sm-4" style="
                                                           clear: both;
                                                           float: right;
                                                           ">IGV S/. {{totalIgv| number:2||"0.00"}}</label>
                                                    <label class="control-label col-sm-4" style="
                                                           clear: both;
                                                           float: right;
                                                           ">Importe Total S/. {{totalImporte| number:2||"0.00"}}</label>
                                                </div>
                                            </div>

                                        </form>
                                    </div>



                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalResumenBoletasConfirmacion" ng-disabled="formProducto.$invalid">Guardar</button>

                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        <!-- Modal -->
                        <!-- Modal -->

                        <!-- Modal -->
                        <div class="modal fade" id="modalDocumentoVenta" role="dialog">
                            <div class="modal-dialog modalMedium">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Boletas de Ventas</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formProducto">
                                            <div class="row">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>#</th>
                                                            <th>
                                                                Serie<br>
                                                                <select class="form-control"
                                                                        ng-init="puntoVentaSeries[0].value" ng-model="puntoVentaSerie" ng-options="puntoVentaSerie.codigo for puntoVentaSerie in puntoVentaSeries track by puntoVentaSerie.codigo"
                                                                        ng-change="listarDocumentoVenta()">
                                                                </select>
                                                            </th>
                                                            <th>Número</th>
                                                            <th>Total</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr ng-repeat="documentoVenta in documentoVentas| filter:{codigoEstado :'!ANU'}">
                                                            <td><input type="checkbox" ng-model="documentoVenta.selected"/></td>
                                                            <td>{{$index + 1}}</td>
                                                            <td>{{documentoVenta.serie}}</td>
                                                            <td>{{documentoVenta.numero}}</td>
                                                            <td>{{documentoVenta.total.toFixed(2)}}</td>

                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <!--ul  uib-pagination boundary-links="true" total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPage" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;">
                                                </ul-->
                                                <div class="row clearfix">
                                                    <div class="col-sm-7">
                                                        <ul  uib-pagination max-size="maxSize" boundary-links="true" total-items="totalItemsListaDocumentoVentas" ng-model="currentPageListaDocumentoVentas" ng-change="pageChangedListaDocumentoVentas()" class="pagination-sm " items-per-page="itemsPerPageListaDocumentoVentas" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;"  rotate="false"  last-text="&raquo;"/>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="pull-right">
                                                            Ver <select style="width: auto;" class="feature-icon form-control"  ng-options="paginaListaDocumentoVentas.value for paginaListaDocumentoVentas in paginasListaDocumentoVentas track by paginaListaDocumentoVentas.value"  ng-model="paginaListaDocumentoVentas" ng-change="setItemsPerPageListaDocumentoVentas(paginaListaDocumentoVentas.value)" ng-init="paginasListaDocumentoVentas[0].value">
                                                            </select> de <b>{{totalItemsListaDocumentoVentas}}</b> Registro(s)
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-disabled="formProducto.$invalid" ng-click="selectDocumentoVentas()">Guardar</button>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="modalComunicacionBaja" role="dialog">
                            <div class="modal-dialog modalMedium">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Comunicación de Baja</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formProducto">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Fecha</label>
                                                <div class="col-sm-4">
                                                    <input type="date" class="form-control" ng-model="fechaEmisionBaja"  required/>
                                                </div>
                                                <button type="button" class="btn btn-default elementRight" data-toggle="modal" data-target="#modalDocumentoVentaBaja" ng-click="listarDocumentoVentaBaja()"><i class="fa fa-plus" aria-hidden="true"></i> Agregar Grupo</button>
                                            </div>

                                            <div class="row">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Tipo Venta</th>
                                                            <th>Serie</th>
                                                            <th>Número</th>
                                                            <th>Descripción</th>
                                                            <th>Eliminar</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr ng-repeat="comunicacionBajaGrupo in comunicacionBajaGrupos">
                                                            <td>{{$index + 1}}</td>
                                                            <td>{{comunicacionBajaGrupo.tipoDocumentoVenta.nombre}}</td>
                                                            <td>{{comunicacionBajaGrupo.puntoVentaSerie.codigo}}</td>
                                                            <td>{{comunicacionBajaGrupo.inicioDocumentoVenta}}</td>
                                                            <td><textarea ng-model="comunicacionBajaGrupo.descripcion"></textarea></td>
                                                            <td>
                                                                <button class="btn btn-default btn-xs" ng-click="eliminarComunicacionBajaGrupo(comunicacionBajaGrupo)">
                                                                    <span class="glyphicon glyphicon-trash"></span>
                                                                </button>
                                                            </td>
                                                            <!--td class="text-center">
                                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalProducto" ng-click="seleccionarProducto(producto)">
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                </button>
                                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalEliminarProducto" ng-click="seleccionarProducto(producto)">
                                                                    <span class="glyphicon glyphicon-trash"></span>
                                                                </button>
                                                            </td-->
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <ul  uib-pagination boundary-links="true" total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPage" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;">
                                                </ul>
                                            </div>

                                        </form>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalComunicacionBajaConfirmacion" ng-disabled="formProducto.$invalid" >Guardar</button>
                                    </div>
                                   
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="modalDocumentoVentaBaja" role="dialog">
                            <div class="modal-dialog modalMedium">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Documentos de Ventas</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formProducto">
                                            <div class="row">
                                                <table class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>#</th>
                                                            <th>
                                                                Tipo<br>
                                                                <select class="form-control"
                                                                        ng-init="tipoDocumentoVentas[0].value" ng-model="tipoDocumentoVenta" ng-options="tipoDocumentoVenta.nombre for tipoDocumentoVenta in tipoDocumentoVentas track by tipoDocumentoVenta.codigo"
                                                                        ng-change="listarDocumentoVentaBaja()">
                                                                </select>
                                                            </th>
                                                            <th>
                                                                Serie<br>
                                                                <select class="form-control"
                                                                        ng-init="puntoVentaSeries[0].value" ng-model="puntoVentaSerie" ng-options="puntoVentaSerie.codigo for puntoVentaSerie in puntoVentaSeries track by puntoVentaSerie.codigo"
                                                                        ng-change="listarDocumentoVentaBaja()">
                                                                </select>
                                                            </th>
                                                            <th>Número</th>
                                                            <th>Total</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr ng-repeat="documentoVenta in documentoVentas| filter:{codigoEstado :'!ANU'}">
                                                            <td><input type="checkbox" ng-model="documentoVenta.selected"/></td>
                                                            <td>{{$index + 1}}</td>
                                                            <td>{{documentoVenta.nombreTipo}}</td>
                                                            <td>{{documentoVenta.serie}}</td>
                                                            <td>{{documentoVenta.numero}}</td>
                                                            <td>{{documentoVenta.total.toFixed(2)}}</td>
                                                            <!--td class="text-center">
                                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalProducto" ng-click="seleccionarProducto(producto)">
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                </button>
                                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalEliminarProducto" ng-click="seleccionarProducto(producto)">
                                                                    <span class="glyphicon glyphicon-trash"></span>
                                                                </button>
                                                            </td-->
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <div class="row clearfix">
                                                    <div class="col-sm-7">
                                                        <ul  uib-pagination max-size="maxSize" boundary-links="true" total-items="totalItemsListaBaja" ng-model="currentPageListaBaja" ng-change="pageChangedListaBaja()" class="pagination-sm " items-per-page="itemsPerPageListaBaja" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;"  rotate="false"  last-text="&raquo;"/>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="pull-right">
                                                            Ver <select style="width: auto;" class="feature-icon form-control"  ng-options="paginaListaBaja.value for paginaListaBaja in paginasListaBaja track by paginaListaBaja.value"  ng-model="paginaListaBaja" ng-change="setItemsPerPageListaBaja(paginaListaBaja.value)" ng-init="paginasListaBaja[0].value">
                                                            </select> de <b>{{totalItemsListaBaja}}</b> Registro(s)
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </form>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-disabled="formProducto.$invalid" ng-click="selectDocumentoVentasBaja()">Guardar</button>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>


                        <div class="modal fade" id="modalAnular" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Anulación</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h1>El registro será anulado ¿Está seguro?</h1>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="anular()">Anular</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="modalComunicacionBajaConfirmacion" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Comunicación de Baja</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h1>¿Desea realizar la comunicación de baja?</h1>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="crearResumenVentasGruposBaja(comunicacionBajaGrupos)">Aceptar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="modalResumenBoletasConfirmacion" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Resumen de Boletas</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h1>¿Desea realizar el resumen de boletas?</h1>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="crearResumenVentasGrupos(resumenVenta)">Aceptar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
            <div id="rowPie" class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
            </div>
        </div>
    </body>
</html>
