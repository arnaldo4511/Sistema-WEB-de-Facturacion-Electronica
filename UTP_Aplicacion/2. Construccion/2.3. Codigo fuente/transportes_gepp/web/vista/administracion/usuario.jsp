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
        <script src="<%= request.getContextPath()%>/js/administracion/usuario.js" type="text/javascript"></script>
        <title>Transportes :: Rol</title>
    </head>
    <body ng-app='UsuarioApp' ng-controller='UsuarioController'>
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
                                    <tr>
                                        <th colspan="9" >
                                            <h2>USUARIOS
                                                <button style="float:right" class="btn btn-default btn-lg " data-toggle="modal" data-target="#modalItem" tabindex="4" >
                                                    <span class="glyphicon glyphicon-plus" ng-click="nuevo()"> Nuevo</span>
                                                </button>
                                            </h2>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>#</th>
                                        <th>
                                            Nombre Completo
                                        </th>
                                        <th>
                                            Nombre
                                            <br>
                                            <input type="text" class="form-control" ng-model="search.nombre" tabindex="1">
                                        </th>
                                        <th>
                                            clave
                                            <br>
                                            <input type="text" class="form-control" ng-model="search.clave" tabindex="2">
                                        </th>
                                        <th>
                                            Rol
                                            <br>
                                            <select class="form-control" ng-model="search.rol" ng-options="rol.nombre for rol in roles track by rol.id" tabindex="3">
                                                <option value="">Todos</option>
                                            </select>
                                        </th>
                                        <th>
                                            Punto de Venta
                                            <br>
                                            <select class="form-control" ng-model="search.puntoVenta" ng-options="puntoVenta.nombre for puntoVenta in puntoVentas track by puntoVenta.nombre" tabindex="3">
                                                <option value="">Todos</option>
                                            </select>
                                        </th>
                                        <th>
                                            Estado
                                            <br>
                                            <select class="form-control" ng-model="search.activo" tabindex="4">
                                                <option value="" selected>Todos</option>
                                                <option value="true">Activo</option>
                                                <option value="false">Desactivo</option>
                                            </select>
                                        </th>
                                        <th colspan="2">
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="item in usuarios.slice(((currentPage - 1) * itemsPerPage), ((currentPage) * itemsPerPage))| filter:search ">
                                        <td>{{$index + 1}}</td>
                                        <td>{{item.entidad.documento + " - " + item.entidad.nombre}}</td>
                                        <td>{{item.nombre}}</td>
                                        <td>{{item.clave}}</td>
                                        <td>{{item.rol.nombre}}</td>
                                        <td>{{item.puntoVenta.nombre}}</td>
                                        <td>{{item.activo?'Activo':'Desactivo'}}</td>
                                        <td class="text-center">
                                            <button class="btn btn-default btn-xs " data-toggle="modal" data-target="#modalItem" ng-click="seleccionarItem(item)">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </button>
                                        </td>
                                        <td>
                                            <button class="btn btn-default btn-xs " data-toggle="modal" data-target="#modalEliminarItem" ng-click="seleccionarItem(item)">
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
                        <div class="modal fade" id="modalItem" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" ng-click="usuario = {}">&times;</button>
                                        <h4 class="modal-title">{{mensajeTitulo}}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formItem">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Entidad</label>
                                                <div class="col-sm-9">
                                                    <input type="text"  ng-model="usuario.entidad.nombre" ng-keyup="completarEntidad(usuario.entidad.nombre)" class="form-control" />
                                                    <ul class="list-group" ng-hide="ocultarEntidadesAuto">
                                                        <li class="list-group-item" ng-repeat="entidad in entidades" ng-click="seleccionarEntidad(entidad)">{{entidad.documento + " - " + entidad.nombre}}</li>  
                                                    </ul> 
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Nombre</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" name="nombre" ng-model="usuario.nombre" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Clave</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" name="clave"  ng-model="usuario.clave" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Rol</label>
                                                <div class="col-sm-9">
                                                    <select class="form-control" name="rol" ng-model="usuario.rol" ng-options="rol.nombre for rol in roles track by rol.id"  requerid>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Punto de Venta</label>
                                                <div class="col-sm-9">
                                                    <select class="form-control" name="puntoVenta" ng-model="usuario.puntoVenta" ng-options="puntoVenta.nombre for puntoVenta in puntoVentas track by puntoVenta.id"  requerid>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >Estado</label>
                                                <div class="col-sm-9">
                                                    <input type="checkbox" class="form-control" name="activo"  ng-model="usuario.activo" >
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="usuario = {}">Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-disabled="formItem.$invalid" ng-click="guardar(usuario)">Guardar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="modalEliminarItem" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Eliminar Rol</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h1>El registro será eliminado ¿Está seguro?</h1>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="eliminar(usuario)">Eliminar</button>
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
