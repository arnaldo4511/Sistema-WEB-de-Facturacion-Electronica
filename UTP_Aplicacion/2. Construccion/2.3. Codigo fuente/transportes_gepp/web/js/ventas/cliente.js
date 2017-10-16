/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ClienteApp = angular.module("ClienteApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
angular.module('ClienteApp').directive('formatterDirective', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$formatters.push(function (val) {
                return parseInt(val, 10);
            });
        }
    };
});
ClienteApp.controller("ClienteController", ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $scope.sesion = {};
        //$scope.clienteTmp = {};
        $scope.entidadTmp = {};
        $scope.clientes = [];
        $scope.tiposDocumentosEntidades = [];
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            console.log("response.data " + response.data);
            $scope.sesion = response.data;
            //$scope.tiposDocumentosEntidades = $scope.sesion.tiposDocumentosEntidades;
            $scope.tiposDocumentosEntidads = $scope.sesion.tipoDocumentoEntidads;
            console.log("response.data "+response.data);
            console.log("$scope.sesion.tipoDocumentoEntidads "+$scope.sesion.tipoDocumentoEntidads);
        }, function myError(response) {
        });
        
        $scope.totalItemsListarClientes = 0;
        $scope.currentPageListarClientes = 1;
        $scope.paginasListarClientes = [
            {value: 5},
            {value: 10},
            {value: 20},
            {value: 40},
            {value: 50}];
        $scope.paginaListarClientes  = {value: 5};
        $scope.itemsPerPageListarClientes = $scope.paginaListarClientes .value;

        $scope.mensajeTituloProducto = "";
        $scope.parametro = {};
        $scope.parametro.estadoDocumentoVenta = {};
        $scope.listar = function () {
            $scope.parametros = [];
            $scope.parametros.push({'nombre': 'documento', 'valor': $scope.parametro.documento});
            $scope.parametros.push({'nombre': 'nombre', 'valor': $scope.parametro.nombre});
            $scope.parametros.push({'nombre': 'currentPage', 'valor': (($scope.currentPageListarClientes * $scope.itemsPerPageListarClientes) - $scope.itemsPerPageListarClientes).toString()});
            $scope.parametros.push({'nombre': 'itemsPerPage', 'valor': $scope.itemsPerPageListarClientes.toString()});
            $http({
                method: 'POST',
                url: '/transportes_gepp/controlador/cliente/listar',
                data: $scope.parametros
            }).then(function mySucces(response) {
                
                $scope.clientes = response.data.clientes;
                $scope.totalItemsListarClientes = response.data.nroClientes;
                console.log('response :' + response);
                console.log('response.data.clientes :' + response.data.clientes);
                console.log('$scope.clientes ' + $scope.clientes);
            }, function myError(response) {
                console.log('error :' + response);
            });
        };
        $scope.setPage = function (pageNo) {
            $scope.currentPageListarClientes = pageNo;
            $scope.listar();
        };
        $scope.pageChanged = function () {
            $scope.listar();
        };
        $scope.setItemsPerPage = function (num) {
            $scope.itemsPerPageListarClientes = num;
            $scope.currentPageListarClientes = 1; //reset to first paghe
            $scope.listar();
        };
        $scope.clienteTmp = {};
        $scope.nuevoCliente = function () {
            $("[name='documento']").attr('disabled','');
            $scope.mensajeTituloCliente = "Crear Cliente";
            $scope.mensajeConfirmacion = "¿Desea crear el cliente?";
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
                $("#modalItem").modal("hide");
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.seleccionarItem = function (item) {
            //$("[name='documento']").removeAttr('disabled');
            $http({method: 'POST', url: '/transportes_gepp/controlador/cliente/buscar/' + item.id
            }).then(function mySucces(response) {
                $scope.clienteTmp = response.data;
                $scope.mensajeTituloCliente = "Editar Cliente";
                $scope.mensajeConfirmacion = "¿Desea editar el cliente?";
                $scope.cambioTipoDocumentoVenta();
            }, function myError(response) {
            });
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
                $("#modalItem").modal("hide");
                console.log(response.data);
                $scope.listar();
            }, function error(response) {
                console.log(response.data);
            });
        };
        $scope.cambiarDocumento = {};
        $scope.cambiarDocumento.clienteTmp = {};
        $scope.cambiarDocumento.clienteTmp.entidad = {};

        $scope.cambioTipoDocumentoVenta = function () {
            //alert("dfdf " + $scope.clienteTmp.entidad.tipoDocumentoEntidad.codigo);
            $("[name='documento']").removeAttr('disabled');
            if ($scope.clienteTmp.entidad.tipoDocumentoEntidad.codigo === "1") {
                $scope.cambiarDocumento.clienteTmp.entidad.documento = {
                    minlength: 8,
                    maxlength: 8
                };
            } else if ($scope.clienteTmp.entidad.tipoDocumentoEntidad.codigo === "6") {
                $scope.cambiarDocumento.clienteTmp.entidad.documento = {
                    minlength: 11,
                    maxlength: 11
                };
            }
        }

        $scope.buscarPadron = function (clienteTmp) {
            $http({
                method: 'GET',
                url: '/gepp_erp/srvContribuyente?parAccion=buscarContribuyente&criterio=' + clienteTmp.entidad.documento
            }).then(function success(response) {
                clienteTmp.entidad.nombre = response.data.RESULT.con_nombre;
                var ubigeo = {"codigo": response.data.RESULT.con_ubigeo};
                clienteTmp.entidad.ubigeo = ubigeo;
                clienteTmp.entidad.direccion = response.data.RESULT.con_direccion;
                clienteTmp.entidad.tipoDocumentoEntidad = {"codigo": '6'}
            }, function error(response) {
                //console.log(response.data);
            });
        };


        $scope.listar();
    }]);
