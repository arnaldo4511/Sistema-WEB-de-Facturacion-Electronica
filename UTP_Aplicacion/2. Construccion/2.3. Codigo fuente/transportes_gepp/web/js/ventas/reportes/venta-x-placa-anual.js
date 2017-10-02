/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ventaXPlacaAnualApp = angular.module("ventaXPlacaAnualApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
ventaXPlacaAnualApp.controller("ventaXPlacaAnualController", ['$scope', '$http', '$filter', '$window', '$sce', function ($scope, $http, $filter, $window, $sce) {
        $scope.sesion = {};
        $scope.anhios = [
            {value: 2017},
            {value: 2018},
            {value: 2019},
            {value: 2020},
            {value: 2021}];
        $scope.anhio = {value: 2017};
        $scope.url = $sce.trustAsResourceUrl('/transportes_gepp/controlador/ventas/reporte/venta-x-placa-anual/' + $scope.anhio.value + '/1');
        $scope.cambioAnhio = function () {
            $scope.url = $sce.trustAsResourceUrl('/transportes_gepp/controlador/ventas/reporte/venta-x-placa-anual/' + $scope.anhio.value + '/1');
        };
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            //console.log(response.data);
            $scope.sesion = response.data;
        }, function myError(response) {
        });
    }]);