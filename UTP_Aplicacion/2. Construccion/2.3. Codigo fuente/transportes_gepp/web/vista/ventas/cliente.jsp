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
        <script src="<%= request.getContextPath()%>/js/ventas/cliente.js" type="text/javascript"></script>
        <title>Transportes :: Cliente</title>
    </head>
    <body ng-app='ClienteApp' ng-controller='ClienteController'>
        <div class="container">
            <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
            <div id="rowMenu" class='row'>
                <div id="colMenu" class="col-sm-3">
                    <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                </div>
                <div id="divForm" class="col-sm-9">
                    <form>
                        <fieldset>
                            <legend>CLIENTES</legend>
                            <div class="row">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>
                                                Tipo de Documento
                                                <br>
                                            </th>
                                            <th>
                                                Documento
                                                <br>
                                                <input type="text" class="form-control" ng-model="search.documento" tabindex="1">
                                            </th>
                                            <th>
                                                Nombre
                                                <br>
                                                <input type="text" class="form-control" ng-model="search.nombre" tabindex="2">
                                            </th>
                                            <th>Direccion</th>
                                            <th class="text-center">
                                                <button class="btn btn-default btn-lg" data-toggle="modal" data-target="#modalItem" tabindex="4">
                                                    <span class="glyphicon glyphicon-plus" ng-click="nuevoCliente()">Nuevo</span>
                                                </button>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="item in clientes.slice(((currentPage - 1) * itemsPerPage), ((currentPage) * itemsPerPage))| filter:search ">
                                            <td>{{$index + 1}}</td>
                                            <td>{{item.entidad.tipoDocumentoEntidad.nombre}}</td>
                                            <td>{{item.entidad.documento}}</td>
                                            <td>{{item.entidad.nombre}}</td>
                                            <td>{{item.entidad.direccion}}</td>
                                            <td class="text-center">
                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalItem" ng-click="seleccionarItem(item)">
                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                </button>
                                                
                                                <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalEliminarItem" ng-click="seleccionarItem(item)">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <ul  uib-pagination boundary-links="true" total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPage" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;">
                                </ul>
                            </div>
                        </fieldset>
                    </form>
                    <!-- Modal -->
                    <div class="modal fade" id="modalItem" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">{{mensajeTitulo}}</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form" name="formItem">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Tipo Documento</label>
                                            <div class="col-sm-10">
                                                <select type="text" class="form-control" ng-model="clienteTmp.entidad.tipoDocumentoEntidad" ng-options="tipoDocumentoEntidad.nombre for tipoDocumentoEntidad in tiposDocumentosEntidades track by tipoDocumentoEntidad.nombre" required>
                                                </select>    
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Documento</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="username" ng-model="clienteTmp.entidad.documento"  required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Nombre</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="username" ng-model="clienteTmp.entidad.nombre" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Direccion</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" ng-model="clienteTmp.entidad.direccion" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >E-mail 1</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" ng-model="clienteTmp.entidad.correoElectronico1" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >E-mail 2</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" ng-model="clienteTmp.entidad.correoElectronico2">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >E-mail 3</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" ng-model="clienteTmp.entidad.correoElectronico3">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-disabled="formItem.$invalid" ng-click="guardar(clienteTmp)">Guardar</button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="modal fade" id="modalEliminarItem" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Eliminar Cliente</h4>
                                </div>
                                <div class="modal-body">
                                    <h1>El registro será eliminado ¿Está seguro?</h1>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="eliminar(entidadTmp)">Eliminar</button>
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
