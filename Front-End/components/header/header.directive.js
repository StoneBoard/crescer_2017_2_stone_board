(function(){
  'use strict';

  angular.module('stoneBoard')
	.directive('navBarHeader', function (authService, $rootScope) {

    return {

      restrict: 'E',

      scope: {},

      templateUrl: 'components/header/headerSuperior.html',

      controller: function ($scope) {

        $scope.scrollTo = function(resp-menu) {
        $location.hash(resp-menu);
        $anchorScroll();
      }


        atualizarUsuario();

        $scope.logout = authService.logout;
        $scope.usuario = authService.getUsuario();
        $rootScope.$on('authLoginSuccess', function () {
          atualizarUsuario();
        });

        $rootScope.$on('authLogoutSuccess', function () {
          atualizarUsuario();
        });

        function atualizarUsuario() {
          $scope.usuario = authService.getUsuario();
        }
      }
    }

  });

})();
