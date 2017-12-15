 (function(){
   'use strict'
  angular.module('stoneBoard').factory('resultGroupService', function($http){

    let urlBase = 'http://localhost:9090/api/resultGroup';

    function findById(id){
      return $http.get(urlBase + '/' + id);
    }

    function findByBoard(idBoard){
      return $http.get(urlBase + '/findByBoard/' + idBoard);
    }

    function saveResultGroup(resultGroup){
      return $http.post(urlBase, resultGroup);
    }

    function updateResultGroup(resultGroup){
      return $http.put(urlBase, resultGroup);
    }

    function deleteResultGroup(idResultGroup, idBoard){
      return $http.delete(urlBase + '/' + idResultGroup + '/' + idBoard);
    }

    function addCards(idResultGroup, idCard){ 
      return $http.put(urlBase + '/addCards/' + idResultGroup + '/' + idCard);
    }

    return{
      findById : findById,
      findByBoard : findByBoard,
      saveResultGroup : saveResultGroup,
      updateResultGroup : updateResultGroup,
      deleteResultGroup : deleteResultGroup,
      addCards : addCards
    };

  });
}());