angular.module('stoneBoard')
    .config(function ($routeProvider) {
    
      $routeProvider

        .when('/login', {
          controller: 'controllerLogin',
          templateUrl: ''
        })
    
        .when('/dashboard', {
          controller: 'controllerDashboard',
          templateUrl: '',
          resolve: {
            autenticado: function (authService) {
              return authService.isAutenticadoPromise();
            }
          }
        }).otherwise('/login');
    });
    