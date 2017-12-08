(function(){
  'use strict';

  angular
    .module('stoneBoard')
    .controller('nomeController as nomeCtrl', nomeController);

  function nomeController() {
    var vm = this;
      vm.teste = teste;
      vm.user = [];


      function teste(id) {

      }
  }

})();
