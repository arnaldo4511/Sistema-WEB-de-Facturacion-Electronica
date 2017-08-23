/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var RolApp = angular.module("RolApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
RolApp.filter('startFrom', function () {
    return function (input, start) {
        if (!input || !input.length) {
            return;
        }
        start = +start; //parse to int
        return input.slice(start);
    }
});
RolApp.controller("RolController", ['$scope', '$http', function ($scope, $http) {
        $scope.sesion = {};
        $scope.rolTmp = {};
        $scope.roles = [];
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

        $scope.mensajeTitutloRol = "";
        $scope.listar = function () {
            $http({
                method: 'GET',
                url: '/transportes_gepp/controlador/rol/listar'
            }).then(function success(response) {
                $scope.roles = response.data;
                $scope.viewby = 5;
                $scope.totalItems = $scope.roles.length;
                $scope.currentPage = 1;
                $scope.itemsPerPage = $scope.viewby;
                $scope.maxSize = 5; //Number of pager buttons to show
            }, function myError(response) {
            });
        };
        $scope.crearRol = function (rol) {
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/rol/crear',
                data: rol
            }).then(function success(response) {
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.editarRol = function (rol) {
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/rol/editar',
                data: rol
            }).then(function success(response) {
                console.log(response);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.eliminarRol = function (rol) {
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/rol/eliminar/' + rol.id
            }).then(function success(response) {
                console.log(response);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.nuevoRol = function () {
            $scope.mensajeTitutloRol = "Crear Rol";
            $scope.rolTmp = {};
            $scope.rolTmp.id = 0;
            console.log("$scope.rolTmp.id "+$scope.rolTmp.id);
        };
        $scope.seleccionarRol = function (rol) {
            $scope.mensajeTitutloRol = "Editar Rol";
            $scope.rolTmp = rol;
        };
        $scope.guardarRol = function (rol) {
            console.log(rol.id);
            if (rol.id === 0) {
                $scope.crearRol(rol);
            } else {
                $scope.editarRol(rol);
            }
        };
        $scope.listar();
    }]);
