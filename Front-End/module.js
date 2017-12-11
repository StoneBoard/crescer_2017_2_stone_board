(function(){
  'use strict';

  angular
    .module('stoneBoard', ['ngStorage','ngRoute','ngStomp']);


  angular
    .module('stoneBoard').constant('authConfig', {

      urlUsuario: 'http://localhost:9090/api/initial/login',

      urlLogin: '/login',

      urlPrivado: '/#!/dashboard',

      urlLogout: '/home'
  });



})();
