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
        controller: '',
        templateUrl: '',
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
      }).otherwise('/');
  });

  })();
