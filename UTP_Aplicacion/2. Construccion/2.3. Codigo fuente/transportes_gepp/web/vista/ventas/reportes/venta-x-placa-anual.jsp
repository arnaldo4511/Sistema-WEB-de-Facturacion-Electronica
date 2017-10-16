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
        <script src="<%= request.getContextPath()%>/js/ventas/reportes/venta-x-placa-anual.js" type="text/javascript"></script>
        <title>Transportes :: Reportes</title>
    </head>
    <body ng-app='ventaXPlacaAnualApp' ng-controller='ventaXPlacaAnualController'>
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
                            <h1 class="text-center">VENTAS POR PLACA ANUAL</h1>
                            <fieldset class="scheduler-border">
                                <div class="row clearfix">
                                    <div class="form-group col-sm-2">
                                        <label >Año</label>
                                        <select class="form-control"
                                                ng-model="anhio" 
                                                ng-options="anhio.value for anhio in anhios track by anhio.value"
                                                ng-change="cambioAnhio()">
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-1">
                                        <label>Xls</label><br>
                                        <button class="btn btn-default">
                                            <a class="glyphicon glyphicon-save-file" href="{{url + '/xls'}}" 
                                               download="{{url + '/xls'}}">
                                            </a>
                                        </button>

                                    </div>
                                </div>
                            </fieldset>
                        </form>     
                        <ng-include  src="'/transportes_gepp/controlador/ventas/reporte/venta-x-placa-anual/'+ anhio.value +'/1/html'" ></ng-include>             
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