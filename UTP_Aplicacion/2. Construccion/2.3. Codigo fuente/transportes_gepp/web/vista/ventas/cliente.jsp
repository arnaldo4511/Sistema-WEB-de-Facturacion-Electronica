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
                                <th colspan="7" >
                                <h2>CLIENTES
                                    <button style="float:right" class="btn btn-default btn-lg" data-toggle="modal" data-target="#modalItem" tabindex="4" ng-click="nuevoCliente()">
                                        <span class="glyphicon glyphicon-plus" >Nuevo</span>
                                    </button>
                                </h2>
                                </th>
                                <tr>
                                    <th>#</th>
                                    <th>
                                        Tipo de Documento
                                        <br>
                                    </th>
                                    <th>
                                        Documento
                                        <br>
                                        <input type="text" class="form-control" ng-model="parametro.documento" ng-enter="listar()" tabindex="1">
                                    </th>
                                    <th>
                                        Nombre
                                        <br>
                                        <input type="text" class="form-control" ng-model="parametro.nombre" ng-enter="listar()" tabindex="2">
                                    </th>
                                    <th>Direccion</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="item in clientes">
                                        <td>{{$index + 1}}</td>
                                        <td>{{item.nombreTipo}}</td>
                                        <td>{{item.documento}}</td>
                                        <td>{{item.nombre}}</td>
                                        <td>{{item.direccion}}</td>
                                        <td class="text-center">
                                            <button title="Editar Cliente" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalItem" ng-click="seleccionarItem(item)">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </button>
                                        </td>
                                        <td class="text-center">
                                            <button title="Eliminar Cliente" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modalEliminarItem" ng-click="seleccionarItem(item)">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                            <div class="col-sm-7">
                                <ul  uib-pagination max-size="maxSize" boundary-links="true" total-items="totalItemsListarClientes" ng-model="currentPageListarClientes" ng-change="pageChanged()" class="pagination-sm " items-per-page="itemsPerPageListarClientes" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;"  rotate="false"  last-text="&raquo;"/>
                            </div>
                            <div class="col-sm-4">
                                <div class="pull-right">
                                    Ver <select style="width: auto;" class="feature-icon form-control"  ng-options="paginaListarClientes.value for paginaListarClientes in paginasListarClientes track by paginaListarClientes.value"  ng-model="paginaListarClientes" ng-change="setItemsPerPage(paginaListarClientes.value)" ng-init="paginasListarClientes[0].value">
                                    </select> de <b>{{totalItemsListarClientes}}</b> Registro(s)
                                </div>
                            </div>
                            clientes{{clientes}}
                            item{{item}}
                        </form>
                        <!-- Modal -->
                        <div class="modal fade" id="modalItem" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">{{mensajeTituloCliente}}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formItem" novalidate>
                                            <div class="form-group" ng-class="{'has-error' : formItem.tipoDocumento.$invalid}">
                                                <label class="control-label col-sm-3" >Tipo Documento*</label>
                                                <div class="col-sm-9">
                                                    <select type="text" class="form-control" name="tipoDocumento" ng-change="cambioTipoDocumentoVenta()" ng-model="clienteTmp.entidad.tipoDocumentoEntidad" ng-options="tipoDocumentoEntidad.nombre for tipoDocumentoEntidad in tiposDocumentosEntidads track by tipoDocumentoEntidad.codigo" required>
                                                    </select>
                                                    <p ng-show="formItem.tipoDocumento.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                                tiposDocumentosEntidads{{tiposDocumentosEntidads}}
                                                tipoDocumentoEntidad{{tipoDocumentoEntidad}}
                                                tipoDocumentoEntidad.codigo{{tipoDocumentoEntidad.codigo}}
                                                tipoDocumentoEntidad.nombre{{tipoDocumentoEntidad.nombre}}
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formItem.documento.$invalid}">
                                                <label class="control-label col-sm-3" >Documento*</label>
                                                <div class="col-sm-7">
                                                    <input type="number" class="form-control" name="documento" ng-model="clienteTmp.entidad.documento" ng-minlength='{{cambiarDocumento.clienteTmp.entidad.documento.minlength}}' ng-maxlength="{{cambiarDocumento.clienteTmp.entidad.documento.maxlength}}" required disabled formatter-directive>
                                                    <p ng-show="formItem.documento.$error.minlength" class="help-block">Por favor, no escriba menos de {{cambiarDocumento.clienteTmp.entidad.documento.minlength}} caracteres.</p>
                                                    <p ng-show="formItem.documento.$error.maxlength" class="help-block">Por favor, no escriba más de {{cambiarDocumento.clienteTmp.entidad.documento.maxlength}} caracteres.</p>
                                                    <p ng-show="formItem.documento.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                                <div class="col-sm-1">
                                                    <button type="button" 
                                                            class="btn btn-default" 
                                                            ng-click="buscarPadron(clienteTmp)">
                                                        <span class="glyphicon glyphicon-search">
                                                        </span>
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formItem.nombre.$invalid}">
                                                <label class="control-label col-sm-3" >Nombre*</label>
                                                <div class="col-sm-9" >
                                                    <input type="text" class="form-control" name="nombre" ng-model="clienteTmp.entidad.nombre" ng-maxlength="250" required>
                                                    <p ng-show="formItem.nombre.$error.maxlength" class="help-block">Por favor, no escriba más de 250 caracteres.</p>
                                                    <p ng-show="formItem.nombre.$invalid" class="help-block">Este campo es obligatorio.</p>

                                                </div>

                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formItem.direccion.$invalid}">
                                                <label class="control-label col-sm-3" >Direccion*</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" name="direccion" ng-model="clienteTmp.entidad.direccion" ng-maxlength="250" required>
                                                    <p ng-show="formItem.direccion.$error.maxlength" class="help-block">Por favor, no escriba más de 250 caracteres.</p>
                                                    <p ng-show="formItem.direccion.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formItem.email1.$error.email || formItem.email1.$invalid}">
                                                <label class="control-label col-sm-3" >E-mail 1*</label>
                                                <div class="col-sm-9">
                                                    <input type="email" class="form-control" name="email1" ng-model="clienteTmp.entidad.correoElectronico1" required>
                                                    <p ng-show="formItem.email1.$error.email" class="help-block">Por favor, escriba un correo electrónico válido.</p>
                                                    <p ng-show="formItem.email1.$invalid" class="help-block">Este campo es obligatorio.</p>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formItem.email2.$error.email || formItem.email2.$invalid}">
                                                <label class="control-label col-sm-3" >E-mail 2</label>
                                                <div class="col-sm-9">
                                                    <input type="email" class="form-control" name="email2" ng-model="clienteTmp.entidad.correoElectronico2">
                                                    <p ng-show="formItem.email2.$error.email" class="help-block">Por favor, escriba un correo electrónico válido.</p>
                                                </div>
                                            </div>
                                            <div class="form-group" ng-class="{ 'has-error' : formItem.email3.$error.email || formItem.email3.$invalid}">
                                                <label class="control-label col-sm-3" >E-mail 3</label>
                                                <div class="col-sm-9">
                                                    <input type="email" class="form-control" name="email3" ng-model="clienteTmp.entidad.correoElectronico3">
                                                    <p ng-show="formItem.email3.$error.email" class="help-block">Por favor, escriba un correo electrónico válido.</p>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modalClienteConfirmacion" ng-disabled="formItem.$invalid" >Guardar</button>
                                    </div>
                                    clienteTmp{{clienteTmp}}    
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
                        <div class="modal fade" id="modalClienteConfirmacion" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Cliente</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h1>{{mensajeConfirmacion}}</h1>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="guardar(clienteTmp)">Aceptar</button>
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
