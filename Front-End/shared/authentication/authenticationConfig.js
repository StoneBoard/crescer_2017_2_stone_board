(function(){
  'use strict';

  angular
    .module('stoneBoard')
    .config(function ($httpProvider) {

    let headerAuth = JSON.parse(window.localStorage.getItem('ngStorage-headerAuth'));
    if (headerAuth) {
      $httpProvider.defaults.headers.common.Authorization = headerAuth;
    }
  });

})();
