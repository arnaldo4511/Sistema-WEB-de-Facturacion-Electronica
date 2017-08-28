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
        $scope.sesion = {};
        $scope.productoTmp = {};
        $scope.resumenVentas = [];
        $scope.unidades = [];
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            console.log("response.data "+response.data);
            $scope.sesion = response.data;
        }, function myError(response) {
        });
        $scope.setPage = function (pageNo) {
            $scope.currentPage = pageNo;
        };
        $scope.pageChanged = function () {
            console.log('Page changed to: ' + $scope.currentPage);
        };
        $scope.setItemsPerPage = function (num) {
            $scope.itemsPerPage = num;
            $scope.currentPage = 1; //reset to first paghe
        };

        $scope.mensajeTituloProducto = "";
        $scope.listar = function () {
            $http({
                method: 'GET',
                url: '/transportes_gepp/controlador/resumenventa/listar'
            }).then(function success(response) {
                console.log(response);
                console.log("response.data "+response.data);
                $scope.resumenVentas = response.data;
                $scope.viewby = 5;
                $scope.totalItems = $scope.resumenVentas.length;
                $scope.currentPage = 1;
                $scope.itemsPerPage = $scope.viewby;
                $scope.maxSize = 5; //Number of pager buttons to show
            }, function myError(response) {
            });
        };
        /*$scope.crearProducto = function (producto) {
            producto.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            producto.empresa = $scope.sesion.usuario.empresa;
            producto.unidad={'codigo':'NIU'}
            console.log("crearProducto "+producto);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/producto/crear',
                data: producto
            }).then(function success(response) {
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
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

        $scope.guardarProducto = function (producto) {
            console.log(producto);
            
            if (producto.id === 0) {
                $scope.crearProducto(producto);
            } else {
                $scope.editarProducto(producto);
            }
            console.log("producto.id "+producto.id);
        };*/
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
                console.log("filterCountry "+$scope.filterCountry);
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
