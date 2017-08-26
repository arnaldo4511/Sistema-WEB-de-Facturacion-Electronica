/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var DocumentoVentasApp = angular.module("DocumentoVentasApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('DocumentoVentasApp').directive("formatDate", function() {
    return {
        require: 'ngModel',
        link: function(scope, elem, attr, modelCtrl) {
            modelCtrl.$formatters.push(function(modelValue) {
                if (modelValue){
                    return new Date(modelValue);
                }
                else {
                    return null;
                }
            });
        }
    };
});
DocumentoVentasApp.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});
DocumentoVentasApp.controller("DocumentoVentasController", ['$scope', '$http', '$filter', '$window', function ($scope, $http, $filter, $window) {
        $scope.sesion = {};
        $scope.documentoVenta = {};
        $scope.producto = {};
        $scope.formaPago = {};
        $scope.condicion = {};
        $scope.documentoVentaDetalle = {};
        $scope.tipoTargeta = {};
        $scope.banco = {};


        $scope.productos = [];
        $scope.usuarios = [];
        $scope.roles = [];
        $scope.puntoVentas = [];
        $scope.documentoVentaDetalles = [];

        $scope.tipoDocumentoEntidades = [
            {'codigo': '1', 'nombre': 'DNI'},
            {'codigo': '9', 'nombre': 'RUC'}];
        $scope.tipoDocumentoVentas = [
            {'codigo': '', 'nombre': 'Todos'},
            {'codigo': '01', 'nombre': 'FACTURA'},
            {'codigo': '03', 'nombre': 'BOLETA DE VENTA'}];
        $scope.condiciones = [
            {'codigo': 'CON', 'nombre': 'CONTADO'},
            {'codigo': 'CRE', 'nombre': 'CRÉDITO'}];
        $scope.formaPagos = [
            {'codigo': 'EFE', 'nombre': 'EFECTIVO'},
            {'codigo': 'TAR', 'nombre': 'TARGETA'},
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
        $scope.paginas = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.pagina = {value: 5};
        $scope.fechaDesde = new Date();
        //$scope.fechaDesde =Date.now();//$filter('date')('2017-01-01','yyyy-MM-dd');
        //$scope.fechaDesde = $filter('date')(Date.now(), 'yyyy-MM-dd');
        
        $scope.tipoDocumentoVenta = {'codigo': '', 'nombre': ''};
        $scope.condicion = {'codigo': 'CON', 'nombre': 'CONTADO'};
        $scope.formaPago = {'codigo': 'EFE', 'nombre': 'EFECTIVO'};
        //alert($filter('date')(Date.now(), 'dd-MM-yyyy'));
        //$scope.documentoVenta.fechaEmision = $filter('date')(Date.now(), 'dd-MM-yyyy');//'yyyy-MM-dd'
        $scope.clientes = [];

        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            //console.log(response.data);
            $scope.sesion = response.data;
            $scope.roles = $scope.sesion.roles;
            $scope.puntoVentas = $scope.sesion.puntoVentas;
        }, function myError(response) {
        });
        $scope.pagina.value = 5;
        $scope.totalItems = 0;
        $scope.currentPage = 1;
        $scope.itemsPerPage = $scope.pagina.value;
        $scope.maxSize = 5;
        $scope.numero = "";
        $scope.setSelected = function (item) {
            //alert(item.id);
            if (this.selected === "") {
                this.selected = "selected";
            } else {
                this.selected = "";
            }
        }
        $scope.listar = function () {
            $scope.documentoVentas = [];
            $scope.parametros = [];
            $scope.parametro = {};
            $scope.parametro.nombre = "tipoDocumentoVenta.codigo";
            $scope.parametro.valor = $scope.tipoDocumentoVenta.codigo;
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "currentPage";
            $scope.parametro.valor = (($scope.currentPage * $scope.itemsPerPage) - $scope.itemsPerPage).toString();
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "itemsPerPage";
            $scope.parametro.valor = $scope.itemsPerPage.toString();
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "numero";
            $scope.parametro.valor = $scope.numero;
            $scope.parametros.push($scope.parametro);
            console.log($scope.parametros);
            //$scope.parametros.push($scope.parametro);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/listar',
                data: $scope.parametros
            }).then(function success(response) {
                console.log(response.data);
                $scope.documentoVentas = response.data.lista;
                $scope.totalItems = response.data.total;//$scope.documentoVentas.length;
                console.log($scope.totalItems);
            }, function myError(response) {
                console.log(response.data);
            });
        };
        $scope.setPage = function (pageNo) {
            $scope.currentPage = pageNo;
            $scope.listar();
        };

        $scope.pageChanged = function () {
            //console.log('Page changed to: ' + $scope.currentPage);
            $scope.listar();
        };

        $scope.setItemsPerPage = function (num) {
            $scope.itemsPerPage = num;
            $scope.currentPage = 1; //reset to first paghe
            $scope.listar();
        }
        $scope.crear = function () {
            $window.location.href = 'documentoventa.jsp';
        }

        $scope.validar = function () {
            //var valPro=($scope.documentoVenta.documentoVentaDetalles.length === 0);
            //var valCli=($scope.documentoVenta.tipoDocumentoVenta.codigo === '03' && $scope.condicion.codigo==='CRE' && $scope.documentoVenta.total<700 && $scope.documentoVenta.cliente === {});
            //return ( valPro && valCli);
        };
        $scope.cancelarCliente = function () {
            $scope.clientes = [];
            $scope.ocultarEntidadesAuto = false;
            $scope.documentoVenta.cliente = {};
            $scope.criterioCliente = '';
        };
        $scope.guardar = function (documentoVenta) {
            documentoVenta.empresa = $scope.sesion.usuario.empresa;
            documentoVenta.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            documentoVenta.formaPago = $scope.formaPago.codigo;
            documentoVenta.condicion = $scope.condicion.codigo;
            documentoVenta.moneda = {'codigo': 'PEN', 'nombre': 'Soles'};
            documentoVenta.estadoDocumentoVenta = {'codigo': 'CRE', 'nombre': 'CREADO'};
            documentoVenta.puntoVenta = $scope.sesion.usuario.puntoVenta;
            documentoVenta.puntoVentaSerie = {'codigo': 'F001'};
            documentoVenta.tipoTargeta = $scope.tipoTargeta.codigo;
            documentoVenta.banco = $scope.banco.codigo;
            documentoVenta.numero = '00000000';
            //console.log(documentoVenta);
            //return;
            //return;
            //usuario.usuarioByIdDocumentoVentaCreacion = $scope.sesion.usuario;
            //usuario.empresa = $scope.sesion.usuario.empresa;
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/crear',
                data: documentoVenta
            }).then(function success(response) {
                console.log(response.data);
                //$scope.listar();
            }, function error(response) {
                //console.log(response.data);
            });
        };
        $scope.completarCliente = function (criterio) {
            if (criterio === undefined || criterio.trim() === "")
            {
                $scope.clientes = [];
                return;
            }
            //console.log(criterio);
            $http({method: 'GET', url: '/transportes_gepp/controlador/cliente/autocompletar/' + criterio
            }).then(function mySucces(response) {
                //console.log(response.data);
                $scope.clientes = response.data;
                $scope.ocultarEntidadesAuto = false;
            }, function myError(response) {

            });
        };
        $scope.criterioCliente = '';
        $scope.seleccionarEntidad = function (item) {
            $scope.documentoVenta.cliente = item;
            $scope.ocultarEntidadesAuto = true;
            $scope.criterioCliente = item.entidad.nombre;
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
        $scope.criterioProducto = '';
        $scope.seleccionarProducto = function (item) {
            $scope.documentoVentaDetalle.producto = item;
            $scope.criterioProducto = item.nombre;
            $scope.ocultarProductosAuto = true;
        };
        $scope.documentoVenta.documentoVentaDetalles = [];
        $scope.agregarProducto = function (item) {
            //
            item.precioToten = item.producto.precioVenta;
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
            item.totalIgv = item.total * 0.18;
            item.totalGrabado = item.total - item.totalIgv;
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
        };
        $scope.listar();
    }]);
