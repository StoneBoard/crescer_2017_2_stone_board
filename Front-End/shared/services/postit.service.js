// (function(){
//   'use strict'
  angular.module('stoneBoard').factory('postitService', function($http){

    let urlBase = 'http://localhost:9090/api/card'; 

    function saveCard(card){

      return $http.post(urlBase, card);
    }
    return {
      saveCard : saveCard
      
    };
  });
//})
