/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var RolApp = angular.module("RolApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
RolApp.directive('ngEnter', function () {
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
        $scope.paginas = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.pagina = {value: 5};
        $scope.parametro = {};
        $scope.pagina.value = 5;
        $scope.totalItems = 0;
        $scope.currentPage = 1;
        $scope.itemsPerPage = $scope.pagina.value;
        $scope.maxSize = 5;

        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            console.log(response.data);
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
            $scope.parametros = [];
            if ($scope.parametro === {}) {
            } else
            {
                $scope.parametros.push({'nombre': 'nombre', 'valor': ($scope.parametro.nombre === undefined ? null : $scope.parametro.nombre)});
                $scope.parametros.push({'nombre': 'descripcion', 'valor': ($scope.parametro.descripcion === undefined ? null : $scope.parametro.descripcion)});
                $scope.parametros.push({'nombre': 'admin', 'valor': ($scope.parametro.admin === undefined ? null : $scope.parametro.admin)});
            }
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPage * $scope.itemsPerPage) - $scope.itemsPerPage).toString()});
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPage.toString()});
            console.log($scope.parametros);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/rol/listar',
                data: $scope.parametros
            }).then(function success(response) {
                console.log(response);
                $scope.roles = response.data.roles;
                $scope.totalItems = response.data.nroRoles;
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
        $scope.setSelected = function (row, item) {
            $scope.selectedRow = row;
            $scope.selectedUsuario = item;
        }
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
        $scope.listar();
    }]);
