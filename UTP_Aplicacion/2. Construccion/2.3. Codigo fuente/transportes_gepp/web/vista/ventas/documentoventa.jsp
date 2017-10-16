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
                        <form name="formDocumentoVenta" ng-submit="guardar(documentoVenta)">
                            <h1 class="text-center">VENTA ELECTRÓNICA</h1>
                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border">VENTA</legend>
                                <div class="row clearfix">
                                    <div class="form-group col-sm-2" 
                                         ng-class="{'has-error' : formDocumentoVenta.tipoDocumentoVenta.$invalid,
                                             'has-success' : formDocumentoVenta.tipoDocumentoVenta.$valid}">
                                        <label >Tipo</label>
                                        <select 
                                            ng-change="cambioTipoDocumentoVenta()" 
                                            name="tipoDocumentoVenta" 
                                            class="form-control" 
                                            ng-model="documentoVenta.tipoDocumentoVenta" 
                                            ng-options="tipoDocumentoVenta.nombre for tipoDocumentoVenta in tipoDocumentoVentas track by tipoDocumentoVenta.codigo" 
                                            ng-required="requiredTipoDocumento" >
                                        </select>                                     
                                    </div>
                                    <div class="form-group col-sm-2"
                                         ng-class="{'has-error' : formDocumentoVenta.fechaEmision.$invalid,
                                             'has-success' : formDocumentoVenta.fechaEmision.$valid}">
                                        <label >Fec. Emisión</label>
                                        <input type="date" class="form-control" name="fechaEmision" ng-model="documentoVenta.fechaEmision" ng-required="requiredFechaEmision" >
                                    </div>
                                    <div class="form-group col-sm-2" ng-hide="ocultarVenta"
                                         ng-class="{'has-error' : formDocumentoVenta.condicion.$invalid,
                                             'has-success' : formDocumentoVenta.condicion.$valid}">
                                        <label >Condición</label>
                                        <select ng-change="cambiarCondicionVenta()"  class="form-control" name="condicion" ng-model="condicion" ng-options="condicion.nombre for condicion in condiciones track by condicion.codigo" ng-required="requiredCondicion" ></select>
                                    </div>
                                    <div class="form-group col-sm-2" 
                                         ng-hide="ocultarNota"
                                         ng-class="{'has-error' : formDocumentoVenta.ventaReferencia.$invalid,
                                             'has-success' : formDocumentoVenta.ventaReferencia.$valid}">
                                        <label>Factura / Boleta</label>
                                        <input type="text" name="ventaReferencia"  class="form-control" disabled ng-value ="documentoVenta.documentoVenta.puntoVentaSerie.codigo + '-' + documentoVenta.documentoVenta.numero" ng-required="requiredVentaReferencia">
                                    </div>
                                    <div class="form-group col-sm-3" 
                                         ng-hide="ocultarNotaCredito" 
                                         ng-class="{'has-error' : formDocumentoVenta.tipoNotaCredito.$invalid,
                                             'has-success' : formDocumentoVenta.tipoNotaCredito.$valid}">
                                        <label >Tipo Nota Crédito</label>
                                        <select class="form-control" 
                                                name="tipoNotaCredito" 
                                                ng-model="documentoVenta.tipoNotaCredito" 
                                                ng-options="tipoNotaCredito.nombre for tipoNotaCredito in tipoNotaCreditos track by tipoNotaCredito.codigo" 
                                                ng-required="requiredTipoNotaCredito"
                                                ng-change="cambioTipoNota()"></select>
                                    </div>
                                    <div class="form-group col-sm-3" 
                                         ng-hide="ocultarNotaDebito" 
                                         ng-class="{'has-error' : formDocumentoVenta.tipoNotaDebito.$invalid,
                                             'has-success' : formDocumentoVenta.tipoNotaDebito.$valid}">
                                        <label >Tipo Nota Débito</label>
                                        <select class="form-control" name="tipoNotaDebito" ng-model="documentoVenta.tipoNotaDebito" ng-options="tipoNotaDebito.nombre for tipoNotaDebito in tipoNotaDebitos track by tipoNotaDebito.codigo" ng-required="requiredTipoNotaDebito"></select>
                                    </div>
                                    
                                    <div class="form-group col-sm-3" 
                                         ng-hide="ocultarNota" 
                                         ng-class="{'has-error' : formDocumentoVenta.descripcionNota.$invalid,
                                             'has-success' : formDocumentoVenta.descripcionNota.$valid}">
                                        <label >Descripción</label>
                                        <textarea
                                            class="form-control" 
                                                name="descripcionNota" 
                                                ng-model="documentoVenta.descripcionNota" 
                                                ng-required="requiredDescripcionNota"
                                            ></textarea>
                                    </div>
                                    <div class="form-group col-sm-2" 
                                         ng-hide="ocultarVenta" 
                                         ng-class="{'has-error' : formDocumentoVenta.fechaVencimiento.$invalid,
                                             'has-success' : formDocumentoVenta.fechaVencimiento.$valid}">
                                        <label >Fec. Vencto.</label>
                                        <input type="date" class="form-control" name="fechaVencimiento" ng-model="documentoVenta.fechaVencimiento" ng-disabled="condicion.codigo === 'CON'" ng-required="requiredFechaVencimiento">
                                    </div>
                                    <div class="form-group col-sm-2" 
                                         ng-hide="ocultarVenta"
                                         ng-class="{'has-error' : formDocumentoVenta.guiaRemision.$invalid,
                                             'has-success' : formDocumentoVenta.guiaRemision.$valid}">
                                        <label >Guia Remisión</label>
                                        <input type="text" class="form-control" name="guiaRemision" ng-model="documentoVenta.guiaRemision"  ng-required="requiredGuiaRemision">
                                    </div>
                                    <div class="form-group col-sm-2" 
                                         ng-hide="ocultarVenta"
                                         ng-class="{'has-error' : formDocumentoVenta.placa.$invalid,
                                             'has-success' : formDocumentoVenta.placa.$valid}">
                                        <label >Placa</label>
                                        <select 
                                            ng-model="vehiculo"
                                            class="form-control" 
                                            name="placa"  
                                            ng-required="requiredPlaca"
                                            ng-change="cambioPlaca()"
                                            ng-options="vehiculo.placa for vehiculo in vehiculos track by vehiculo.placa">
                                            <option value="" selected></option></select>
                                    </div>
                                    <div class="form-group col-sm-2" 
                                         ng-hide="ocultarVenta" 
                                         ng-class="{'has-error' : formDocumentoVenta.guiaRemisionTransportista.$invalid,
                                             'has-success' : formDocumentoVenta.guiaRemisionTransportista.$valid}">
                                        <label >Guia Transportista</label>
                                        <input 
                                            type="text" 
                                            class="form-control" 
                                            name="guiaRemisionTransportista" 
                                            ng-model="documentoVenta.guiaRemisionTransportista" 
                                            ng-required="requiredGuiaRemisionTransportista">                                     
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
                                            <li class="list-group-item" ng-repeat="cliente in clientes" ng-click="seleccionarEntidad(cliente)">{{cliente.documento + " " + cliente.nombre}}</li>  
                                        </ul> 
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <button type="button" class="btn btn-default btn-group-lg" ng-click="cancelarCliente()"><span class="glyphicon glyphicon-remove"></span></button>
                                        <button type="button" class="btn btn-default btn-group-lg" ng-click="nuevoCliente()" data-toggle="modal" data-target="#modalCliente"><span class="glyphicon glyphicon-user"></span></button>
                                        <button type="button" class="btn btn-default btn-group-lg" ng-click="verCliente(documentoVenta.cliente)" ng-disabled="documentoVenta.cliente === undefined" data-toggle="modal" data-target="#modalCliente"><span class="glyphicon glyphicon-pencil"></span></button>
                                    </div>
                                </div>
                                <div class="row clearfix" 
                                     ng-class="{'has-error' : (documentoVenta.cliente === undefined && requiredCliente),
                                             'has-success' : (documentoVenta.cliente !== undefined && requiredCliente) || !requiredCliente}">
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
                                    <div class="form-group col-sm-3"
										ng-class="{'has-error' : (documentoVentaDetalle.producto === undefined),
										'has-success' : (documentoVentaDetalle.producto !== undefined)}">
                                        <label >Nombre:</label><br>
                                        <select 
										class="form-control" 
										ng-model="documentoVentaDetalle.producto" 
										ng-options="producto.nombre for producto in productos track by producto.id" 
										ng-change="cambioServicio()"
										></select>
                                    </div>
                                    <div class="form-group col-sm-2">
                                        <label >Precio</label><br>
                                        <input type="number" ng-model="documentoVentaDetalle.precioToten" class="form-control" step="0.01">
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
                                        <button type="button" class="btn btn-default btn-lg" ng-disabled="documentoVentaDetalle.producto === undefined" ng-click="agregarProducto(documentoVentaDetalle)">
                                            <span class="glyphicon glyphicon-plus"></span>
                                        </button>
                                    </div>
                                </div>
                                <div class="row clearfix" >
                                    <div class="col-sm-12" ng-class="{'has-error' : documentoVenta.documentoVentaDetalles === undefined}">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Nombre</th>
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
                                                <tr class="highlight" ng-repeat="item in documentoVenta.documentoVentaDetalles" ng-click="setSelected($index, item)" ng-class="{activeItem:$index == selectedRow}" >
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
                                                        <button type="button" class="btn btn-default btn-xs" ng-click="eliminarProducto(item)">
                                                            <span class="glyphicon glyphicon-trash"></span>
                                                        </button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <p ng-show="documentoVenta.documentoVentaDetalles === undefined" 
                                           class="help-block">Se necesita como mínimo un servicio para crear la Venta.</p>    
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="scheduler-border">
                                <legend class="scheduler-border">PAGO</legend>
                                <div class="row clearfix">
                                    <div class="form-group col-sm-2"
                                         ng-class="{'has-error' : formDocumentoVenta.formaPago.$invalid,
                                             'has-success' : formDocumentoVenta.formaPago.$valid}">
                                        <label >Forma:</label>
                                        <select
                                            class="form-control"
                                            name="formaPago"
                                            ng-change="cambiarFormaPago()" 
                                            ng-init="formaPagos[0].value"
                                            ng-model="formaPago" 
                                            ng-options="formaPago.nombre for formaPago in formaPagos track by formaPago.codigo" 
                                            ng-disabled="condicion.codigo === 'CRE'"
                                            ng-required="requiredFormaPago"
                                            ></select>
                                    </div>
                                    <div class="form-group col-sm-2"
                                         ng-class="{'has-error' : formDocumentoVenta.tipoTarjeta.$invalid,
                                             'has-success' : formDocumentoVenta.tipoTarjeta.$valid}">
                                        <label >Tipo de Tarjeta:</label>
                                        <select 
                                            class="form-control"
                                            name="tipoTarjeta"
                                            ng-model="tipoTargeta"
                                            ng-options="tipoTargeta.nombre for tipoTargeta in tipoTargetas track by tipoTargeta.codigo"
                                            ng-disabled="formaPago.codigo !== 'TAR'"
                                            ng-required="requiredTipoTarjeta"
                                            ></select>
                                    </div>
                                    <div class="form-group col-sm-2"
                                         ng-class="{'has-error' : formDocumentoVenta.banco.$invalid,
                                             'has-success' : formDocumentoVenta.banco.$valid}">
                                        <label >Banco:</label>
                                        <select 
                                            class="form-control" 
                                            name="banco"
                                            ng-model="banco" 
                                            ng-options="banco.nombre for banco in bancos track by banco.codigo" 
                                            ng-disabled="formaPago.codigo !== 'DEP'"
                                            ng-required="requiredBanco"
                                            ></select>
                                    </div>
                                    <div class="form-group col-sm-3"
                                         ng-class="{'has-error' : formDocumentoVenta.numeroPago.$invalid,
                                             'has-success' : formDocumentoVenta.numeroPago.$valid}">
                                        <label >Nro. de Tarjeta / Depósito:</label>
                                        <input type="text" 
                                               class="form-control"
                                               name="numeroPago"
                                               ng-model="documentoVenta.numeroPago" 
                                               ng-disabled="formaPago.codigo === 'EFE' || formaPago.codigo === 'CRE'"
                                               ng-required="requiredNumeroPago">
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
                                            <button type="submit" class="btn btn-default" ng-disabled="formDocumentoVenta.$invalid || documentoVenta.documentoVentaDetalles === undefined || (documentoVenta.cliente === undefined && requiredCliente)">Guardar</button>
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
                                        <h4 class="modal-title">{{mensajeTituloCliente}}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="formCliente">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Tipo Documento</label>
                                                <div class="col-sm-10">
                                                    <select
                                                        class="form-control" 
                                                        ng-model="clienteTmp.entidad.tipoDocumentoEntidad" 
                                                        ng-options="tipoDocumentoEntidad.nombre for tipoDocumentoEntidad in tipoDocumentoEntidads track by tipoDocumentoEntidad.codigo" 
                                                        ng-disabled="true">
                                                    </select>    
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Documento</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" name="username" ng-model="clienteTmp.entidad.documento"   ng-required>
                                                </div>
                                                <div class="col-sm-1">
                                                    <button type="button" 
                                                            class="btn btn-default" 
                                                            ng-click="buscarPadron(clienteTmp)"
                                                            ng-disabled="clienteTmp.entidad.tipoDocumentoEntidad.codigo === '1'">
                                                        <span class="glyphicon glyphicon-search">
                                                        </span>
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Nombre</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="username" ng-model="clienteTmp.entidad.nombre" ng-required>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Direccion</label>
                                                <div class="col-sm-10">
                                                    <textarea class="form-control" ng-model="clienteTmp.entidad.direccion" ng-required></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >E-mail 1</label>
                                                <div class="col-sm-10">
                                                    <input type="email" class="form-control" ng-model="clienteTmp.entidad.correoElectronico1" ng-required>
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
                        <!-- Modal -->
                        <div class="modal fade" id="modalConfirmacion" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Confirmación de Venta</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h1>{{mensaje}}</h1>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="cancelar()" >Cancelar</button>
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
