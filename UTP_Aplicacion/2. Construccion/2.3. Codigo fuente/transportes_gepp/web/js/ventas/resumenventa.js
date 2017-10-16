/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ResumenVentaApp = angular.module("ResumenVentaApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
ResumenVentaApp.filter('startFrom', function () {
    return function (input, start) {
        if (!input || !input.length) {
            return;
        }
        start = +start; //parse to int
        return input.slice(start);
    }
});
ResumenVentaApp.directive('ngEnter', function () {
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
ResumenVentaApp.controller("ResumenVentaController", ['$scope', '$http', '$filter', '$window', function ($scope, $http, $filter, $window) {
        //$scope.resumenVentasGruposVentas = [];
        $scope.resumenVentasSeleccionado = undefined;
        $scope.resumenVentas = [];
        $scope.documentoVentas = [];
        console.log("{}");
        
        
        $scope.resumenVenta = {};
        $scope.documentoVenta = {};
        $scope.resumenVentasGrupo = {};
        $scope.resumenVentasGruposVenta = {};
        
        $scope.resumenVentasGrupos = [];
        $scope.resumenVentasGruposVentas = [];
        //$scope.resumenVentasGrupos = {};
        //$scope.resumenVenta.resumenVentasGrupos = [];
        $scope.puntoVentaSeries = [
            {'codigo': 'B001'},
            {'codigo': 'B002'},
            {'codigo': 'F001'},
            {'codigo': 'F007'},
            {'codigo': 'F008'}];
        $scope.puntoVentaSerie = {'codigo': 'B001'};
        $scope.tipoDocumentoVentas = [
            {'codigo': '01', 'nombre': 'FACTURA ELECTRÓNICA'},
            {'codigo': '03', 'nombre': 'BOLETA DE VENTA ELECTRÓNICA'},
            {'codigo': '07', 'nombre': 'NOTA DE CRÉDITO  ELECTRÓNICA'},
            {'codigo': '08', 'nombre': 'NOTA DE DÉBITO  ELECTRÓNICA'}];
        $scope.tipoDocumentoVenta = {'codigo': '03', 'nombre': 'BOLETA DE VENTA ELECTRÓNICA'};
        $scope.fechaEmision = "";
        $scope.fechaEmisionBaja = "";
        $scope.sesion = {};
        $scope.productoTmp = {};
        $scope.listaResumenVentas = [];
        $scope.unidades = [];
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            //console.log("response.data " + response.data);
            $scope.sesion = response.data;
            $scope.estadoDocumentoVentas = $scope.sesion.estadoDocumentoVentas;
        }, function myError(response) {
        });

        $scope.totalItemsListaResumenVentas = 0;
        $scope.currentPageListaResumenVentas = 1;
        $scope.paginasListaResumenVentas = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.paginaListaResumenVentas = {value: 5};
        $scope.itemsPerPageListarResumenVentas = $scope.paginaListaResumenVentas.value;

        $scope.mensajeTituloProducto = "";
        $scope.parametro = {};
        $scope.parametro.estadoDocumentoVenta = {};
        $scope.listar = function () {
            console.log("listar");
            $scope.resumenesVentas = [];
            $scope.parametros = [];
            $scope.parametros.push({'nombre': 'fechaEmision', 'valor': $scope.parametro.fechaEmision});
            $scope.parametros.push({'nombre': 'numero', 'valor': $scope.parametro.numero});
            $scope.parametros.push({'nombre': 'codigoEstado', 'valor': ($scope.parametro.estadoDocumentoVenta === null ? null : $scope.parametro.estadoDocumentoVenta.codigo)});
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPageListaResumenVentas * $scope.itemsPerPageListarResumenVentas) - $scope.itemsPerPageListarResumenVentas).toString()});
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPageListarResumenVentas.toString()});
            console.log("parametros " + $scope.parametros);
            console.log("parametro.fechaEmision " + $scope.parametro.fechaEmision);
            console.log("parametro.numero " + $scope.parametro.numero);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/resumenventa/listar',
                data: $scope.parametros
            }).then(function success(response) {

                $scope.listaResumenVentas = response.data.vwSelResumenesVentas;
                $scope.totalItemsListaResumenVentas = response.data.nroResumenesVentas;
            }, function myError(response) {
            });
        };
        $scope.setPage = function (pageNo) {
            $scope.currentPageListaResumenVentas = pageNo;
            $scope.listar();
            console.log('setPage');
        };
        $scope.pageChanged = function () {
            //console.log('Page changed to: ' + $scope.currentPageListaResumenVentas);
            $scope.listar();
            console.log('pageChanged');
        };
        $scope.setItemsPerPage = function (num) {
            $scope.itemsPerPageListarResumenVentas = num;
            $scope.currentPageListaResumenVentas = 1; //reset to first paghe
            $scope.listar();
            console.log('setItemsPerPage');
        };


        $scope.totalItemsListaDocumentoVentas = 0;
        $scope.currentPageListaDocumentoVentas = 1;
        $scope.paginasListaDocumentoVentas = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.paginaListaDocumentoVentas = {value: 5};
        $scope.itemsPerPageListaDocumentoVentas = $scope.paginaListaDocumentoVentas.value;
        $scope.listarDocumentoVenta = function () {
            $scope.documentoVentas = [];
            $scope.parametros = [];
            $scope.parametro = {};
            $scope.parametros.push({'nombre': 'codigoTipo', 'valor': '03'});
            $scope.parametros.push({'nombre': 'fechaEmision', 'valor': $scope.fechaEmision});
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPageListaDocumentoVentas * $scope.itemsPerPageListaDocumentoVentas) - $scope.itemsPerPageListaDocumentoVentas).toString()});
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPageListaDocumentoVentas.toString()});
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/listar',
                data: $scope.parametros
            }).then(function success(response) {

                console.log("response.data.documentoVentas " + response.data.documentoVentas);
                $scope.documentoVentas = response.data.documentoVentas;
                $scope.totalItemsListaDocumentoVentas = response.data.nroDocumentoVentas;


            }, function myError(response) {
                console.log(response.data);
            });
        };
        $scope.setPageListaDocumentoVentas = function (pageNo) {
            $scope.currentPageListaDocumentoVentas = pageNo;
            $scope.listarDocumentoVenta();
        };
        $scope.pageChangedListaDocumentoVentas = function () {
            //console.log('Page changed to: ' + $scope.currentPageListaResumenVentas);
            $scope.listarDocumentoVenta();
        };
        $scope.setItemsPerPageListaDocumentoVentas = function (num) {
            $scope.itemsPerPageListaDocumentoVentas = num;
            $scope.currentPageListaDocumentoVentas = 1; //reset to first paghe
            $scope.listarDocumentoVenta();
        };
        $scope.selectDocumentoVentas = function () {
            $scope.boletasJson = [];
            $scope.boletaJson = {};
            
            angular.forEach($scope.documentoVentas, function (selected) {
                if (selected.selected) {

                    $scope.boletaJson = {};
                    $scope.boletaJson.dccId = selected.id;
                    $scope.boletaJson.dccSerie = selected.serie;
                    $scope.boletaJson.dccNumero = selected.numero;
                    $scope.boletaJson.dccTotalVenta = selected.total;
                    $scope.boletaJson.dccTotalGravado = selected.totalGrabado;
                    $scope.boletaJson.dccTotalIgv = selected.totalIgv;
                    $scope.boletasJson.push($scope.boletaJson);
                    console.log($scope.boletasJson.length);
                }
            });

            if ($scope.boletasJson.length > 0) {

                $scope.resumenVentasGrupos = [];
                $scope.resumenVentasGrupoVentas = [];
                var ultimoDccNumero = 0;

                var sumDccTotalGravado = 0;
                var sumDccTotalIgv = 0;
                var sumDccTotalVenta = 0

                var redInicioDocumento = "";
                var redFinDocumento = "";
                var redSerie = "";

                for (var cont = 0; cont <= $scope.boletasJson.length; cont++) {
                    console.log($scope.boletasJson.length);
                    var length = $scope.boletasJson.length;
                    if (length === cont) {
                        $scope.resumenVentasGrupos = {};
                        $scope.resumenVentasGrupos.puntoVentaSerie = {'codigo': redSerie};
                        $scope.resumenVentasGrupos.finDocumentoVenta = redFinDocumento;
                        $scope.resumenVentasGrupos.inicioDocumentoVenta = redInicioDocumento;
                        $scope.resumenVentasGrupos.totalGrabado = sumDccTotalGravado.toFixed(2);
                        $scope.resumenVentasGrupos.totalIgv = sumDccTotalIgv.toFixed(2);
                        $scope.resumenVentasGrupos.total = sumDccTotalVenta.toFixed(2);
                        $scope.resumenVentasGrupos.tipoDocumentoVenta = {'codigo': '03'};
                        $scope.resumenVentasGrupos.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
                        $scope.resumenVentasGrupos.fechaCreacion = new Date();
                        
                        $scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                        $scope.resumenVentasGrupoVentas = [];
                            
                        /*$scope.resumenVentasGrupoVenta = {};
                        $scope.resumenVentasGrupoVenta.documentoVenta = { 'id' : boleta.dccId};
                        $scope.resumenVentasGrupoVenta.usuarioByIdUsuarioCreacion = {'id': $scope.sesion.usuario};
                        $scope.resumenVentasGrupoVenta.fechaCreacion = new Date();
                        $scope.resumenVentasGrupos.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);*/
                        //$scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                        $scope.resumenVenta.resumenVentasGrupos.push($scope.resumenVentasGrupos);
                        
                        /**$scope.resumenVentasGrupoVenta = {};
                        $scope.resumenVentasGrupoVenta.documentoVenta = { 'id' : boleta.dccId};
                        $scope.resumenVentasGrupoVenta.usuarioByIdUsuarioCreacion = {'id': $scope.sesion.usuario};
                        $scope.resumenVentasGrupoVenta.fechaCreacion = new Date();
                        //$scope.resumenVentasGrupos.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);
                        $scope.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);
                        $scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                        //$scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;*/
                    } else {
                        var boleta = $scope.boletasJson[cont];
                        var dccNumero = parseInt(boleta.dccNumero);
                        var dccNumeroTmp = ultimoDccNumero - 1;
                        if (ultimoDccNumero === 0)
                        {
                            dccNumeroTmp = dccNumero;
                            redInicioDocumento = boleta.dccNumero;
                            redFinDocumento = boleta.dccNumero;
                            redSerie = boleta.dccSerie;
                        }
                        if (dccNumeroTmp !== dccNumero) {
                            $scope.resumenVentasGrupos = {};
                            $scope.resumenVentasGrupos.puntoVentaSerie = {'codigo': redSerie};
                            $scope.resumenVentasGrupos.finDocumentoVenta = redFinDocumento;
                            $scope.resumenVentasGrupos.inicioDocumentoVenta = redInicioDocumento;
                            $scope.resumenVentasGrupos.totalGrabado = sumDccTotalGravado.toFixed(2);
                            $scope.resumenVentasGrupos.totalIgv = sumDccTotalIgv.toFixed(2);
                            $scope.resumenVentasGrupos.total = sumDccTotalVenta.toFixed(2);
                            $scope.resumenVentasGrupos.tipoDocumentoVenta = {'codigo': '03'};
                            $scope.resumenVentasGrupos.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
                            $scope.resumenVentasGrupos.fechaCreacion = new Date();
                            
                            $scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                            $scope.resumenVentasGrupoVentas = [];
                            
                            $scope.resumenVenta.resumenVentasGrupos.push($scope.resumenVentasGrupos);

                            sumDccTotalGravado = 0;
                            sumDccTotalIgv = 0;
                            sumDccTotalVenta = 0;

                            sumDccTotalGravado += boleta.dccTotalGravado;
                            sumDccTotalIgv += boleta.dccTotalIgv;
                            sumDccTotalVenta += boleta.dccTotalVenta;

                            redInicioDocumento = boleta.dccNumero;
                            redFinDocumento = boleta.dccNumero;
                            redSerie = boleta.dccSerie;
                            
                            //$scope.resumenVentasGrupoVentas = [];
                            $scope.resumenVentasGrupoVenta = {};
                            $scope.resumenVentasGrupoVenta.documentoVenta = { 'id' : boleta.dccId};
                            $scope.resumenVentasGrupoVenta.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
                            $scope.resumenVentasGrupoVenta.fechaCreacion = new Date();
                            //$scope.resumenVentasGrupos.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);
                            
                            $scope.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);
                            //$scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                            //$scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                        } else {
                            sumDccTotalGravado += boleta.dccTotalGravado;
                            sumDccTotalIgv += boleta.dccTotalIgv;
                            sumDccTotalVenta += boleta.dccTotalVenta;
                            redFinDocumento = boleta.dccNumero;
                            
                            //$scope.resumenVentasGrupoVentas = [];
                            $scope.resumenVentasGrupoVenta = {};
                            $scope.resumenVentasGrupoVenta.documentoVenta = { 'id' : boleta.dccId};
                            $scope.resumenVentasGrupoVenta.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
                            $scope.resumenVentasGrupoVenta.fechaCreacion = new Date();
                            //$scope.resumenVentasGrupos.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);
                            
                            $scope.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);
                            //$scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                            //$scope.resumenVentasGrupos.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                        }
                        ultimoDccNumero = dccNumero;
                    }
                }

            }

            var totalesIgv = 0;
            var totalesGravado = 0;
            var totalesTotalBoleta = 0;
            console.log($scope.resumenVenta.resumenVentasGrupos.length);
            var resumenVentasGruposTamaño = $scope.resumenVenta.resumenVentasGrupos.length;
            for (var cont = 0; cont < resumenVentasGruposTamaño; cont++) {
                var igv = parseFloat($scope.resumenVenta.resumenVentasGrupos[cont].totalIgv);
                var gravado = parseFloat($scope.resumenVenta.resumenVentasGrupos[cont].totalGrabado);
                var totalBoleta = parseFloat($scope.resumenVenta.resumenVentasGrupos[cont].total);

                totalesIgv += igv;
                totalesGravado += gravado;
                totalesTotalBoleta += totalBoleta;
            }
            $scope.totalGrabado = totalesGravado;
            $scope.totalIgv = totalesIgv;
            $scope.totalImporte = totalesTotalBoleta;



            //console.log($scope.parametro.estadoDocumentoVenta.codigo);
        }


        $scope.totalItemsListaBaja = 0;
        $scope.currentPageListaBaja = 1;
        $scope.paginasListaBaja = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.paginaListaBaja = {value: 5};
        $scope.itemsPerPageListaBaja = $scope.paginaListaBaja.value;

        $scope.listarDocumentoVentaBaja = function () {
            $scope.documentoVentas = [];
            $scope.parametros = [];
            $scope.parametro = {};
            $scope.parametros.push({'nombre': 'codigoTipo', 'valor': $scope.tipoDocumentoVenta.codigo});
            $scope.parametros.push({'nombre': 'serie', 'valor': $scope.puntoVentaSerie.codigo});
            $scope.parametros.push({'nombre': 'fechaEmision', 'valor': $scope.fechaEmisionBaja});
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPageListaBaja * $scope.itemsPerPageListaBaja) - $scope.itemsPerPageListaBaja).toString()});
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPageListaBaja.toString()});
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/listar',
                data: $scope.parametros
            }).then(function success(response) {


                $scope.documentoVentas = response.data.documentoVentas;
                $scope.totalItemsListaBaja = response.data.nroDocumentoVentas;
            }, function myError(response) {
                console.log(response.data);
            });
        };
        $scope.setPageListaBaja = function (pageNo) {
            $scope.currentPageListaBaja = pageNo;
            $scope.listarDocumentoVentaBaja();
            console.log('setPageListaBaja');
        };
        $scope.pageChangedListaBaja = function () {
            //console.log('Page changed to: ' + $scope.currentPageListaResumenVentas);
            $scope.listarDocumentoVentaBaja();
            console.log('pageChangedListaBaja');
        };
        $scope.setItemsPerPageListaBaja = function (num) {
            $scope.itemsPerPageListaBaja = num;
            $scope.currentPageListaBaja = 1; //reset to first paghe
            $scope.listarDocumentoVentaBaja();
            console.log('setItemsPerPageListaBaja');
        };

        $scope.selectDocumentoVentasBaja = function () {
            //$scope.resumenVentasGrupos = [];
            $scope.comunicacionBajaGrupos = [];

            //$scope.resumenVentasGrupo.serie = 

            angular.forEach($scope.documentoVentas, function (selected) {
                if (selected.selected) {
                    console.log("selected.$index " + selected.$index);
                    console.log("selected.numero " + selected.serie);
                    console.log("selected.numero " + selected.numero);
                    console.log("selected.numero " + selected.totalGrabado);
                    console.log("selected.numero " + selected.totalIgv);
                    console.log("selected.numero " + selected.total);

                    /*if ($scope.comunicacionBajaGrupo.serie === selected.puntoVentaSerie.codigo || $scope.comunicacionBajaGrupo.serie === undefined) {
                     console.log("104 ");
                     $scope.comunicacionBajaGrupo.serie = selected.puntoVentaSerie.codigo;*/
                    $scope.comunicacionBajaGrupo = {};
                    $scope.comunicacionBajaGrupo.tipoDocumentoVenta = {'codigo': selected.codigoTipo, 'nombre': selected.nombreTipo};
                    $scope.comunicacionBajaGrupo.puntoVentaSerie = {'codigo': selected.serie};
                    $scope.comunicacionBajaGrupo.moneda = {'codigo': 'PEN'};
                    $scope.comunicacionBajaGrupo.inicioDocumentoVenta = selected.numero;
                    $scope.comunicacionBajaGrupo.finDocumentoVenta = selected.numero;
                    $scope.comunicacionBajaGrupo.fechaCreacion = new Date();
                    $scope.comunicacionBajaGrupo.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
                    $scope.resumenVentasGrupoVentas = [];
                    $scope.resumenVentasGrupoVenta = {};
                    $scope.resumenVentasGrupoVenta.documentoVenta = {'id': selected.id, 'estadoDocumentoVenta': {'codigo': 'ANU'}};
                    $scope.resumenVentasGrupoVenta.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
                    $scope.resumenVentasGrupoVenta.fechaCreacion = new Date();
                    $scope.resumenVentasGrupoVentas.push($scope.resumenVentasGrupoVenta);
                    $scope.comunicacionBajaGrupo.resumenVentasGrupoVentas = $scope.resumenVentasGrupoVentas;
                    $scope.comunicacionBajaGrupos.push($scope.comunicacionBajaGrupo);

                }
            });

            //$scope.personalDetails = newDataList;
        };
        $scope.crearResumenVentasGrupos = function (resumenVenta) {

            resumenVenta.tipo = "RC";
            resumenVenta.estadoDocumentoVenta = {'codigo': 'PES'};
            //resumenVenta.numero = 1;
            resumenVenta.empresa = $scope.sesion.usuario.empresa;
            resumenVenta.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;


            //resumenVentas.resumenVentasGrupo = {'id': '1'};
            //producto.unidad = {'codigo': 'NIU'};
            console.log("resumenVentasGruposVenta " + $scope.resumenVenta);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/resumenventa/crear',
                data: resumenVenta
            }).then(function success(response) {
                $("#modalResumen").modal("hide");
                console.log(response.data);
                $scope.parametro.estadoDocumentoVenta = null;
                $scope.listar();

                /*$scope.resumenVenta = [];
                 $scope.documentoVentas = [];*/
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.crearResumenVentasGruposBaja = function (comunicacionBajaGrupos) {
            $scope.resumenVenta = {};
            //$scope.comunicacionBajaGrupos.push($scope.comunicacionBajaGrupo);
            $scope.resumenVenta.tipo = "RA";
            $scope.resumenVenta.estadoDocumentoVenta = {'codigo': 'PES'};
            $scope.resumenVenta.numero = 1;
            $scope.resumenVenta.empresa = {'id': '1'};
            $scope.resumenVenta.usuarioByIdUsuarioCreacion = {'id': '3'};
            $scope.resumenVenta.resumenVentasGrupos = comunicacionBajaGrupos;
            $scope.resumenVenta.fechaCreacion = new Date();
            //resumenVentas.resumenVentasGrupo = {'id': '1'};
            //producto.unidad = {'codigo': 'NIU'};
            console.log("comunicacionBajaGrupos " + comunicacionBajaGrupos);
            console.log("resumenVentasGruposVenta " + $scope.resumenVenta);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/resumenventa/crear',
                data: $scope.resumenVenta
            }).then(function success(response) {
                $("#modalComunicacionBaja").modal("hide");
                console.log(response.data);
                $scope.parametro.estadoDocumentoVenta = null;
                $scope.listar();
                
            }, function error(response) {
                console.log(response.data);
            });
        };
        /*
         $scope.editarProducto = function (producto) {
         producto.usuarioByIdUsuarioModificacion = $scope.sesion.usuario;
         console.log("editarProducto "+producto);
         $http({
         method: 'POST',
         url: '/transportes_gepp/controlador/producto/editar',
         data: producto
         }).then(function success(response) {
         console.log(response);
         $scope.listar();
         }, function error(response) {
         console.log(response.data);
         });
         };
         $scope.eliminarProducto = function (producto) {
         $http({
         method: 'POST',
         url: '/transportes_gepp/controlador/producto/eliminar/' + producto.id
         }).then(function success(response) {
         console.log(response);
         $scope.listar();
         }, function error(response) {
         console.log(response.data);
         });
         };
         $scope.nuevoProducto = function () {
         $scope.mensajeTituloProducto = "Crear Producto";
         $scope.productoTmp = {};
         $scope.productoTmp.id = 0;
         console.log($scope.productoTmp.id);
         };
         $scope.seleccionarProducto = function (producto) {
         $scope.mensajeTituloProducto = "Editar Producto";
         $scope.productoTmp = producto;
         };
         */
        $scope.guardarProducto = function (resumenVentasGrupos) {
            console.log("resumenVentasGrupos " + resumenVentasGrupos);
            console.log("resumenVentasGrupos[0] " + resumenVentasGrupos[0]);
            console.log("resumenVentasGrupos[0].serie " + resumenVentasGrupos[0].serie);

            /*if (producto.id === 0) {
             $scope.crearProducto(producto);
             } else {
             $scope.editarProducto(producto);
             }
             console.log("producto.id " + producto.id);*/
        };
        //Autocomplete-Inicio
        $scope.complete = function (string) {
            console.log("string " + string);
            console.log("$scope.countryList " + $scope.country);
            $http({
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/transportes_gepp/controlador/producto/autocomplete',
                data: $scope.country
            }).then(function mySucces(response) {
                $scope.countryList = response.data;

                $scope.hidethis = false;
                var output = [];
                angular.forEach($scope.countryList, function (country) {
                    /*if(country.toLowerCase().indexOf(string.toLowerCase()) >= 0)  
                     { */
                    output.push(country);
                    //}  
                });
                $scope.filterCountry = output;
                console.log("filterCountry " + $scope.filterCountry);
            }, function myError(response) {
            });
        };
        $scope.fillTextbox = function (string) {
            console.log("fillTextbox");
            $scope.country = string;
            $scope.hidethis = true;
        };
        /*$scope.onBlur = function() {
         $scope.hidethis = true;
         }*/
        //Autocomplete-Fin
        $scope.eliminarListaResumenVentaGrupo = function (listaResumenVentaGrupo) {
            //$scope.users.splice($scope.users.indexOf($scope.clickedUser),1);
            $scope.resumenVenta.resumenVentasGrupos.splice(listaResumenVentaGrupo, 1);
            //$scope.calcularMontos();
        };
        $scope.eliminarComunicacionBajaGrupo = function (comunicacionBajaGrupo) {
            //$scope.users.splice($scope.users.indexOf($scope.clickedUser),1);
            $scope.comunicacionBajaGrupos.splice(comunicacionBajaGrupo, 1);
            //$scope.calcularMontos();
        };
        $scope.limpiar = function () {
            console.log("limpiar");
            $scope.resumenVenta = {};
            $scope.resumenVenta.resumenVentasGrupos = [];
            $scope.comunicacionBajaGrupos = [];
            $scope.comunicacionBajaGrupo = {};
        };
        $scope.listar();
        $scope.setSelected = function (row, item) {
            $scope.selectedRow = row;
            $scope.resumenVentasSeleccionado = item;
        };
        $scope.enviarSunat = function () {
            alert($scope.resumenVentasSeleccionado.tipo);
            if ($scope.resumenVentasSeleccionado.tipo === "RA") {
                $http({
                    method: 'POST', url: '/transportes_gepp/controlador/resumenventa/enviarcomunicacionbajasunat/' + $scope.resumenVentasSeleccionado.id
                }).then(function success(response) {
                    $scope.parametro.estadoDocumentoVenta = null;
                    $scope.listar();
                }, function error(response) {
                    //console.log(response.data);
                });
            }
            if ($scope.resumenVentasSeleccionado.tipo === "RC") {
                $http({
                    method: 'POST', url: '/transportes_gepp/controlador/resumenventa/enviarresumenboletassunat/' + $scope.resumenVentasSeleccionado.id
                }).then(function success(response) {
                    $scope.parametro.estadoDocumentoVenta = null;
                    $scope.listar();
                }, function error(response) {
                    //console.log(response.data);
                });
            }
            /**/
        };
        $scope.resumenVentaSeleccionado = undefined;
        $scope.descargarResumenVenta = function () {
            if ($scope.resumenVentaSeleccionado !== {})
            {
                $window.open('/transportes_gepp/controlador/resumenventa/descargar/' + $scope.resumenVentasSeleccionado.id, '_blank');
            }
        };
        $scope.consultarEstadoSunat = function () {
            //;
            $http({
                method: 'POST', url: '/transportes_gepp/controlador/resumenventa/consultarproceso/' + $scope.resumenVentasSeleccionado.id
            }).then(function mySucces(response) {
                $scope.listar();
            }, function myError(response) {

            });
        };
        $scope.enviarComunicacionBajaSunat = function () {
            //;
            $http({
                method: 'POST', url: '/transportes_gepp/controlador/resumenventa/enviarcomunicacionbajasunat/' + $scope.resumenVentasSeleccionado.id
            }).then(function mySucces(response) {
                $scope.listar();
            }, function myError(response) {

            });
        };
        $scope.anular = function () {
            var idAnular = $scope.resumenVentasSeleccionado.id;
            console.log(idAnular);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/resumenventa/anular/'+ idAnular
            }).then(function success(response) {
                $scope.parametro.estadoDocumentoVenta = null;
                $scope.listar();
            }, function error(response) {
                //console.log(response.data);
            });
        };

    }]);
