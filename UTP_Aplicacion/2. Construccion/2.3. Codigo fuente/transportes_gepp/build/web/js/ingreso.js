/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var IngresoApp = angular.module("IngresoApp", []);
IngresoApp.controller("IngresoController", ['$scope', '$http','$window', function ($scope, $http,$window) {
        $scope.nombre = "";
        $scope.clave = "";
        $scope.ingresarSistema = function () {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: 'controlador/Usuario/ingresarSistema/' + $scope.nombre + "/" + $scope.clave
            }).then(function mySucces(response) {
                if ("OK" === response.data.RSP)
                {
                    $window.location.href = 'vista/principal.jsp';
                    //$location.path('configuration/streaming')
                }
            }, function myError(response) {
            });
            ;
        };
    }]);
