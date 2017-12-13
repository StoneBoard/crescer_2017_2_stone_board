 (function(){
   'use strict'
  angular.module('stoneBoard').factory('postitService', function($http){

    let urlBase = 'http://10.99.30.75:9090/api/card';

    function saveCard(card){

      return $http.post(urlBase, card);
    }
    function editCard(card){

      return $http.put(urlBase, card);
    }
    function deleteCard(id){
      return $http.delete('http://10.99.30.75:9090/api/card/' + id);
    }
    return {
      saveCard : saveCard,
      editCard : editCard,
      deleteCard : deleteCard

    };
  });
}());
