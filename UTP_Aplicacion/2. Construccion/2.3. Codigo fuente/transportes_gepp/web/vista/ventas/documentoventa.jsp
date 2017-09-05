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
        <script src="<%= request.getContextPath()%>/js/ventas/documentoventa.js" type="text/javascript"></script>
        <title>Transportes :: Venta</title>
    </head>
    <body ng-app='DocumentoVentaApp' ng-controller='DocumentoVentaController'>
        <div class="container-fluid">
            <div class='row clearfix'>
                <aside>
                    <ng-include src="'<%= request.getContextPath()%>/vista/cabecera.jsp'"></ng-include>
                </aside>
            </div>
            <div class='row clearfix'>
                <div class="col-sm-12">
                    <section>
                        <form name="formDocumentoVenta">
                            <h1 class="text-center">VENTA ELECTRÓNICA</h1>
                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border">VENTA</legend>
                                <div class="row clearfix">
                                    <div class="form-group col-sm-2">
                                        <label >Tipo:</label>
                                        <select ng-change="cambioTipoDocumentoVenta()" class="form-control" ng-model="documentoVenta.tipoDocumentoVenta" ng-options="tipoDocumentoVenta.nombre for tipoDocumentoVenta in tipoDocumentoVentas track by tipoDocumentoVenta.codigo" required="true" ></select>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Fec. de Emisión</label>
                                        <input type="date" class="form-control" ng-model="documentoVenta.fechaEmision" required="true"    >
                                    </div>
                                    <div class="form-group col-sm-2" ng-hide="ocultarVenta">
                                        <label >Condición</label>
                                        <select ng-change="cambiarCondicionVenta()"  class="form-control" ng-init="condiciones[0].value" ng-model="condicion" ng-options="condicion.nombre for condicion in condiciones track by condicion.codigo"></select>
                                    </div>
                                    <div class="form-group col-sm-2" ng-hide="ocultarNota">
                                        <label >Factura / Boleta</label>
                                        <label  class="form-control" disabled>{{documentoVenta.documentoVenta.puntoVentaSerie.codigo + '-' + documentoVenta.documentoVenta.numero}}</label>
                                    </div>
                                    <div class="form-group col-sm-3" ng-hide="ocultarNotaCredito">
                                        <label >Tipo Nota Crédito</label>
                                        <select class="form-control" ng-model="documentoVenta.tipoNotaCredito" ng-options="tipoNotaCredito.nombre for tipoNotaCredito in tipoNotaCreditos track by tipoNotaCredito.codigo"></select>
                                    </div>
                                    <div class="form-group col-sm-3" ng-hide="ocultarNotaDebito">
                                        <label >Tipo Nota Débito</label>
                                        <select class="form-control" ng-model="documentoVenta.tipoNotaDebito" ng-options="tipoNotaDebito.nombre for tipoNotaDebito in tipoNotaDebitos track by tipoNotaDebito.codigo"></select>
                                    </div>
                                    <div class="form-group col-sm-2" ng-hide="ocultarVenta">
                                        <label >Fec. de Vencto.</label>
                                        <input type="date" class="form-control" ng-model="documentoVenta.fechaVencimiento" ng-disabled="condicion.codigo === 'CON'">
                                    </div>
                                    <div class="form-group col-sm-2" ng-hide="ocultarVenta">
                                        <label >Guia de Remisión</label>
                                        <input type="text" class="form-control" ng-model="documentoVenta.guiaRemision" ng-disabled="condicion.codigo === 'CON'">
                                    </div>
                                    <div class="form-group col-sm-2" ng-hide="ocultarVenta">
                                        <label >Placa</label>
                                        <input type="text" class="form-control" ng-model="documentoVenta.placa" >
                                    </div>
                                    <div class="form-group col-sm-2" ng-hide="ocultarVenta">
                                        <label >Guia de Remisión Transportista</label>
                                        <input type="text" class="form-control" ng-model="documentoVenta.guiaRemisionTransportista" >
                                    </div>
                                    <div class="col-sm-1">
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border">CLIENTE</legend>
                                <div class="row clearfix" ng-hide="ocultarVenta">
                                    <div class="form-group col-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon" id="basic-addon1">
                                                <span class="glyphicon glyphicon-zoom-in"></span>
                                            </span>
                                            <input type="text"  placeholder="Nro. de Documento / Nombre"  ng-model="criterioCliente" ng-keyup="completarCliente(criterioCliente)" class="form-control" />
                                        </div>
                                        <ul class="list-group" ng-hide="ocultarEntidadesAuto">
                                            <li class="list-group-item" ng-repeat="cliente in clientes" ng-click="seleccionarEntidad(cliente)">{{cliente.entidad.documento + " " + cliente.entidad.nombre}}</li>  
                                        </ul> 
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <button class="btn btn-default btn-group-lg" ng-click="cancelarCliente()"><span class="glyphicon glyphicon-remove"></span></button>
                                        <button class="btn btn-default btn-group-lg" ng-click="nuevoCliente()" data-toggle="modal" data-target="#modalCliente"><span class="glyphicon glyphicon-user"></span></button>
                                        <button class="btn btn-default btn-group-lg" ng-click="verCliente()" data-toggle="modal" data-target="#modalCliente"><span class="glyphicon glyphicon-pencil"></span></button>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="form-group col-sm-2">
                                        <label >Tipo de Documento</label>
                                        <select class="form-control" ng-model="documentoVenta.cliente.entidad.tipoDocumentoEntidad" ng-options="tipoDocumentoEntidad.nombre for tipoDocumentoEntidad in tipoDocumentoEntidads track by tipoDocumentoEntidad.codigo" disabled></select>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Nro. de Documento</label>
                                        <input type="text" class="form-control" ng-model="documentoVenta.cliente.entidad.documento" disabled>
                                    </div>
                                    <div class="form-group col-sm-8">
                                        <label >Nombre</label>
                                        <input type="text" class="form-control" ng-model="documentoVenta.cliente.entidad.nombre" disabled>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border">SERVICIOS</legend>
                                <div class="row clearfix" ng-hide="ocultarVenta">
                                    <div class="row clearfix">
                                        <div class="form-group col-sm-12">
                                            <div class="input-group">
                                                <span class="input-group-addon" id="basic-addon1">
                                                    <span class="glyphicon glyphicon-zoom-in"></span>
                                                </span>
                                                <input type="text" class="form-control" placeholder="Nombre" ng-model="criterioProducto" ng-keyup="completarProducto(criterioProducto)">
                                            </div>

                                            <ul class="list-group" ng-hide="ocultarProductosAuto">
                                                <li class="list-group-item" ng-repeat="producto in productos" ng-click="seleccionarProducto(producto)">{{producto.nombre}}</li>  
                                            </ul>
                                        </div>  
                                    </div>
                                    <div class="form-group col-sm-3">
                                        <label >Servicio:</label><br>
                                        {{documentoVentaDetalle.producto.nombre}}
                                    </div>
                                    <div class="form-group col-sm-1">
                                        <label >Precio</label><br>
                                        S/.{{documentoVentaDetalle.producto.precioVenta}}
                                    </div>
                                    <div class="form-group col-sm-1">
                                        <label >Gratuito:</label>
                                        <input type="checkbox" ng-model="documentoVentaDetalle.gratuito" class="form-control">
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Descuento:</label>
                                        <input type="number" ng-model="documentoVentaDetalle.descuentoUnitario" class="form-control" ng-disabled="documentoVentaDetalle.gratuito" ng-checked="cambiarProductoGratuito()">
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Cantidad:</label>
                                        <input type="number" ng-model="documentoVentaDetalle.cantidad" class="form-control">
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <button class="btn btn-default btn-lg" ng-disabled="documentoVentaDetalle.producto === undefined" ng-click="agregarProducto(documentoVentaDetalle)">
                                            <span class="glyphicon glyphicon-plus"></span>
                                        </button>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-sm-12">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Producto</th>
                                                    <th>Gratuito</th>
                                                    <th>Precio Toten</th>
                                                    <th>Descuento</th>
                                                    <th>Cantidad</th>
                                                    <th>Valor Total</th>
                                                    <th>IGV</th>
                                                    <th>Total</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="highlight" ng-repeat="item in documentoVenta.documentoVentaDetalles" ng-click="setSelected($index, item)" ng-class="{activeItem:$index == selectedRow}">
                                                    <td>{{$index + 1}}</td>
                                                    <td>{{item.producto.nombre}}</td>
                                                    <td>{{item.gratuito?'Si':'No'}}</td>
                                                    <td><input ng-disabled="habilitarEdicionPrecioToten" type="number" class="form-control" ng-model="item.precioToten" ng-change="calcularDetalle(item)" ></td>
                                                    <td>{{item.descuentoUnitario| number:2}}</td>
                                                    <td>{{item.cantidad| number:2}}</td>
                                                    <td>{{item.totalGrabado| number:2}}</td>
                                                    <td>{{item.totalIgv| number:2}}</td>
                                                    <td>{{item.total| number:2}}</td>
                                                    <td ng-hide="ocultarVenta">
                                                        <button class="btn btn-default btn-xs" ng-click="eliminarProducto(item)">
                                                            <span class="glyphicon glyphicon-trash"></span>
                                                        </button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border">PAGO</legend>
                                <div class="row clearfix">
                                    <div class="form-group col-sm-2">
                                        <label >Forma:</label>
                                        <select class="form-control" ng-change="cambiarFormaPago()" ng-init="formaPagos[0].value" ng-model="formaPago" ng-options="formaPago.nombre for formaPago in formaPagos track by formaPago.codigo" ng-disabled="condicion.codigo === 'CRE'"></select>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Tipo de Targeta:</label>
                                        <select class="form-control" ng-model="tipoTargeta" ng-options="tipoTargeta.nombre for tipoTargeta in tipoTargetas track by tipoTargeta.codigo" ng-disabled="formaPago.codigo !== 'TAR'"></select>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Banco:</label>
                                        <select class="form-control" ng-model="banco" ng-options="banco.nombre for banco in bancos track by banco.codigo" ng-disabled="formaPago.codigo !== 'DEP'"></select>
                                    </div>
                                    <div class="form-group col-sm-3">
                                        <label >Nro. de Targeta / Depósito:</label>
                                        <input type="text" class="form-control" ng-model="documentoVenta.numeroPago" ng-disabled="formaPago.codigo === 'EFE' || formaPago.codigo === 'CRE'" >
                                    </div>
                                    <div class="col-sm-3">
                                        <label >Gratuito:</label><label class="pull-right">{{documentoVenta.totalGratuito| number:2}}</label><br>
                                        <label >Total Descuentos:</label><label class="pull-right">{{documentoVenta.totalDescuento| number:2}}</label><br>
                                        <label >Grabado:</label><label class="pull-right">{{documentoVenta.totalGrabado| number:2}}</label><br>
                                        <label >IGV:</label><label class="pull-right">{{documentoVenta.totalIgv| number:2}}</label><br>
                                        <label >TOTAL:</label><label class="pull-right">{{documentoVenta.total| number:2}}</label>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border"></legend>
                                <div class="row clearfix">
                                    <div class="form-group col-sm-9">
                                    </div>
                                    <div class="form-group col-sm-3 ">
                                        <div class="pull-right">
                                            <button type="button" class="btn btn-default btn-group-lg" ng-click="cancelar()">Cancelar</button>
                                            <button type="button" class="btn btn-default" 
                                                    ng-disabled="formDocumentoVenta.$invalid || validar()" 
                                                    ng-click="guardar(documentoVenta)">Guardar</button>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </section>
                    <section>
                        <!-- Modal -->
                        <div class="modal fade" id="modalCliente" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">{{mensajeTitulo}}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formCliente">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Tipo Documento</label>
                                                <div class="col-sm-10">
                                                    <select type="text" class="form-control" ng-model="clienteTmp.entidad.tipoDocumentoEntidad" ng-options="tipoDocumentoEntidad.nombre for tipoDocumentoEntidad in tipoDocumentoEntidads track by tipoDocumentoEntidad.codigo" required>
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
                                                    <input type="email" class="form-control" ng-model="clienteTmp.entidad.correoElectronico1" required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >E-mail 2</label>
                                                <div class="col-sm-10">
                                                    <input type="email" class="form-control" ng-model="clienteTmp.entidad.correoElectronico2">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >E-mail 3</label>
                                                <div class="col-sm-10">
                                                    <input type="email" class="form-control" ng-model="clienteTmp.entidad.correoElectronico3">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" >Cancelar</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-disabled="formCliente.$invalid" ng-click="guardarCliente(clienteTmp)">Guardar</button>
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
