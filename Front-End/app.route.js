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

      .when('/board/:idBoard?', {
        controller: 'controllerBoard',
        templateUrl: 'components/board/board.html'
      })


      .when('/board-register', {
        controller: 'controllerBoardRegister',
        templateUrl: 'components/board/board.register.html'
      })

      .otherwise('/login');

  });

})();
