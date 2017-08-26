/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var menuApp = angular.module("menuApp", []);
menuApp.controller("menuController", ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $scope.sesion = {};
        $http({method: 'GET', url: '/transportes_gepp/controlador/usuario/buscarsesion'}).then(function success(response) {
            $scope.sesion = response.data;
        }, function myError(response) {
        });
        $scope.selectLi = function () {
            console.log("selectLi");
            $scope.class = "active";
        };
    }]);
