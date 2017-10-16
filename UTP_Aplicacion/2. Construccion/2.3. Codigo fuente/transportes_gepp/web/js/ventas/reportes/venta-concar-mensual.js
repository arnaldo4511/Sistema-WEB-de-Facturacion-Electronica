/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var ventaConcarMensualApp = angular.module("ventaConcarMensualApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);
ventaConcarMensualApp.controller("ventaConcarMensualController", ['$scope', '$http', '$filter', '$window', '$sce', function ($scope, $http, $filter, $window, $sce) {
        $scope.sesion = {};
        $scope.anhios = [
            {codigo: 2017},
            {codigo: 2018},
            {codigo: 2019},
            {codigo: 2020},
            {codigo: 2021}];
        $scope.meses = [
            {codigo: 1, nombre: "ENERO"},
            {codigo: 2, nombre: "FEBRERO"},
            {codigo: 3, nombre: "MARZO"},
            {codigo: 4, nombre: "ABRIL"},
            {codigo: 5, nombre: "MAYO"},
            {codigo: 6, nombre: "JUNIO"},
            {codigo: 7, nombre: "JULIO"},
            {codigo: 8, nombre: "AGOSTO"},
            {codigo: 9, nombre: "SEPTIEMBRE"},
            {codigo: 10, nombre: "OCTUBRE"},
            {codigo: 11, nombre: "NOVIEMBRE"},
            {codigo: 12, nombre: "DICIEMBRE"}];
        $scope.anhio = {codigo: 2017};
        $scope.mes = {codigo: 9};
        $scope.diaInicio = 1;
        $scope.diaFin = 10;
        $scope.inicioCorrelativo = 1;
        $scope.generarUrl = function () {
            var url = '/transportes_gepp/controlador/ventas/reporte/con-documento-venta/';
            url += 'xls/';
            url += $scope.sesion.usuario.empresa.id + '/';
            url += $scope.anhio.codigo + '/';
            url += $scope.mes.codigo + '/';
            url += $scope.diaInicio + '/';
            url += $scope.diaFin + '/';
            url += $scope.inicioCorrelativo;
            $scope.url = $sce.trustAsResourceUrl(url);
        };
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            $scope.sesion = response.data;
            $scope.generarUrl();
        }, function myError(response) {
        });
    }]);