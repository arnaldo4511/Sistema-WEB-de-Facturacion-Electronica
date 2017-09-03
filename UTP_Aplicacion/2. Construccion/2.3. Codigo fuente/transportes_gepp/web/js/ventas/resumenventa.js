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
ResumenVentaApp.controller("ResumenVentaController", ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $scope.resumenVentasGruposVentas = [];
        
        $scope.resumenVentas = [];
        $scope.documentoVentas = [];
        console.log("{}");
        $scope.resumenVentasGruposVenta = {};
        $scope.resumenVentasGrupo = {};
        $scope.resumenVenta = {};
        $scope.documentoVenta = {};
        $scope.resumenVentasGrupos = {};
        $scope.resumenVenta.resumenVentasGrupos = [];
        $scope.puntoVentaSeries = [
            {'codigo': 'B001'},
            {'codigo': 'B002'},
            {'codigo': 'F001'},
            {'codigo': 'F007'},
            {'codigo': 'F008'}];
        $scope.puntoVentaSerie = {'codigo': 'B001'};
        $scope.fechaEmision = "";
        $scope.sesion = {};
        $scope.productoTmp = {};
        $scope.resumenVentasLista = [];
        $scope.unidades = [];
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            console.log("response.data " + response.data);
            $scope.sesion = response.data;
        }, function myError(response) {
        });
        $scope.setPage = function (pageNo) {
            $scope.currentPageResumenVentas = pageNo;
        };
        $scope.pageChanged = function () {
            console.log('Page changed to: ' + $scope.currentPageResumenVentas);
        };
        $scope.setItemsPerPage = function (num) {
            $scope.itemsPerPageResumenVentas = num;
            $scope.currentPageResumenVentas = 1; //reset to first paghe
        };

        $scope.mensajeTituloProducto = "";
        $scope.listar = function () {
            $http({
                method: 'GET',
                url: '/transportes_gepp/controlador/resumenventa/listar'
            }).then(function success(response) {
                console.log(response);
                console.log("response.data " + response.data);
                $scope.resumenVentasLista = response.data;
                $scope.viewby = 5;
                $scope.totalItemsResumenVentas = $scope.resumenVentasLista.length;
                $scope.currentPageResumenVentas = 1;
                $scope.itemsPerPage = $scope.viewby;
                $scope.maxSize = 5; //Number of pager buttons to show
            }, function myError(response) {
            });
        };
        $scope.listarDocumentoVenta = function () {
            $scope.documentoVentas = [];
            $scope.parametros = [];
            $scope.parametro = {};
            $scope.parametro.nombre = "tipoDocumentoVenta.codigo";
            $scope.parametro.valor = "03";
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "currentPage";
            $scope.parametro.valor = "0";
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "itemsPerPage";
            $scope.parametro.valor = "0";
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "numero";
            $scope.parametro.valor = "";
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "puntoVentaSerie.codigo";
            $scope.parametro.valor = $scope.puntoVentaSerie.codigo;
            $scope.parametros.push($scope.parametro);
            $scope.parametro = {};
            $scope.parametro.nombre = "fechaEmision";
            $scope.parametro.valor = $scope.fechaEmision;
            $scope.parametros.push($scope.parametro);
            console.log("$scope.fechaEmision " + $scope.fechaEmision);
            console.log("$scope.fechaEmision " + $scope.fechaEmision);
            console.log("$scope.parametros " + $scope.parametros);
            //$scope.parametros.push($scope.parametro);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/documentoventa/listar',
                data: $scope.parametros
            }).then(function success(response) {
                console.log("response.data " + response.data);
                console.log("response.status " + response.status);
                console.log("response.statusText " + response.statusText);
                console.log("response.headers " + response.headers);
                console.log("response.config " + response.config);
                console.log("response.config.data " + response.config.data);
                console.log("response.config.data.numero " + response.config.data.numero);

                console.log("response.records " + response.records);
                console.log("response.data " + response.data);
                console.log("response.data.lista.numero " + response.data.lista.numero);
                $scope.documentoVentas = response.data.lista;
                /*$scope.totalItems = response.data.total;//$scope.documentoVentas.length;
                 console.log($scope.totalItems);*/
            }, function myError(response) {
                console.log(response.data);
            });
        };
        $scope.selectDocumentoVentas = function () {

            //$scope.resumenVentasGrupo.serie = 
            var c1 = 0;
            var c2 = 0;
            var c3 = 0;
            var grabada;
            var igv;
            var total;
            var numero = 0;
            var numero2 = 0;
            angular.forEach($scope.documentoVentas, function (selected) {
                if (selected.selected) {
                    $scope.resumenVentasGrupos.documentoVenta = selected.id;
                    console.log("selected.numero " + selected.puntoVentaSerie.codigo);
                    console.log("selected.numero " + selected.numero);
                    console.log("selected.numero " + selected.totalGrabado);
                    console.log("selected.numero " + selected.totalIgv);
                    console.log("selected.numero " + selected.total);

                    console.log("selected.puntoVentaSerie.codigo " + selected.puntoVentaSerie.codigo);
                    //if ($scope.resumenVentasGrupo.serie === selected.puntoVentaSerie.codigo || $scope.resumenVentasGrupo.serie === undefined) {
                    console.log("140 ==================================================================");

                    $scope.resumenVentasGrupos.serie = selected.puntoVentaSerie.codigo;
                    console.log("   $scope.resumenVentasGrupo.serie " + $scope.resumenVentasGrupos.serie);
                    if (numero === 0)
                    {
                        grabada = 0;
                        igv = 0;
                        total = 0;
                        numero = parseInt(selected.numero);
                        //$scope.resumenVentasGrupo = {};
                        console.log("PUSH");
                        $scope.resumenVenta.resumenVentasGrupos.push($scope.resumenVentasGrupos);
                        $scope.resumenVentas.push($scope.resumenVentasGrupos);
                        
                    }
                    //numero2 = numero-1;
                    console.log("   numero " + numero);
                    console.log("   parseInt(selected.numero) " + parseInt(selected.numero));
                    if (numero === parseInt(selected.numero) /*|| numero2 === parseInt(selected.numero)*/) {
                        console.log("c1 " + c1 + "------------------------------------------------------------------");
                        if (c1 === 0) {
                            $scope.resumenVentasGrupos.desde = selected.numero;
                            console.log("   $scope.resumenVentasGrupo.desde " + $scope.resumenVentasGrupos.desde);
                        }
                        c1++;

                        $scope.resumenVentasGrupos.hasta = selected.numero;
                        console.log("   $scope.resumenVentasGrupo.hasta " + $scope.resumenVentasGrupos.hasta);

                        grabada = grabada + selected.totalGrabado;
                        igv = igv + selected.totalIgv;
                        total = total + selected.total;


                        $scope.resumenVentasGrupos.grabada = grabada.toFixed(2);
                        console.log("   $scope.resumenVentasGrupo.grabada " + $scope.resumenVentasGrupos.grabada);
                        $scope.resumenVentasGrupos.igv = igv.toFixed(2);
                        console.log("   $scope.resumenVentasGrupo.igv " + $scope.resumenVentasGrupos.igv);
                        $scope.resumenVentasGrupos.total = total.toFixed(2);
                        console.log("   $scope.resumenVentasGrupo.total " + $scope.resumenVentasGrupos.total);


                        console.log("c1 " + c1 + "------------------------------------------------------------------");

                    } else {
                        //return;
                        numero2 = numero + 1;
                        console.log("   numero2 " + numero2);
                        console.log("   parseInt(selected.numero) " + parseInt(selected.numero));

                        if (numero2 === parseInt(selected.numero)) {


                            console.log("c2 " + c2 + "------------------------------------------------------------------");

                            //$scope.resumenVentasGrupo = {};

                            if (c2 === 0) {
                                console.log("{}");
                                $scope.resumenVentasGrupos = {};
                                $scope.resumenVentasGrupos.serie = selected.puntoVentaSerie.codigo;
                                console.log("   $scope.resumenVentasGrupo.serie " + $scope.resumenVentasGrupos.serie);
                                grabada = 0;
                                igv = 0;
                                total = 0;
                                $scope.resumenVentasGrupos.desde = selected.numero;
                                console.log("   $scope.resumenVentasGrupo.desde " + $scope.resumenVentasGrupos.desde);
                                console.log("PUSH");
                                $scope.resumenVentas.push($scope.resumenVentasGrupos);
                            }
                            c2++;

                            $scope.resumenVentasGrupos.hasta = selected.numero;
                            console.log("   $scope.resumenVentasGrupo.hasta " + $scope.resumenVentasGrupos.hasta);

                            grabada = grabada + selected.totalGrabado;
                            igv = igv + selected.totalIgv;
                            total = total + selected.total;

                            console.log("108");
                            $scope.resumenVentasGrupos.grabada = grabada.toFixed(2);
                            console.log("   $scope.resumenVentasGrupo.grabada " + $scope.resumenVentasGrupos.grabada);
                            $scope.resumenVentasGrupos.igv = igv.toFixed(2);
                            console.log("   $scope.resumenVentasGrupo.igv " + $scope.resumenVentasGrupos.igv);
                            $scope.resumenVentasGrupos.total = total.toFixed(2);
                            console.log("   $scope.resumenVentasGrupo.total " + $scope.resumenVentasGrupos.total);

                            console.log("c2 " + c2 + "------------------------------------------------------------------");
                        }
                        else {
                            //$scope.resumenVentasGrupo = {};

                            console.log("c3 " + c3 + "------------------------------------------------------------------");

                            //$scope.resumenVentasGrupo = {};

                            if (c3 === 0) {
                                console.log("{}");
                                $scope.resumenVentasGrupos = {};
                                $scope.resumenVentasGrupos.serie = selected.puntoVentaSerie.codigo;
                                console.log("   $scope.resumenVentasGrupo.serie " + $scope.resumenVentasGrupos.serie);
                                grabada = 0;
                                igv = 0;
                                total = 0;
                                $scope.resumenVentasGrupos.desde = selected.numero;
                                console.log("   $scope.resumenVentasGrupo.desde " + $scope.resumenVentasGrupos.desde);
                                console.log("PUSH");
                                $scope.resumenVentas.push($scope.resumenVentasGrupos);
                            }
                            c3++;

                            $scope.resumenVentasGrupos.hasta = selected.numero;
                            console.log("   $scope.resumenVentasGrupo.hasta " + $scope.resumenVentasGrupos.hasta);

                            grabada = grabada + selected.totalGrabado;
                            igv = igv + selected.totalIgv;
                            total = total + selected.total;

                            console.log("108");
                            $scope.resumenVentasGrupos.grabada = grabada.toFixed(2);
                            console.log("   $scope.resumenVentasGrupo.grabada " + $scope.resumenVentasGrupos.grabada);
                            $scope.resumenVentasGrupos.igv = igv.toFixed(2);
                            console.log("   $scope.resumenVentasGrupo.igv " + $scope.resumenVentasGrupos.igv);
                            $scope.resumenVentasGrupos.total = total.toFixed(2);
                            console.log("   $scope.resumenVentasGrupo.total " + $scope.resumenVentasGrupos.total);

                            console.log("c3 " + c3 + "------------------------------------------------------------------");
                        }
                    }
                    numero++;
                    /*} else {
                     alert("diferente");
                     $scope.resumenVentasGrupos = [];
                     throw("exit");
                     }*/

                    console.log("222 ==================================================================");
                }
            });
            console.log("$scope.resumenVentasGrupo.serie " + $scope.resumenVentasGrupos.serie);
            console.log("$scope.resumenVentasGrupos " + $scope.resumenVentas);
            
            //$scope.personalDetails = newDataList;
        };

        $scope.selectDocumentoVentasBaja = function () {
            //$scope.resumenVentasGrupos = [];
            $scope.comunicacionBajaGrupos = [];
            $scope.comunicacionBajaGrupo = {};
            //$scope.resumenVentasGrupo.serie = 
            var c1 = 0;
            var c2 = 0;
            var grabada;
            var igv;
            var total;
            var numero = 0;
            angular.forEach($scope.documentoVentas, function (selected) {
                if (selected.selected) {
                    console.log("selected.$index " + selected.$index);
                    console.log("selected.numero " + selected.puntoVentaSerie.codigo);
                    console.log("selected.numero " + selected.numero);
                    console.log("selected.numero " + selected.totalGrabado);
                    console.log("selected.numero " + selected.totalIgv);
                    console.log("selected.numero " + selected.total);

                    console.log("selected.puntoVentaSerie.codigo " + selected.puntoVentaSerie.codigo);
                    console.log("$scope.resumenVentasGrupo.serie " + $scope.comunicacionBajaGrupo.serie);
                    /*if ($scope.comunicacionBajaGrupo.serie === selected.puntoVentaSerie.codigo || $scope.comunicacionBajaGrupo.serie === undefined) {
                     console.log("104 ");
                     $scope.comunicacionBajaGrupo.serie = selected.puntoVentaSerie.codigo;*/

                    if (numero === 0)
                    {
                        grabada = 0;
                        igv = 0;
                        total = 0;
                        numero = parseInt(selected.numero);
                        //$scope.resumenVentasGrupo = {};
                        $scope.comunicacionBajaGrupos.push($scope.resumenVentasGrupo);
                    }

                    if (numero === parseInt(selected.numero)) {
                        if (c1 === 0) {
                            $scope.comunicacionBajaGrupo.desde = selected.numero;
                        }
                        c1++;

                        $scope.comunicacionBajaGrupo.hasta = selected.numero;

                        grabada = grabada + selected.totalGrabado;
                        igv = igv + selected.totalIgv;
                        total = total + selected.total;

                        console.log("numero " + numero);
                        console.log("108");
                        $scope.comunicacionBajaGrupo.grabada = grabada;
                        $scope.comunicacionBajaGrupo.igv = igv;
                        $scope.comunicacionBajaGrupo.total = total;




                    } else {

                        $scope.resumenVentasGrupo.serie = selected.puntoVentaSerie.codigo;
                        if (c2 === 0) {
                            $scope.comunicacionBajaGrupo = {};
                            $scope.comunicacionBajaGrupos.push($scope.resumenVentasGrupo);
                            grabada = 0;
                            igv = 0;
                            total = 0;
                            $scope.comunicacionBajaGrupo.desde = selected.numero;
                        }
                        c2++;

                        $scope.comunicacionBajaGrupo.hasta = selected.numero;

                        grabada = grabada + selected.totalGrabado;
                        igv = igv + selected.totalIgv;
                        total = total + selected.total;

                        console.log("numero " + numero);
                        console.log("108");
                        $scope.comunicacionBajaGrupo.grabada = grabada;
                        $scope.comunicacionBajaGrupo.igv = igv;
                        $scope.comunicacionBajaGrupo.total = total;

                    }
                    numero++;
                    /*} else {
                     alert("diferente");
                     $scope.resumenVentasGrupos = [];
                     throw("exit");
                     }*/


                }
            });

            //$scope.personalDetails = newDataList;
        };
        $scope.crearResumenVentasGrupos = function (resumenVenta) {
            
            resumenVenta.tipo = "RSB";
            resumenVenta.estadoDocumentoVenta = {'codigo': 'CRE'};
            resumenVenta.numero = 1;
            resumenVenta.empresa = {'id': '1'};
            resumenVenta.usuarioByIdUsuarioCreacion = {'id': '3'};
            //resumenVentas.resumenVentasGrupo = {'id': '1'};
            //producto.unidad = {'codigo': 'NIU'};
            console.log("resumenVentasGruposVenta " + $scope.resumenVenta);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/resumenventa/crear',
                data: resumenVenta
            }).then(function success(response) {
                console.log(response.data);
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
        $scope.listar();
    }]);
