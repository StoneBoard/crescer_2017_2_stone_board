(function(){
  'use strict';

  angular
    .module('stoneBoard', ['ngStorage','ngRoute']);


  angular
    .module('stoneBoard').constant('authConfig', {

      urlUsuario: 'http://localhost:9090/api/api/login',

      urlLogin: '/login',

      urlPrivado: '/privado',

      urlLogout: '/home'
  });



})();
