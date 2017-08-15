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
        <div class="container">
            <div class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
            </div>
            <div class='row'>
                <div class="col-sm-3">
                    <ng-include src="'<%= request.getContextPath()%>/vista/menu.jsp'"></ng-include>
                </div>
                <div class="col-sm-9">
                    <form>
                        <fieldset>
                            <legend>ROLES</legend>
                            <div class="row">
                                <button class="btn btn-default pull-right" data-toggle="modal" data-target="#modalNuevoRol" data-placement="top" rel="tooltip"><span class="glyphicon glyphicon-plus"> Nuevo Rol</span></button>
                            </div>
                            <div class="row">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nombre</th>
                                            <th>Descripción</th>
                                            <th>Administrador</th>
                                            <th>Editar</th>
                                            <th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="rol in roles">
                                            <td>{{$index + 1}}</td>
                                            <td>{{rol.nombre}}</td>
                                            <td>{{rol.descripcion}}</td>
                                            <td>{{rol.admin}}</td>
                                            <td>
                                                <button class="btn btn-default btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" data-placement="top" rel="tooltip">
                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                </button>
                                            </td>
                                            <td>
                                                <button class="btn btn-default btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" data-placement="top" rel="tooltip">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </fieldset>
                    </form>
                    <!-- Modal -->
                    <div class="modal fade" id="modalNuevoRol" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Nuevo Rol</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form" name="formNuevoRol">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Nombre</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="username" ng-model="rol.nombre" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Descripción</label>
                                            <div class="col-sm-10">
                                                <input  class="form-control"  ng-model="rol.descripcion" required type="text" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Administrador</label>
                                            <div class="col-sm-10">
                                                <input type="checkbox" class="form-control" ng-model="rol.admin">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="guardarRol()">Guardar</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class='row'>
                <ng-include src="'<%= request.getContextPath()%>/vista/pie.jsp'"></ng-include>
            </div>
        </div>
    </body>
</html>
