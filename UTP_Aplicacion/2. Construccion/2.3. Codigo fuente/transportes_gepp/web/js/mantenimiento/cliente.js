/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ClienteApp = angular.module("ClienteApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
ClienteApp.filter('startFrom', function () {
    return function (input, start) {
        if (!input || !input.length) {
            return;
        }
        start = +start; //parse to int
        return input.slice(start);
    }
});
ClienteApp.controller("ClienteController", ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $scope.sesion = {};
        //$scope.clienteTmp = {};
        $scope.entidadTmp = {};
        $scope.clientes = [];
        $scope.tiposDocumentosEntidades = [];
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            console.log("response.data "+response.data);
            $scope.sesion = response.data;
            $scope.tiposDocumentosEntidades = $scope.sesion.tiposDocumentosEntidades;
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
                url: '/transportes_gepp/controlador/cliente/listar'
            }).then(function mySucces(response) {
                console.log('response ' + response);
                console.log('response.data ' + response.data);
                $scope.clientes = response.data;
                $scope.viewby = 5;
                $scope.totalItems = $scope.clientes.length;
                $scope.currentPage = 1;
                $scope.itemsPerPage = $scope.viewby;
                $scope.maxSize = 5; //Number of pager buttons to show
                console.log('$scope.clientes ' + $scope.clientes);
            }, function myError(response) {
            });
        };
        $scope.nuevoCliente = function () {
            $scope.mensajeTituloCliente = "Crear Cliente";
            $scope.entidadTmp = {};
            $scope.entidadTmp.id = 0;
            console.log("$scope.entidadTmp.id "+$scope.entidadTmp.id);
        };
        $scope.guardar = function (entidad) {
            console.log("entidad "+entidad);
            
            if (entidad.id === 0) {
                $scope.crearEntidad(entidad);
            } else {
                $scope.editarEntidad(entidad);
            }
            console.log("entidad.id "+entidad.id);
        };
        $scope.crearEntidad = function (entidad) {
            entidad.entidad.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            //entidad.empresa = $scope.sesion.usuario.empresa;
            //entidad.unidad={'codigo':'NIU'}
            console.log("crearEntidad "+entidad.usuarioByIdUsuarioCreacion);
            console.log("$scope.sesion.usuario "+$scope.sesion.usuario);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/crear',
                data: entidad
            }).then(function success(response) {
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.seleccionarItem = function (item) {
            
            $scope.mensajeTituloCliente = "Editar Cliente";
            $scope.entidadTmp = item;
            console.log("item "+item);
            console.log("entidadTmp "+$scope.entidadTmp);
        };
        $scope.eliminar = function (entidad) {
            console.log("entidad "+entidad);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/eliminar/' + entidad.id
            }).then(function success(response) {
                console.log(response);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.editarEntidad = function (entidad) {
            console.log("entidad "+entidad);
            entidad.usuarioByIdUsuarioModificacion = $scope.sesion.usuario;
            console.log("editarEntidad "+entidad);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/editar',
                data: entidad
            }).then(function success(response) {
                console.log(response);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.listar();
    }]);
