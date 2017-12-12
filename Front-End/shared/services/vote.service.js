 (function(){
   'use strict'
  angular.module('stoneBoard').factory('voteService', function($http){

    let urlBase = 'http://localhost:9090/api/vote';

    function saveVote(vote){

      return $http.post(urlBase, vote);
    }
   
    return {
      saveVote : saveVote
    };
  });
}());
