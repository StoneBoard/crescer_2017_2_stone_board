(function(){
  'use strict';

  angular
    .module('stoneBoard', ['ngStorage','ngRoute','ngWebSocket','toastr','angularModalService']);

  angular
    .module('stoneBoard').constant('utils', {

      colorPallet: ['orange', 'blue', 'pink', 'green']

  });

  angular
    .module('stoneBoard').constant('authConfig', {

      urlUsuario: 'http://localhost:9090/api/initial/login',

      urlLogin: '/login',

      urlPrivado: '/#!/dashboard',

      urlLogout: '/home'
  });

})();
