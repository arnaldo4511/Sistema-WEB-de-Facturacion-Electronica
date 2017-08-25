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
        <script src="<%= request.getContextPath()%>/js/facturacion/resumenventa.js" type="text/javascript"></script>
        <title>Transportes :: Resumenes/Comunicaciones</title>
    </head>
    <body ng-app='ResumenVentaApp' ng-controller='ResumenVentaController'>
        <div class="container">
            <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
            <div id="rowMenu" class='row'>
                <div id="colMenu" class="col-sm-3">
                    <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                </div>
                <div id="divForm" class="col-sm-9">
                    <form>
                        <!--label>Nombre</label>  
                        <input type="text" name="country" id="country" ng-model="country" ng-keyup="complete(country)" class="form-control" />  
                        <ul class="list-group" ng-model="hidethis" ng-hide="hidethis">  
                            <li class="list-group-item" ng-repeat="countrydata in filterCountry" ng-click="fillTextbox(countrydata)">{{countrydata}}</li>  
                        </ul--> 
                        <fieldset>
                            <legend>Resumenes / Comunicaciones</legend>
                            <div class="row">
                                <button type="button" class="btn btn-default"><i class="fa fa-file-pdf-o" aria-hidden="true"></i></button>
                                <button type="button" class="btn btn-default"><i class="fa fa-share-square-o" aria-hidden="true"></i> Enviar a SUNAT</button>
                                <button type="button" class="btn btn-default"><i class="fa fa-share-square-o" aria-hidden="true"></i> Consultar Ticket SUNAT</button>
                                <button type="button" class="btn btn-default"><i class="fa fa-ban" aria-hidden="true"></i> Anular</button>
                                <button type="button" class="btn btn-default"><i class="fa fa-plus" aria-hidden="true"></i> Crear Resumen</button>
                                <button type="button" class="btn btn-default"><i class="fa fa-plus" aria-hidden="true"></i> Crear Comunicaciones</button>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Tipo</th>
                                            <th>Estado</th>
                                            <th>Descripcion Estado</th>
                                            <th>Numero</th>
                                            <th>Fecha</th>
                                            <th>Ticket</th>
                                            <th>Estado Proceso en SUNAT</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="resumenVenta in resumenVentas.slice(((currentPage - 1) * itemsPerPage), ((currentPage) * itemsPerPage))| filter:search ">
                                            <td>{{$index + 1}}</td>
                                            <td>{{resumenVenta.tipo}}</td>
                                            <td>{{resumenVenta.estadoDocumentoVenta}}</td>
                                            <td></td>
                                            <td>{{resumenVenta.numero}}</td>
                                            <td>{{resumenVenta.fechaEmision}}</td>
                                            <td></td>
                                            <td></td>
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
                        </fieldset>
                    </form>
                    <!-- Modal -->
                    <div class="modal fade" id="modalProducto" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">{{mensajeTituloProducto}}</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form" name="formProducto">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Nombre</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="username" ng-model="productoTmp.nombre" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Descripción</label>
                                            <div class="col-sm-10">
                                                <input  class="form-control"  ng-model="productoTmp.descripcion" required type="text" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Unidad</label>
                                            <div class="col-sm-10">
                                                <select  class="form-control"  ng-model="productoTmp.unidad" ng-options="unidad.codigo for unidad in unidades track by unidad.codigo" required type="text" required>
                                                </select>    
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Precio Venta</label>
                                            <div class="col-sm-10">
                                                <input  class="form-control"  ng-model="productoTmp.precioVenta" required type="text" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Precio Compra</label>
                                            <div class="col-sm-10">
                                                <input  class="form-control"  ng-model="productoTmp.precioCompra" required type="text" required>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-disabled="formProducto.$invalid" ng-click="guardarProducto(productoTmp)">Guardar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="modalEliminarProducto" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Eliminar Producto</h4>
                                </div>
                                <div class="modal-body">
                                    <h1>El registro será eliminado ¿Está seguro?</h1>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="eliminarProducto(productoTmp)">Eliminar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="rowPie" class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
            </div>
        </div>
    </body>
</html>
