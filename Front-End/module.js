(function(){
  'use strict';

  angular
    .module('stoneBoard', ['ngStorage','ngRoute','ngWebSocket','toastr']);


  angular
    .module('stoneBoard').constant('authConfig', {

      urlUsuario: 'http://10.99.30.75:9090/api/initial/login',

      urlLogin: '/login',

      urlPrivado: '/#!/dashboard',

      urlLogout: '/home'
  });



})();
