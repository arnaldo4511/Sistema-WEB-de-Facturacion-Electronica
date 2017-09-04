/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var DocumentoVentasApp = angular.module("DocumentoVentasApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('DocumentoVentasApp').directive("formatDate", function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attr, modelCtrl) {
            modelCtrl.$formatters.push(function (modelValue) {
                if (modelValue) {
                    return new Date(modelValue);
                } else {
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

        $scope.paginas = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.pagina = {value: 5};
        $scope.parametro = {};
        //$scope.parametro.fechaDesde = new Date();
        //$scope.parametro.fechaHasta = new Date(2017,01,01);
        $scope.parametro.fechaHasta = new Date();//$filter('date')(new Date(), 'yyyy-MM-dd');
        $scope.parametro.fechaDesde = new Date(new Date().getFullYear(), 0, 01);
        //new ;//$filter('date')(new Date(), 'yyyy-MM-dd');
        //$scope.fechaDesde = $filter('date')(Date.now(), 'yyyy-MM-dd');


        $scope.tipoNotificacions = [{'codigo': 'CLI', 'nombre': 'CLIENTE'}, {'codigo': 'PER', 'nombre': 'PERSONALIZADO'}];
        $scope.tipoNotificacion = {'codigo': 'CLI', 'nombre': 'CLIENTE'};
        $scope.tipoAnulacions = [{'codigo': 'CTI', 'nombre': 'CONTROL INTERNO'}, {'codigo': 'COB', 'nombre': 'COMUNICACIÓN DE BAJA'}];
        $scope.tipoAnulacion = {'codigo': 'CTI'};

        //alert($filter('date')(Date.now(), 'dd-MM-yyyy'));
        //$scope.documentoVenta.fechaEmision = $filter('date')(Date.now(), 'dd-MM-yyyy');//'yyyy-MM-dd'


        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            //console.log(response.data);
            $scope.sesion = response.data;
            $scope.tipoDocumentoVentas = $scope.sesion.tipoDocumentoVentas;
            $scope.estadoDocumentoVentas = $scope.sesion.estadoDocumentoVentas;

            //console.log($scope.sesion.vwSelDocumentoVentas);
        }, function myError(response) {
        });
        $scope.pagina.value = 5;
        $scope.totalItems = 0;
        $scope.currentPage = 1;
        $scope.itemsPerPage = $scope.pagina.value;
        $scope.maxSize = 5;
        $scope.numero = "";
        $scope.documentoVentaSeleccionado = {};
        $scope.descargarDocumentoVenta = function () {
            if ($scope.documentoVentaSeleccionado !== {})
            {
                $window.open('/transportes_gepp/controlador/documentoventa/descargar/' + $scope.documentoVentaSeleccionado.id, '_blank');
            }
        };
        $scope.setSelected = function (row, item) {
            //alert(item.id)
            //console.log(row);
            //console.log(item);
            $scope.selectedRow = row;
            $scope.documentoVentaSeleccionado = item;
            /*if (this.selected === "") {
             this.selected = "selected";
             $scope.documentoVentaSeleccionado = item;
             } else {
             this.selected = "";
             $scope.documentoVentaSeleccionado = {};
             }*/
        }
        //$scope.parametro = {};
        $scope.parametro.tipoDocumentoVenta = {};
        $scope.parametro.estadoDocumentoVenta = {};
        $scope.listar = function () {

            //alert("listar");
            $scope.documentoVentas = [];
            //$scope.totalItems = 0;
            $scope.totalFacturas = 0;
            $scope.totalBoletas = 0;
            $scope.totalNotaCreditos = 0;
            $scope.totalNotaDebitos = 0;
            $scope.parametros = [];
            if ($scope.parametro === {}) {
            } else
            {
                $scope.parametros.push({'nombre': 'fechaDesde', 'valor': $scope.parametro.fechaDesde});
                $scope.parametros.push({'nombre': 'fechaHasta', 'valor': $scope.parametro.fechaHasta});
                $scope.parametros.push({'nombre': 'codigoTipo', 'valor': ($scope.parametro.tipoDocumentoVenta === null ? null : $scope.parametro.tipoDocumentoVenta.codigo)});
                $scope.parametros.push({'nombre': 'codigoEstado', 'valor': ($scope.parametro.estadoDocumentoVenta === null ? null : $scope.parametro.estadoDocumentoVenta.codigo)});
                $scope.parametros.push({'nombre': 'numero', 'valor': $scope.parametro.numero});
                $scope.parametros.push({'nombre': 'fechaEmision', 'valor': $scope.parametro.fechaEmision});
                $scope.parametros.push({'nombre': 'documentoCliente', 'valor': $scope.parametro.documentoCliente});
                $scope.parametros.push({'nombre': 'nombreCliente', 'valor': $scope.parametro.nombreCliente});
            }

            //alert("listar2");
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPage * $scope.itemsPerPage) - $scope.itemsPerPage).toString()});

            //alert("listar3");
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPage.toString()});

            //alert("listar4");
            //console.log($scope.parametros);
            //$scope.parametros.push($scope.parametro);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/listar',
                data: $scope.parametros
            }).then(function success(response) {
                //console.log(response.data);

                //alert("listar-fin");
                $scope.documentoVentas = response.data.documentoVentas;
                $scope.totalItems = response.data.nroDocumentoVentas;//$scope.documentoVentas.length;
                $scope.totalFacturas = response.data.totalFacturas;
                $scope.totalBoletas = response.data.totalBoletas;
                $scope.totalNotaCreditos = response.data.totalNotaCreditos;
                $scope.totalNotaDebitos = response.data.totalNotaDebitos;
                //console.log($scope.totalItems);
            }, function myError(response) {
                //console.log(response.data);
            });
        };
        $scope.setPage = function (pageNo) {
            $scope.currentPage = pageNo;
            $scope.listar();
        };

        $scope.pageChanged = function () {
            //alert("pageChanged:" + $scope.currentPage);
            //console.log('Page changed to: ' + $scope.currentPage);
            $scope.listar();
        };

        $scope.setItemsPerPage = function (num) {
            //alert("setItemsPerPage:" + $scope.currentPage);
            $scope.itemsPerPage = num;
            $scope.currentPage = 1; //reset to first paghe
            $scope.listar();
        }
        $scope.crear = function () {
            $window.open('documentoventa.jsp', "_self");

            //$window.location.href = 'documentoventa.jsp';
        }
        $scope.hacerNotaCredito = function () {
            $window.open('documentoventa.jsp?id=' + $scope.documentoVentaSeleccionado.id + "&codigoTipo=07", "_self");
            //$window.location.href = 'documentoventa.jsp';
        }
        $scope.hacerNotaDebito = function () {
            $window.open('documentoventa.jsp?id=' + $scope.documentoVentaSeleccionado.id + "&codigoTipo=08", "_self");
            //$window.location.href = 'documentoventa.jsp';
        }


        $scope.nuevaNotificacion = function () {
            $scope.notificacion = {};
            $scope.notificacion.idDocumentoVenta = $scope.documentoVentaSeleccionado.id;
        };
        $scope.notificacion = {};
        $scope.notificar = function () {
            $scope.notificacion.tipo = $scope.tipoNotificacion.codigo;
            console.log($scope.notificacion);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/enviarcliente',
                data: $scope.notificacion
            }).then(function success(response) {
                console.log(response.data);
                //$scope.listar();
            }, function error(response) {
                //console.log(response.data);
            });
        };
        $scope.nuevaAnulacion = function () {
            $scope.anulacion = {};
            $scope.anulacion.idDocumentoVenta = $scope.documentoVentaSeleccionado.id;
            $scope.tipoAnulacion = {'codigo': 'CTI'};
        };
        $scope.anularDocumentoVenta = function () {
            $scope.anulacion.tipo = $scope.tipoAnulacion.codigo;
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/anular',
                data: $scope.anulacion
            }).then(function success(response) {
                $scope.listar();
            }, function error(response) {
                //console.log(response.data);
            });
        };

        $scope.limpiarParametros = function () {
            $scope.parametro = {};
            $scope.parametro.estadoDocumentoVenta = {};
            $scope.parametro.tipoDocumentoVenta = {};
            $scope.parametro.fechaHasta = new Date();//$filter('date')(new Date(), 'yyyy-MM-dd');
            $scope.parametro.fechaDesde = new Date(new Date().getFullYear(), 0, 01);
        };
        $scope.listar();
    }]);
