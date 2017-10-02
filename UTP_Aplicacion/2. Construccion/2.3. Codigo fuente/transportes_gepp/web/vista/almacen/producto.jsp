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
        <script src="<%= request.getContextPath()%>/js/almacen/producto.js" type="text/javascript"></script>
        <title>Transportes :: Servicios</title>
    </head>
    <body ng-app='ProductoApp' ng-controller='ProductoController'>
        <div class="container-fluid">
            <div class='row clearfix'>
                <header>
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
                    </aside>
                </header>
            </div>
            <div class='row clearfix'>
                <div class="col-sm-12">
                    <section>
                        <form>
                            <table class="table table-bordered">
                                <thead>
                                <th colspan="8" >
                                <h2>SERVICIOS
                                    <button style="float:right" class="btn btn-default btn-lg" data-toggle="modal" data-target="#modalProducto" tabindex="4" ng-click="nuevoProducto()">
                                        <span class="glyphicon glyphicon-plus" >Nuevo</span>
                                    </button>
                                </h2>
                                </th>
                                <tr>
                                    <th>#</th>
                                    <th>
                                        Nombre
                                        <br>
                                        <input type="text" class="form-control" ng-model="search.nombre" tabindex="1">
                                    </th>
                                    <th>
                                        Descripción
                                        <br>
                                        <input type="text" class="form-control" ng-model="search.descripcion" tabindex="2">
                                    </th>
                                    <th>Unidad</th>
                                    <th>Precio Venta</th>
                                    <th>Precio Compra</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="producto in productos.slice(((currentPage - 1) * itemsPerPage), ((currentPage) * itemsPerPage))| filter:search ">
                                        <td>{{$index + 1}}</td>
                                        <td>{{producto.nombre}}</td>
                                        <td>{{producto.descripcion}}</td>
                                        <td>{{producto.unidad.codigo}}</td>
                                        <td>{{producto.precioVenta}}</td>
                                        <td>{{producto.precioCompra}}</td>
                                        <td class="text-center">
                                            <button title="Editar Servicio" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalProducto" ng-click="seleccionarProducto(producto)">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </button>
                                        </td>
                                        <td class="text-center">
                                            <button title="Eliminar Servicio" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalEliminarProducto" ng-click="seleccionarProducto(producto)">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <ul  uib-pagination boundary-links="true" total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPage" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;">
                            </ul>
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
                                        <form class="form-horizontal" role="form" name="formProducto" novalidate>
                                            <div class="form-group" ng-class="{'has-error' : formProducto.nombre.$invalid}">
                                                <label class="control-label col-sm-3" >Nombre*</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" name="nombre" ng-model="productoTmp.nombre" ng-maxlength="50" required>
                                                    <p ng-show="formProducto.nombre.$error.maxlength" class="help-block">Por favor, no escriba más de 50 caracteres.</p>
                                                    <p ng-show="formProducto.nombre.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formProducto.descripcion.$invalid}">
                                                <label class="control-label col-sm-3" >Descripción*</label>
                                                <div class="col-sm-9">
                                                    <input  class="form-control" name="descripcion" ng-model="productoTmp.descripcion" ng-maxlength="250" type="text" required>
                                                    <p ng-show="formProducto.descripcion.$error.maxlength" class="help-block">Por favor, no escriba más de 250 caracteres.</p>
                                                    <p ng-show="formProducto.descripcion.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formProducto.unidad.$invalid}">
                                                <label class="control-label col-sm-3" >Unidad*</label>
                                                <div class="col-sm-9">
                                                    <select  class="form-control" name="unidad" ng-model="productoTmp.unidad" ng-options="unidad.codigo for unidad in unidades track by unidad.codigo"  type="text" required>
                                                    </select>
                                                    <p ng-show="formProducto.unidad.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formProducto.precioVenta.$invalid}">
                                                <label class="control-label col-sm-3" >Precio Venta*</label>
                                                <div class="col-sm-9">
                                                    <input  class="form-control" name="precioVenta" ng-model="productoTmp.precioVenta" required type="number" required>
                                                    <p ng-show="formProducto.precioVenta.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formProducto.precioCompra.$invalid}">
                                                <label class="control-label col-sm-3" >Precio Compra*</label>
                                                <div class="col-sm-9">
                                                    <input  class="form-control" name="precioCompra" ng-model="productoTmp.precioCompra" required type="number" required>
                                                    <p ng-show="formProducto.precioCompra.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalServicioConfirmacion" ng-disabled="formProducto.$invalid" >Guardar</button>
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
                        
                        <div class="modal fade" id="modalServicioConfirmacion" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Servicios</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h1>{{mensajeConfirmacion}}</h1>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="guardarProducto(productoTmp)">Aceptar</button>
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
