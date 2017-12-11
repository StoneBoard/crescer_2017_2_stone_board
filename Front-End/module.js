(function(){
  'use strict';

  angular
    .module('stoneBoard', ['ngStorage','ngRoute','ngWebSocket']);


  angular
    .module('stoneBoard').constant('authConfig', {

      urlUsuario: 'http://localhost:9090/api/initial/login',

      urlLogin: '/login',

      urlPrivado: '/privado',

      urlLogout: '/home'
  });



})();
