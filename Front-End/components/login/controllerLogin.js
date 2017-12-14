(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerLogin', function ($scope, authService, $location, toastr) {

    $scope.loginClass = ''
    $scope.registerClass = ''

    $scope.login = function (usuario) {

      authService.login(usuario)
      .then(
        function (response) {},

        function (response) {
          console.log(response);
          toastr.error('Usuario ou senha inválidos!');
        });
      };

    });
  })();
