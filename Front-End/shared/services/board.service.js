 (function(){
   'use strict'
  angular.module('stoneBoard').factory('boardService', function($http){

    let urlBase = 'http://localhost:9090/api/board';

    function findById(id){
      return $http.get('http://localhost:9090/api/board/' + id);
    }

    function saveBoard(board){
      return $http.post(urlBase, board);
    }

    function addMembers(boardMembers){
      return $http.put('http://localhost:9090/api/board/addMember', boardMembers);
    }

    function update(board){
      return $http.put(urlBase, board);
    }
    
    return {
      saveBoard : saveBoard,
      findById : findById,
      addMembers : addMembers,
      update : update
    };
  });
}());
