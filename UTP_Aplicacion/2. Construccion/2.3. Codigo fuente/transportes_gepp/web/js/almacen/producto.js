/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ProductoApp = angular.module("ProductoApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
ProductoApp.filter('startFrom', function () {
    return function (input, start) {
        if (!input || !input.length) {
            return;
        }
        start = +start; //parse to int
        return input.slice(start);
    }
});
ProductoApp.controller("ProductoController", ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $scope.sesion = {};
        $scope.productoTmp = {};
        $scope.productos = [];
        $scope.unidades = [];
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            console.log(response.data);
            $scope.sesion = response.data;
            $scope.unidades = $scope.sesion.unidades;
        }, function myError(response) {
        });
        
        
        $scope.totalItemsListarProductos = 0;
        $scope.currentPageListarProductos = 1;
        $scope.paginasListarProductos = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.paginaListarProductos  = {value: 5};
        $scope.itemsPerPageListarProductos = $scope.paginaListarProductos .value;

        $scope.mensajeTituloProducto = "";
        $scope.mensajeConfirmacion = "";
        
        $scope.parametro = {};
        $scope.listar = function () {
            $scope.parametros = [];
            $scope.parametros.push({'nombre': 'nombre', 'valor': $scope.parametro.nombre});
            $scope.parametros.push({'nombre': 'descripcion', 'valor': $scope.parametro.descripcion});
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPageListarProductos * $scope.itemsPerPageListarProductos) - $scope.itemsPerPageListarProductos).toString()});
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPageListarProductos.toString()});
            
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/producto/listar',
                data: $scope.parametros
            }).then(function success(response) {
                console.log(response);
                console.log("response.data "+response.data);
                $scope.productos = response.data.productos;
                $scope.totalItemsListarProductos = response.data.nroProductos;
            }, function myError(response) {
            });
        };
        
        $scope.setPage = function (pageNo) {
            $scope.currentPageListarProductos = pageNo;
            $scope.listar();
        };
        $scope.pageChanged = function () {
            $scope.listar();
        };
        $scope.setItemsPerPage = function (num) {
            $scope.itemsPerPageListarProductos = num;
            $scope.currentPageListarProductos = 1; //reset to first paghe
            $scope.listar();
        };
        
        $scope.crearProducto = function (producto) {
            producto.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            producto.empresa = $scope.sesion.usuario.empresa;
            producto.unidad={'codigo':'NIU'}
            console.log("crearProducto "+producto);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/producto/crear',
                data: producto
            }).then(function success(response) {
                $("#modalProducto").modal("hide");
                console.log("CREAR");
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
                $("#modalProducto").modal("hide");
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
            $scope.mensajeTituloProducto = "Crear Servicio";
            $scope.mensajeConfirmacion = "¿Desea crear el servicio?";
            $scope.productoTmp = {};
            $scope.productoTmp.id = 0;
            console.log("nuevo "+$scope.productoTmp.id);
        };
        $scope.seleccionarProducto = function (producto) {
            $http({method: 'POST', url: '/transportes_gepp/controlador/producto/buscar/' + producto.id
            }).then(function mySucces(response) {
                $scope.productoTmp = response.data;
                $scope.mensajeTituloProducto = "Editar Servicio";
                $scope.mensajeConfirmacion = "¿Desea editar el servicio?";
            }, function myError(response) {
            });
        };

        $scope.guardarProducto = function (producto) {
            console.log(producto);
            
            if (producto.id === 0) {
                $scope.crearProducto(producto);
            } else {
                $scope.editarProducto(producto);
            }
            console.log("producto.id "+producto.id);
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
