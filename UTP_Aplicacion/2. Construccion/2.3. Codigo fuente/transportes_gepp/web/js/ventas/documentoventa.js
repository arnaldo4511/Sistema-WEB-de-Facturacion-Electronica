/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var DocumentoVentaApp = angular.module("DocumentoVentaApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
DocumentoVentaApp.controller("DocumentoVentaController", ['$scope', '$http', '$filter', '$window', '$location', function ($scope, $http, $filter, $window, $location) {
        //console.log("s:"+$location.search());
        $scope.parametros = {};
        $window.location.search.replace(/\?/, '').split('&').map(function (o) {
            if (o !== "") {
                $scope.parametros[o.split('=')[0]] = o.split('=')[1]
            }
        });
        $scope.sesion = {};
        $scope.documentoVenta = {};
        $scope.producto = {};
        $scope.formaPago = {};
        $scope.condicion = {};
        $scope.vehiculo = {};
        $scope.documentoVentaDetalle = {};
        $scope.tipoTargeta = {};
        $scope.banco = {};


        $scope.productos = [];
        $scope.usuarios = [];
        $scope.roles = [];
        $scope.puntoVentas = [];
        $scope.documentoVentaDetalles = [];

        /*$scope.tipoDocumentoEntidades = [
         {'codigo': '1', 'nombre': 'DNI'},
         {'codigo': '9', 'nombre': 'RUC'}];*/
        $scope.condiciones = [
            {'codigo': 'CON', 'nombre': 'CONTADO'},
            {'codigo': 'CRE', 'nombre': 'CRÉDITO'}];
        $scope.formaPagos = [
            {'codigo': 'EFE', 'nombre': 'EFECTIVO'},
            {'codigo': 'TAR', 'nombre': 'TARJETA'},
            {'codigo': 'DEP', 'nombre': 'DEPÓSITO'},
            {'codigo': 'CRE', 'nombre': 'CRÉDITO'}];
        $scope.tipoTargetas = [
            {'codigo': 'VIS', 'nombre': 'VISA'},
            {'codigo': 'MAS', 'nombre': 'MASTERCAD'}];
        $scope.bancos = [
            {'codigo': 'BCP', 'nombre': 'BCP'},
            {'codigo': 'BBVA', 'nombre': 'BBVA'},
            {'codigo': 'BNA', 'nombre': 'BN'},
            {'codigo': 'SCO', 'nombre': 'SCOTIABANCK'},
            {'codigo': 'INT', 'nombre': 'INTERBANCK'}];
        $scope.documentoVenta.tipoDocumentoVenta = {'codigo': '01'}
        //$scope.documentoVenta.tipoDocumentoVenta = {'codigo': '01', 'nombre': 'FACTURA'};
        $scope.condicion = {'codigo': 'CON', 'nombre': 'CONTADO'};
        $scope.formaPago = {'codigo': 'EFE', 'nombre': 'EFECTIVO'};

        $scope.documentoVenta.fechaEmision = new Date();
        //alert($filter('date')(Date.now(), 'dd-MM-yyyy'));
        //$scope.documentoVenta.fechaEmision = $filter('date')(Date.now(), 'dd-MM-yyyy');//'yyyy-MM-dd'
        $scope.clientes = [];

        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            $scope.sesion = response.data;
            $scope.roles = $scope.sesion.roles;
            $scope.puntoVentas = $scope.sesion.puntoVentas;
            $scope.tipoDocumentoVentas = $scope.sesion.tipoDocumentoVentas;
            $scope.tipoNotaCreditos = $scope.sesion.tipoNotaCreditos;
            $scope.tipoNotaDebitos = $scope.sesion.tipoNotaDebitos;
            $scope.tipoDocumentoEntidads = $scope.sesion.tipoDocumentoEntidads;
            $scope.vehiculos = $scope.sesion.vehiculos;
            //console.log($scope.sesion.productos);
            $scope.productos = $scope.sesion.productos;
            $scope.producto1 = $scope.productos[0];
            $scope.producto2 = $scope.productos[1];

            if ($scope.parametros.id !== undefined) {
                $scope.tipoDocumentoVentaSelected = $filter('filter')($scope.tipoDocumentoVentas, {codigo: $scope.parametros.codigoTipo}, true)[0];
                $scope.documentoVenta.tipoDocumentoVenta = $scope.tipoDocumentoVentaSelected;
                $scope.documentoVenta.documentoVenta = {};
                $scope.cambioTipoDocumentoVenta();
                if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "07") {
                    $scope.documentoVenta.tipoNotaCredito = $scope.tipoNotaCreditos[0];
                } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "08") {
                    $scope.documentoVenta.tipoNotaDebito = $scope.tipoNotaDebitos[0];
                }
                $http({method: 'POST', url: '/transportes_gepp/controlador/documentoventa/buscar/' + $scope.parametros.id
                }).then(function success(response) {
                    $scope.documentoVentaRef = response.data;
                    $scope.documentoVenta.cliente = $scope.documentoVentaRef.cliente;
                    $scope.documentoVenta.documentoVenta = $scope.documentoVentaRef;
                    $scope.cambioTipoNota();
                }, function error(response) {
                });
            } else {
                $scope.vehiculo = $scope.sesion.vehiculos[0];
                $scope.documentoVenta.guiaRemisionTransportista = $scope.vehiculo.serie + "-";
            }
        }, function myError(response) {
        });

        $scope.cambioTipoDocumentoVenta = function () {
            if ($scope.documentoVenta.tipoDocumentoVenta === undefined) {
                return;
            }
            if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "01") {
                $scope.habilitarEdicionPrecioToten = true;
                $scope.ocultarVenta = false;
                $scope.ocultarNotaCredito = true;
                $scope.ocultarNotaDebito = true;
                $scope.ocultarNota = true;
            } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "03") {
                $scope.habilitarEdicionPrecioToten = true;
                $scope.ocultarVenta = false;
                $scope.ocultarNotaCredito = true;
                $scope.ocultarNotaDebito = true;
                $scope.ocultarNota = true;
            } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "07") {
                $scope.ocultarVenta = true;
                $scope.ocultarNotaCredito = false;
                $scope.ocultarNotaDebito = true;
                $scope.ocultarNota = false;
                $scope.habilitarEdicionPrecioToten = false;
            } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "08") {
                $scope.ocultarVenta = true;
                $scope.ocultarNotaCredito = true;
                $scope.ocultarNotaDebito = false;
                $scope.ocultarNota = false;
                $scope.habilitarEdicionPrecioToten = false;
            }
            $scope.cambiarCamposRequeridos();
        };
        $scope.cancelar = function () {
            $window.location.href = 'documentoventas.jsp';
        }
        $scope.validar = function () {
            //var valPro=($scope.documentoVenta.documentoVentaDetalles.length === 0);
            //var valCli=($scope.documentoVenta.tipoDocumentoVenta.codigo === '03' && $scope.condicion.codigo==='CRE' && $scope.documentoVenta.total<700 && $scope.documentoVenta.cliente === {});
            //return ( valPro && valCli);
        };
        $scope.cancelarCliente = function () {
            $scope.clientes = [];
            $scope.ocultarEntidadesAuto = false;
            $scope.documentoVenta.cliente = undefined;
            $scope.criterioCliente = '';
        };
        $scope.guardar = function (documentoVenta) {
            documentoVenta.empresa = $scope.sesion.usuario.empresa;
            documentoVenta.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            documentoVenta.formaPago = $scope.formaPago.codigo;
            documentoVenta.condicion = $scope.condicion.codigo;
            documentoVenta.moneda = {'codigo': 'PEN'};
            documentoVenta.estadoDocumentoVenta = {'codigo': 'PES'};
            documentoVenta.puntoVenta = $scope.sesion.usuario.puntoVenta;
            //documentoVenta.puntoVentaSerie = {'codigo': 'F001'};
            documentoVenta.tipoTargeta = ($scope.tipoTargeta === undefined ? undefined : $scope.tipoTargeta.codigo);
            documentoVenta.banco = ($scope.banco === undefined ? undefined : $scope.banco.codigo);
            documentoVenta.placa = (($scope.vehiculo === undefined || $scope.vehiculo === null) ? undefined : $scope.vehiculo.placa);
            //$scope.documentoVenta.documentoVenta=undefined;
            if (documentoVenta.tipoDocumentoVenta.codigo === "07" || documentoVenta.tipoDocumentoVenta.codigo === "08")
            {
                documentoVenta.documentoVentaDetalles[0].producto = null;
            } else {

                documentoVenta.tipoProducto = documentoVenta.documentoVentaDetalles[0].producto.tipo;
            }
            //documentoVenta.numero = '00000000';
            console.log(documentoVenta);
            //return;
            //return;
            //usuario.usuarioByIdDocumentoVentaCreacion = $scope.sesion.usuario;
            //usuario.empresa = $scope.sesion.usuario.empresa;
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/crear',
                data: documentoVenta
            }).then(function success(response) {
                console.log(response.data.id);
                if (response.data.id > 0) {
                    /*
                     $http({
                     method: 'GET',
                     url: '/transportes_gepp/controlador/documentoventa/descargar/' + response.data.id
                     }).then(function success(response) {
                     var blob = response.data;
                     var objectUrl = URL.createObjectURL(blob);
                     $window.open(objectUrl);
                     
                     }, function error(response) {
                     //alert("Hubo un error");
                     //console.log(response.data);
                     });*/
                    $window.open('/transportes_gepp/controlador/documentoventa/descargar/' + response.data.id, '_blank');
                    //$scope.cancelar();

                    alert("Guardado Con Exito");
                }
            }, function error(response) {
                alert("Hubo un error");
                //console.log(response.data);
            });
        };
        $scope.cambiarCondicionVenta = function () {
            if ($scope.condicion.codigo === 'CON') {
                console.log($scope.documentoVenta.fechaVencimiento);
                $scope.documentoVenta.fechaVencimiento = null;
                $scope.documentoVenta.fechaVencimiento = undefined;
                console.log($scope.documentoVenta.fechaVencimiento);
                $scope.formaPago = {'codigo': 'EFE'};
            } else {
                $scope.formaPago = {'codigo': 'CRE'};
                $scope.tipoTargeta = undefined;
                $scope.banco = undefined;
                $scope.documentoVenta.numeroPago = undefined;
            }
            $scope.cambiarCamposRequeridos();
        };
        $scope.cambiarFormaPago = function () {
            if ($scope.formaPago.codigo === 'EFE') {
                $scope.tipoTargeta = {};
                $scope.banco = {};
                $scope.documentoVenta.numeroPago = '';
            } else if ($scope.formaPago.codigo === 'TAR') {
                $scope.tipoTargeta = {'codigo': 'VIS'};
                $scope.banco = undefined;
                $scope.documentoVenta.numeroPago = '';
            } else if ($scope.formaPago.codigo === 'DEP') {
                $scope.tipoTargeta = undefined;
                $scope.banco = {'codigo': 'BCP'};
                $scope.documentoVenta.numeroPago = '';
            } else if ($scope.formaPago.codigo === 'CRE') {
                $scope.tipoTargeta = undefined;
                $scope.banco = undefined;
                $scope.documentoVenta.numeroPago = undefined;
            }
            $scope.cambiarCamposRequeridos();
        };
        $scope.cambiarProductoGratuito = function () {
            if ($scope.documentoVentaDetalle.gratuito) {
                $scope.documentoVentaDetalle.descuentoUnitario = 0;
            }
        };

        $scope.completarCliente = function (criterio) {
            if (criterio === undefined || criterio.trim() === "")
            {
                $scope.clientes = [];
                return;
            }
            var codigo = '6';
            if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "03") {
                codigo = '1';
            }
            //console.log(criterio);
            $http({method: 'GET', url: '/transportes_gepp/controlador/cliente/autocompletar/' + criterio + "/" + codigo
            }).then(function mySucces(response) {
                console.log(response.data);
                $scope.clientes = response.data;
                $scope.ocultarEntidadesAuto = false;
            }, function myError(response) {

            });
        };
        $scope.criterioCliente = '';
        $scope.seleccionarEntidad = function (item) {
            $scope.documentoVenta.cliente = {};
            $scope.documentoVenta.cliente.entidad = {};
            $scope.documentoVenta.cliente.entidad.tipoDocumentoEntidad = {};
            $scope.documentoVenta.cliente.id = item.id;
            $scope.documentoVenta.cliente.entidad.nombre = item.nombre;
            $scope.documentoVenta.cliente.entidad.documento = item.documento;
            $scope.documentoVenta.cliente.entidad.tipoDocumentoEntidad = {'codigo': item.codigoTipoDocumentoEntidad};
            $scope.ocultarEntidadesAuto = true;
            $scope.criterioCliente = item.nombre;
        };
        $scope.completarProducto = function (criterio) {
            //console.log(criterio);
            if (criterio === undefined || criterio.trim() === "")
            {
                $scope.productos = [];
                return;
            }
            $http({method: 'GET', url: '/transportes_gepp/controlador/producto/autocompletar/' + criterio
            }).then(function mySucces(response) {
                //console.log(response.data);
                $scope.productos = response.data;
                $scope.ocultarProductosAuto = false;
            }, function myError(response) {

                //console.log(response);
            });
        };
        $scope.cambioServicio = function () {
            //$scope.documentoVentaDetalle.producto = item;
            $scope.documentoVentaDetalle.precioToten = $scope.documentoVentaDetalle.producto.precioVenta;
            $scope.documentoVentaDetalle.descuentoUnitario = 0;
            $scope.documentoVentaDetalle.cantidad = 1;
        };
        $scope.criterioProducto = '';
        $scope.seleccionarProducto = function (item) {
            //console.log(item);
            $scope.documentoVentaDetalle.producto = item;
            $scope.documentoVentaDetalle.precioToten = item.precioVenta;
            $scope.documentoVentaDetalle.descuentoUnitario = 0;
            $scope.documentoVentaDetalle.cantidad = 1;
            //$scope.criterioProducto = item.nombre;
            //$scope.ocultarProductosAuto = true;
        };
        //$scope.documentoVenta.documentoVentaDetalles = [];
        $scope.agregarProducto = function (item) {
            if ($scope.documentoVenta.documentoVentaDetalles === undefined) {
                $scope.documentoVenta.documentoVentaDetalles = [];
            }
            //
            item.precioToten = item.precioToten;
            item.unidad = item.producto.unidad;
            if (item.gratuito) {
                item.fleteUnitario = 0;
                item.bonificacionUnitario = 0;
                item.precioUnitario = 0;
                item.valorUnitario = 0;
                item.costoUnitario = 0;
                item.precioFinal = 0;
                item.codigoPrecio = '02';
                item.codigoIgv = '11';
                item.totalGratuito = item.precioToten * item.cantidad;
            } else {
                item.fleteUnitario = 0;
                item.bonificacionUnitario = 0;
                item.precioUnitario = (item.precioToten + item.fleteUnitario + item.bonificacionUnitario)
                item.valorUnitario = item.precioUnitario / 1.18;
                item.costoUnitario = (item.fleteUnitario + item.bonificacionUnitario) - item.descuentoUnitario;
                item.precioFinal = item.precioToten + item.costoUnitario;
                item.codigoPrecio = '01';
                item.codigoIgv = '10';
                item.totalGratuito = 0;
            }
            //
            item.ventaBruta = item.valorUnitario * item.cantidad;
            item.totalDescuento = item.descuentoUnitario * item.cantidad;
            item.descuento = item.totalDescuento > 0 ? true : false;
            item.total = item.precioFinal * item.cantidad;
            item.totalGrabado = item.ventaBruta;
            item.totalIgv = item.total - item.totalGrabado;
            item.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            $scope.documentoVenta.documentoVentaDetalles.push(item);
            $scope.calcularMontos();
            $scope.nuevoDocumentoVentaDetalle();
        };
        $scope.nuevoDocumentoVentaDetalle = function () {
            $scope.criterioProducto = '';
            $scope.documentoVentaDetalle = {};
            $scope.documentoVentaDetalle.descuentoUnitario = 0;
            $scope.documentoVentaDetalle.cantidad = 1;
        };
        $scope.calcularMontos = function () {
            $scope.documentoVenta.totalGratuito = 0;
            $scope.documentoVenta.totalDescuento = 0;
            $scope.documentoVenta.totalGrabado = 0;
            $scope.documentoVenta.totalIgv = 0;
            $scope.documentoVenta.total = 0;
            angular.forEach($scope.documentoVenta.documentoVentaDetalles, function (value) {
                $scope.documentoVenta.totalGratuito += value.totalGratuito;
                $scope.documentoVenta.totalDescuento += value.totalDescuento;
                $scope.documentoVenta.totalGrabado += value.totalGrabado;
                $scope.documentoVenta.totalIgv += value.totalIgv;
                $scope.documentoVenta.total += value.total;
            });
        };
        $scope.eliminarProducto = function (item) {
            //$scope.users.splice($scope.users.indexOf($scope.clickedUser),1);
            $scope.documentoVenta.documentoVentaDetalles.splice(item, 1);
            $scope.calcularMontos();
            if ($scope.documentoVenta.documentoVentaDetalles.length === 0) {
                $scope.documentoVenta.documentoVentaDetalles = undefined;
            }
        };
        $scope.setSelected = function (row, item) {
            $scope.selectedRow = row;
        };
        $scope.calcularDetalle = function (item) {
            item.precioUnitario = (item.precioToten + item.fleteUnitario + item.bonificacionUnitario)
            item.valorUnitario = item.precioUnitario / 1.18;
            item.costoUnitario = (item.fleteUnitario + item.bonificacionUnitario) - item.descuentoUnitario;
            item.precioFinal = item.precioToten + item.costoUnitario;
            item.ventaBruta = item.valorUnitario * item.cantidad;
            item.totalDescuento = item.descuentoUnitario * item.cantidad;
            item.descuento = item.totalDescuento > 0 ? true : false;
            item.total = item.precioFinal * item.cantidad;
            item.totalGrabado = item.ventaBruta;
            item.totalIgv = item.total - item.totalGrabado;
            $scope.calcularMontos();
        };
        $scope.clienteTmp = {};
        $scope.nuevoCliente = function () {
            $scope.mensajeTituloCliente = "Crear Cliente";
            $scope.clienteTmp = {};
            $scope.clienteTmp.id = 0;
            $scope.clienteTmp.entidad = {};
            $scope.clienteTmp.entidad.ubigeo = {'codigo': '040101'};
            $scope.clienteTmp.entidad.tipoDocumentoEntidad = {};
            //alert($scope.documentoVenta.tipoDocumentoVenta.codigo);
            if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "03") {
                $scope.clienteTmp.entidad.tipoDocumentoEntidad = {'codigo': '1'};
            } else {
                $scope.clienteTmp.entidad.tipoDocumentoEntidad = {'codigo': '6'};
            }
            //alert($scope.clienteTmp.entidad.tipoDocumentoEntidad.codigo);
            //$scope.clienteTmp.entidad.tipoDocumentoEntidad = $scope.tipoDocumentoEntidads[0];
            //console.log("$scope.entidadTmp.id " + $scope.entidadTmp.id);
        };
        $scope.verCliente = function (item) {
            $scope.clienteTmp = {};
            $http({method: 'POST', url: '/transportes_gepp/controlador/cliente/buscar/' + item.id
            }).then(function mySucces(response) {
                $scope.clienteTmp = response.data;
                $scope.mensajeTituloCliente = "Editar Cliente";
            }, function myError(response) {
            });
        };
        $scope.guardarCliente = function (cliente) {
            if (cliente.id === 0) {
                $scope.crearCliente(cliente);
            } else {
                $scope.editarCliente(cliente);
            }
        };
        $scope.crearCliente = function (cliente) {
            cliente.entidad.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            cliente.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            console.log(cliente);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/crear',
                data: cliente
            }).then(function success(response) {
                $scope.documentoVenta.cliente = response.data;
                $scope.criterioCliente = "";
                //console.log(response.data);
                //$scope.listar();
            }, function error(response) {
                //console.log(response.data);
            });
        };
        $scope.editarCliente = function (cliente) {
            cliente.entidad.usuarioByIdUsuarioModificacion = $scope.sesion.usuario;
            cliente.usuarioByIdUsuarioModificacion = $scope.sesion.usuario;
            console.log("cliente " + cliente.usuarioByIdUsuarioModificacion);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/editar',
                data: cliente
            }).then(function success(response) {
                //console.log(response.data);
                //$scope.listar();
                $scope.documentoVenta.cliente = response.data;
                $scope.criterioCliente = "";
            }, function error(response) {
                //console.log(response.data);
            });
        };
        $scope.buscarPadron = function (cliente) {
            $http({
                method: 'GET',
                url: '/gepp_erp/srvContribuyente?parAccion=buscarContribuyente&criterio=' + cliente.entidad.documento
            }).then(function success(response) {
                cliente.entidad.nombre = response.data.RESULT.con_nombre;
                var ubigeo = {"codigo": response.data.RESULT.con_ubigeo};
                cliente.entidad.ubigeo = ubigeo;
                cliente.entidad.direccion = response.data.RESULT.con_direccion;
                cliente.entidad.tipoDocumentoEntidad = {"codigo": '6'}
            }, function error(response) {
                //console.log(response.data);
            });
        };

        $scope.cambiarCamposRequeridos = function () {
            if ($scope.documentoVenta.tipoDocumentoVenta === undefined) {
                return;
            }
            if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "01") {
                $scope.requiredTipoDocumento = true;
                $scope.requiredFechaEmision = true;
                $scope.requiredCondicion = true;
                if ($scope.condicion.codigo === "CON") {
                    $scope.requiredFechaVencimiento = false;
                    $scope.requiredFormaPago = true;
                    if ($scope.formaPago.codigo === "EFE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    } else if ($scope.formaPago.codigo === "TAR") {
                        $scope.requiredTipoTarjeta = true;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "DEP") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = true;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "CRE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    }
                } else if ($scope.condicion.codigo === "CRE") {
                    $scope.requiredFechaVencimiento = true;
                    $scope.requiredFormaPago = false;
                    $scope.requiredTipoTarjeta = false;
                    $scope.requiredBanco = false;
                    $scope.requiredNumeroPago = false;
                }
                $scope.requiredGuiaRemision = false;
                $scope.requiredVentaReferencia = false;
                $scope.requiredTipoNotaCredito = false;
                $scope.requiredTipoNotaDebito = false;
                $scope.requiredDescripcionNota = false;
                $scope.requiredPlaca = false;
                $scope.requiredGuiaRemisionTransportista = false;
                $scope.requiredCliente = true;
            } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "03") {
                $scope.requiredTipoDocumento = true;
                $scope.requiredFechaEmision = true;
                $scope.requiredCondicion = true;
                if ($scope.condicion.codigo === "CON") {
                    $scope.requiredFechaVencimiento = false;
                    $scope.requiredFormaPago = true;
                    if ($scope.formaPago.codigo === "EFE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    } else if ($scope.formaPago.codigo === "TAR") {
                        $scope.requiredTipoTarjeta = true;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "DEP") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = true;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "CRE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    }
                } else if ($scope.condicion.codigo === "CRE") {
                    $scope.requiredFechaVencimiento = true;
                    $scope.requiredFormaPago = false;
                    $scope.requiredTipoTarjeta = false;
                    $scope.requiredBanco = false;
                    $scope.requiredNumeroPago = false;
                }
                $scope.requiredGuiaRemision = false;
                $scope.requiredVentaReferencia = false;
                $scope.requiredTipoNotaCredito = false;
                $scope.requiredTipoNotaDebito = false;
                $scope.requiredDescripcionNota = false;
                $scope.requiredPlaca = false;
                $scope.requiredGuiaRemisionTransportista = false;
                if ($scope.documentoVenta.total > 600) {
                    $scope.requiredCliente = true;
                } else {
                    $scope.requiredCliente = false;
                }
            } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "07") {
                $scope.requiredTipoDocumento = true;
                $scope.requiredFechaEmision = true;
                $scope.requiredCondicion = true;
                if ($scope.condicion.codigo === "CON") {
                    $scope.requiredFechaVencimiento = false;
                    $scope.requiredFormaPago = true;
                    if ($scope.formaPago.codigo === "EFE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    } else if ($scope.formaPago.codigo === "TAR") {
                        $scope.requiredTipoTarjeta = true;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "DEP") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = true;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "CRE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    }
                } else if ($scope.condicion.codigo === "CRE") {
                    $scope.requiredFechaVencimiento = false;
                    $scope.requiredFormaPago = false;
                    $scope.requiredTipoTarjeta = false;
                    $scope.requiredBanco = false;
                    $scope.requiredNumeroPago = false;
                }
                $scope.requiredGuiaRemision = false;
                $scope.requiredVentaReferencia = false;
                $scope.requiredTipoNotaCredito = true;
                $scope.requiredTipoNotaDebito = false;
                $scope.requiredDescripcionNota = true;
                $scope.requiredPlaca = false;
                $scope.requiredGuiaRemisionTransportista = false;
                $scope.requiredCliente = false;
            } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "08") {
                $scope.requiredTipoDocumento = true;
                $scope.requiredFechaEmision = true;
                $scope.requiredCondicion = true;
                if ($scope.condicion.codigo === "CON") {
                    $scope.requiredFechaVencimiento = false;
                    $scope.requiredFormaPago = true;
                    if ($scope.formaPago.codigo === "EFE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    } else if ($scope.formaPago.codigo === "TAR") {
                        $scope.requiredTipoTarjeta = true;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "DEP") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = true;
                        $scope.requiredNumeroPago = true;
                    } else if ($scope.formaPago.codigo === "CRE") {
                        $scope.requiredTipoTarjeta = false;
                        $scope.requiredBanco = false;
                        $scope.requiredNumeroPago = false;
                    }
                } else if ($scope.condicion.codigo === "CRE") {
                    $scope.requiredFechaVencimiento = false;
                    $scope.requiredFormaPago = false;
                    $scope.requiredTipoTarjeta = false;
                    $scope.requiredBanco = false;
                    $scope.requiredNumeroPago = false;
                }
                $scope.requiredGuiaRemision = false;
                $scope.requiredVentaReferencia = false;
                $scope.requiredTipoNotaCredito = false;
                $scope.requiredTipoNotaDebito = true;
                $scope.requiredDescripcionNota = true;
                $scope.requiredPlaca = false;
                $scope.requiredGuiaRemisionTransportista = false;
                $scope.requiredCliente = false;
            }
        };
        $scope.cambioPlaca = function () {
            if ($scope.vehiculo === undefined || $scope.vehiculo === null)
            {
                $scope.documentoVenta.guiaRemisionTransportista = "";
            } else {
                $scope.documentoVenta.guiaRemisionTransportista = $scope.vehiculo.serie + "-";
            }
        };
        $scope.cambioTipoNota = function () {
            $scope.item = {};
            $scope.item.producto = {};
            if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "07") {
                $scope.item.producto.nombre = $scope.documentoVenta.tipoNotaCredito.nombre;
            } else if ($scope.documentoVenta.tipoDocumentoVenta.codigo === "08") {
                $scope.item.producto.nombre = $scope.documentoVenta.tipoNotaDebito.nombre;
            }
            $scope.item.cantidad = 1;
            $scope.item.descuentoUnitario = 0;
            $scope.item.precioToten = $scope.documentoVenta.documentoVenta.total;
            $scope.item.producto.id = null;
            $scope.item.unidad = {'codigo': 'NIU'};
            $scope.item.fleteUnitario = 0;
            $scope.item.bonificacionUnitario = 0;
            $scope.item.precioUnitario = ($scope.item.precioToten + $scope.item.fleteUnitario + $scope.item.bonificacionUnitario)
            $scope.item.valorUnitario = $scope.item.precioUnitario / 1.18;
            $scope.item.costoUnitario = ($scope.item.fleteUnitario + $scope.item.bonificacionUnitario) - $scope.item.descuentoUnitario;
            $scope.item.precioFinal = $scope.item.precioToten + $scope.item.costoUnitario;
            $scope.item.codigoPrecio = '01';
            $scope.item.codigoIgv = '10';
            $scope.item.totalGratuito = 0;
            $scope.item.ventaBruta = $scope.item.valorUnitario * $scope.item.cantidad;
            $scope.item.totalDescuento = $scope.item.descuentoUnitario * $scope.item.cantidad;
            $scope.item.descuento = $scope.item.totalDescuento > 0 ? true : false;
            $scope.item.total = $scope.item.precioFinal * $scope.item.cantidad;
            $scope.item.totalGrabado = $scope.item.ventaBruta;
            $scope.item.totalIgv = $scope.item.total - $scope.item.totalGrabado;
            $scope.item.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            $scope.documentoVenta.documentoVentaDetalles = [];
            $scope.documentoVenta.documentoVentaDetalles.push($scope.item);
            //console.log($scope.item);
            $scope.calcularMontos();
            $scope.nuevoDocumentoVentaDetalle();
        };
        $scope.nuevoDocumentoVentaDetalle();
        $scope.calcularMontos();
        $scope.cambioTipoDocumentoVenta();
        $scope.cambiarCamposRequeridos();
    }]);
DocumentoVentaApp.directive('selectOnClick', ['$window', function ($window) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                element.on('click', function () {
                    if (!$window.getSelection().toString()) {
                        // Required for mobile Safari
                        this.setSelectionRange(0, this.value.length)
                    }
                });
            }
        };
    }]);