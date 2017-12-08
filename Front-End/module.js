(function(){
  'use strict';

  angular
    .module('stoneBoard', ['ngStorage','ngRoute']);



  angular
    .module('stoneBoard').constant('authConfig', {

      // Obrigatória - URL da API que retorna o usuário

      //urlUsuario: 'http://10.99.0.12:3296/api/acessos/usuarioLogado',
      //urlUsuario: 'http://10.99.0.24/AutDemo.WebApi/api/acessos/usuariologado',

      urlUsuario: 'http://localhost:9090/api/acessos/usuario',

      urlLogin: '/login',

      urlPrivado: '/privado',

      urlLogout: '/home'
  });



})();
