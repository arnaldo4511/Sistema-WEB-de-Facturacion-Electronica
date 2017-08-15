/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var RolApp = angular.module("RolApp", []);
RolApp.controller("RolController", ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $scope.rol = {};
        $http({
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            url: '/transportes_gepp/controlador/rol/listar'
        }).then(function mySucces(response) {
            $scope.roles = response.data;
        }, function myError(response) {
        });
        $scope.listar = function () {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/transportes_gepp/controlador/rol/listar'
            }).then(function mySucces(response) {
                $scope.roles = response.data;
            }, function myError(response) {
            });
        };
        $scope.guardarRol = function () {
            $http({
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/transportes_gepp/controlador/rol/crear',
                data: $scope.rol
            }).then(function mySucces(response) {
                $http({
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    url: '/transportes_gepp/controlador/rol/listar'
                }).then(function mySucces(response) {
                    $scope.roles = response.data;
                }, function myError(response) {
                });
            }, function myError(response) {
            });
        };
    }]);
