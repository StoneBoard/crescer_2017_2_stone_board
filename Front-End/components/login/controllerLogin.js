(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerLogin', function ($scope, authService, $location) {

    $scope.login = function (usuario) {

      authService.login(usuario)
      .then(
        function (response) {},
        function (response) {
          console.log(response);
          alert('Erro no Login!');
        });
      };

    });
  })();
