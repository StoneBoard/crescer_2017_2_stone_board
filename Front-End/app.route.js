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
        templateUrl: 'components/dashboard/dashboard.html'/*,
        resolve: {
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }*/
      })
<<<<<<< HEAD
      .otherwise('/login');
=======

      .when('/board', {
        controller: 'controllerBoard',
        templateUrl: 'components/board/board.html'
      })

      .otherwise('/');
>>>>>>> 65ee3a26827c8c5678ccc4fe608e19fbf30c1057
  });

})();
