(function(){
  'use strict'

  angular
    .module('stoneBoard')
    .config(function ($routeProvider) {

    $routeProvider

      .when('/login', {
        controller: 'controllerLogin',
        templateUrl: 'components/login/login.html'
      })

      .when('/dashboard', {
        controller: 'controllerDashboard',
        templateUrl: 'components/dashboard/dashboard.html',
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
      })

      .when('/board/:idBoard', {
        controller: 'controllerBoard',
        templateUrl: 'components/board/board.html',
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
      })


      .when('/board-register', {
        controller: 'controllerBoardRegister',
        templateUrl: 'components/board/board.register.html',
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
      })

      .when('/board/:idBoard/edit', {
        controller: 'controllerEditBoard',
        templateUrl: 'components/board/board.edit.html',
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
      })

      .when('/board/:idBoard/result-group', {
        controller: 'controllerResultGroup',
        templateUrl: 'components/ResultGroup/result.group.html',
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
      })

      .when('/board/:idBoard/result-group/detail/:idResultGroup?', {
        controller: 'controllerResultGroupDetail',
        templateUrl: 'components/ResultGroup/result.group.detail.html',
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
      })

      .otherwise('/login');

  });

})();
