(function(){
  'use strict'

  angular.module('stoneBoard')
    .config(function ($routeProvider) {

    $routeProvider

      .when('/login', {
        controller: '',
        templateUrl: ''
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
