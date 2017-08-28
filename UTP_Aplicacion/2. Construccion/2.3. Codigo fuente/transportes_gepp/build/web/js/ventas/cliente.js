/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ClienteApp = angular.module("ClienteApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);

ClienteApp.controller("ClienteController", ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $scope.sesion = {};
        //$scope.clienteTmp = {};
        $scope.entidadTmp = {};
        $scope.clientes = [];
        $scope.tiposDocumentosEntidades = [];
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            console.log("response.data " + response.data);
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
                console.log('response :' + response);
                console.log('response.data :' + response.data);
                $scope.clientes = response.data;
                $scope.viewby = 5;
                $scope.totalItems = $scope.clientes.length;
                $scope.currentPage = 1;
                $scope.itemsPerPage = $scope.viewby;
                $scope.maxSize = 5; //Number of pager buttons to show
                console.log('$scope.clientes ' + $scope.clientes);
            }, function myError(response) {
                console.log('error :' + response);
            });
        };
        $scope.clienteTmp = {};
        $scope.nuevoCliente = function () {
            $scope.mensajeTituloCliente = "Crear Cliente";
            $scope.clienteTmp = {};
            $scope.clienteTmp.id = 0;
            //console.log("$scope.entidadTmp.id " + $scope.entidadTmp.id);
        };
        $scope.guardar = function (cliente) {
            if (cliente.id === 0) {
                $scope.crearCliente(cliente);
            } else {
                $scope.editarCliente(cliente);
            }
        };
        $scope.crearCliente = function (cliente) {
            cliente.entidad.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            cliente.entidad.ubigeo = {'codigo': '030101'};
            cliente.usuarioByIdUsuarioCreacion = $scope.sesion.usuario;
            console.log("cliente:" + cliente);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/crear',
                data: cliente
            }).then(function success(response) {
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.seleccionarItem = function (item) {

            $scope.mensajeTituloCliente = "Editar Cliente";
            $scope.clienteTmp = item;
            console.log("item " + item);
            console.log("entidadTmp " + $scope.entidadTmp);
        };
        $scope.eliminar = function (entidad) {
            console.log("entidad " + entidad);
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
        $scope.editarCliente = function (cliente) {
            cliente.entidad.usuarioByIdUsuarioModificacion = $scope.sesion.usuario;
            cliente.usuarioByIdUsuarioModificacion = $scope.sesion.usuario;
            console.log("cliente " + cliente.usuarioByIdUsuarioModificacion);
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/editar',
                data: cliente
            }).then(function success(response) {
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.listar();
    }]);
