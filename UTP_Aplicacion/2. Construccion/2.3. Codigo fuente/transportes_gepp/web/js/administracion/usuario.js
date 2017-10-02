/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var UsuarioApp = angular.module("UsuarioApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
UsuarioApp.directive('ngEnter', function () {
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
UsuarioApp.controller("UsuarioController", ['$scope', '$http', function ($scope, $http) {
        $scope.sesion = {};
        $scope.rol = {};
        $scope.usuario = {};

        $scope.usuarios = [];
        $scope.roles = [];
        $scope.puntoVentas = [];


        $scope.entidades = [];
        $scope.nombreEntidad = "";

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
            $scope.roles = $scope.sesion.roles;
            $scope.puntoVentas = $scope.sesion.puntoVentas;
        }, function myError(response) {
        });
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

        $scope.mensajeTituloRol = "";
        $scope.listar = function () {
            $scope.parametros = [];
            if ($scope.parametro === {}) {
            } else
            {
                $scope.parametros.push({'nombre': 'nombre', 'valor': ($scope.parametro.nombre === undefined ? null : $scope.parametro.nombre)});
                $scope.parametros.push({'nombre': 'clave', 'valor': ($scope.parametro.clave === undefined ? null : $scope.parametro.clave)});
                $scope.parametros.push({'nombre': 'idRol', 'valor': ($scope.parametro.rol === undefined ? null : $scope.parametro.rol.id)});
                $scope.parametros.push({'nombre': 'idPuntoVenta', 'valor': ($scope.parametro.puntoVenta === undefined ? null : $scope.parametro.puntoVenta.id)});
                $scope.parametros.push({'nombre': 'activo', 'valor': ($scope.parametro.activo === undefined ? null : $scope.parametro.activo)});
            }
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPage * $scope.itemsPerPage) - $scope.itemsPerPage).toString()});
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPage.toString()});
            console.log($scope.parametros);

            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/usuario/listar',
                data: $scope.parametros
            }).then(function success(response) {
                console.log(response.data);
                $scope.usuarios = response.data;
                $scope.totalItems = response.data.nroUsuarios;
            }, function myError(response) {
            });
        };
        $scope.crear = function (usuario) {
            usuario.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            usuario.empresa = $scope.sesion.usuario.empresa;
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/usuario/crear',
                data: usuario
            }).then(function success(response) {
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.editar = function (usuario) {
            usuario.usuarioByIdUsuarioModificacion = $scope.sesion.usuario;
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/usuario/editar',
                data: usuario
            }).then(function success(response) {
                console.log(response);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.eliminar = function (usuario) {
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/usuario/eliminar/' + usuario.id
            }).then(function success(response) {
                console.log(response);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.nuevo = function () {
            $scope.mensajeTitulo = "Crear Usuario";
            $scope.usuario = {};
            $scope.usuario.id = 0;
            $scope.usuario.rol = $scope.sesion.usuario.rol;
            $scope.usuario.puntoVenta = $scope.sesion.usuario.puntoVenta;
        };
        $scope.seleccionarItem = function (item) {
            $scope.mensajeTitulo = "Editar Usuario";
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/usuario/buscar/' + item.id
            }).then(function success(response) {
                //console.log(response);
                $scope.usuario = response.data;
            });
        };
        $scope.guardar = function (usuario) {
            $scope.usuario = {};
            if (usuario.id === 0) {
                $scope.crear(usuario);
            } else {
                $scope.editar(usuario);
            }
        };
        $scope.completarEntidad = function (criterio) {
            if (criterio === undefined || criterio.trim() === "")
            {
                $scope.entidades = [];
                return;
            }
            $http({method: 'GET', url: '/transportes_gepp/controlador/entidad/autocompletar/' + criterio
            }).then(function mySucces(response) {
                $scope.entidades = response.data;
                $scope.ocultarEntidadesAuto = false;
            }, function myError(response) {
            });
        };
        $scope.seleccionarEntidad = function (item) {
            $scope.usuario.entidad = item;
            $scope.ocultarEntidadesAuto = true;
        };
        $scope.listar();
    }]);
