 (function(){
   'use strict'
  angular.module('stoneBoard').factory('resultGroupService', function($http){

    let urlBase = 'http://localhost:9090/api/resultGroup';

    function findById(id){
      return $http.get(urlBase + '/' + id);
    }

    function saveResultGroup(resultGroup){
      return $http.post(urlBase, resultGroup);
    }

    function updateResultGroup(resultGroup){
      return $http.put(urlBase, resultGroup);
    }

    function deleteResultGroup(idResultGroup, idBoard){
      return $http.put(urlBase + '/' + idResultGroup + '/' + idBoard);
    }

    function addCards(idResultGroup, idCard){ 
      return $http.put(urlBase + '/addCards/' + idResultGroup + '/' + idBoard);
    }

    return{
      findById : findById,
      saveResultGroup : saveResultGroup,
      updateResultGroup : updateResultGroup,
      deleteResultGroup : deleteResultGroup,
      addCards : addCards
    };

  });
}());