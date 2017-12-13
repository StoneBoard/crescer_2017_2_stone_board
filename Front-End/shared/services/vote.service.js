 (function(){
   'use strict'
  angular.module('stoneBoard').factory('voteService', function($http){

    let urlBase = 'http://10.99.30.75:9090/api/vote';

    function saveVote(vote){

      return $http.post(urlBase, vote);
    }
   
    return {
      saveVote : saveVote
    };
  });
}());
