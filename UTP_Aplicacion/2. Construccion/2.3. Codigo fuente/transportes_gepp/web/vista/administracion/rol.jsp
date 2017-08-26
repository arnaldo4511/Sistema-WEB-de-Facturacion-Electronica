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
        <script src="<%= request.getContextPath()%>/js/administracion/rol.js" type="text/javascript"></script>
        <title>Transportes :: Rol</title>
    </head>
    <body ng-app='RolApp' ng-controller='RolController'>
<<<<<<< HEAD
        <div class="container-fluid">
            <div class="row clearfix">
                <header>
                    <aside>
                    <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
                    </aside>
                </header>
            </div>
            <div class="row clearfix">
                <div class="col-md-3">
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                    </aside>
                </div>
                <div class="col-md-9">
                    <section>
                        <form>
                            <table class="table table-bordered">
                                <thead>
                                <th colspan="5" >
                                    <h2>ROLES
                                        <button style="float:right" class="btn btn-default btn-lg " data-toggle="modal" data-target="#modalRol" tabindex="4" >
                                            <span class="glyphicon glyphicon-plus" ng-click="nuevoRol()"> Nuevo</span>
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
                                    <th>
                                        Administrador
                                        <br>
                                        <select class="form-control" ng-model="search.admin" tabindex="3">
                                            <option value="" >Todos</option>
                                            <option value="true">Si</option>
                                            <option value="false">No</option>
                                        </select>
                                    </th>
                                    <th>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="rol in roles.slice(((currentPage - 1) * itemsPerPage), ((currentPage) * itemsPerPage))| filter:search ">
                                        <td>{{$index + 1}}</td>
                                        <td>{{rol.nombre}}</td>
                                        <td>{{rol.descripcion}}</td>
                                        <td>{{rol.admin?'Si':'No'}}</td>
                                        <td class="text-center">
                                            <button class="btn btn-default btn-xs " data-toggle="modal" data-target="#modalRol" ng-click="seleccionarRol(rol)">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </button>
=======
        <div class="container">
            <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
            <div id="rowMenu" class='row'>
                <div id="colMenu" class="col-sm-3">
                    <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                </div>
                <div id="divForm" class="col-sm-9">
                    <form>
                        <fieldset>
                            <legend>ROLES</legend>
                            <div class="row">
                                <table class="table table-bordered">
                                    <thead>
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
                                            <th>
                                                Administrador
                                                <br>
                                                <select class="form-control" ng-model="search.admin" tabindex="3">
                                                    <option value="" >Todos</option>
                                                    <option value="true">Si</option>
                                                    <option value="false">No</option>
                                                </select>
                                            </th>
                                            <th class="text-center">
                                                <button class="btn btn-default btn-lg" data-toggle="modal" data-target="#modalRol" tabindex="4" >
                                                    <span class="glyphicon glyphicon-plus" ng-click="nuevoRol()"> Nuevo</span>
                                                </button>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="rol in roles.slice(((currentPage - 1) * itemsPerPage), ((currentPage) * itemsPerPage))| filter:search ">
                                            <td>{{$index + 1}}</td>
                                            <td>{{rol.nombre}}</td>
                                            <td>{{rol.descripcion}}</td>
                                            <td>{{rol.admin?'Si':'No'}}</td>
                                            <td class="text-center">
                                                <button class="btn btn-default btn-xs " data-toggle="modal" data-target="#modalRol" ng-click="seleccionarRol(rol)">
                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                </button>
>>>>>>> master

                                            <button class="btn btn-default btn-xs " data-toggle="modal" data-target="#modalEliminarRol" ng-click="seleccionarRol(rol)">
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
                        <div class="modal fade" id="modalRol" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">{{mensajeTitutloRol}}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formRol">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Nombre</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="username" ng-model="rolTmp.nombre" required>
                                                    <!--<span style="color:red" class="has-error" ng-show="formRol.username.$touched && formRol.username.$invalid">
                                                        <span style="color:red" ng-show="formRol.username.$error.required">Email is required.</span>
                                                        <span style="color:red" ng-show="formRol.username.$error.email">Invalid email address.</span>
                                                    </span>-->
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Descripción</label>
                                                <div class="col-sm-10">
                                                    <input  class="form-control"  ng-model="rolTmp.descripcion" required type="text" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Administrador</label>
                                                <div class="col-sm-10">
                                                    <input type="checkbox" class="form-control" ng-model="rolTmp.admin">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-disabled="formRol.$invalid" ng-click="guardarRol(rolTmp)">Guardar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="modalEliminarRol" role="dialog">
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
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="eliminarRol(rolTmp)">Eliminar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
<<<<<<< HEAD
            <div class="row clearfix">
                <div class="col-md-12">
                    <aside>
                        <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
                    </aside>
                </div>
=======
            <div id="rowPie" class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
>>>>>>> master
            </div>
        </div>
    </body>
</html>
