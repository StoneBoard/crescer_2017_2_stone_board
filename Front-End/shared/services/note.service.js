(function(){
   'use strict'
  angular.module('stoneBoard').factory('noteService', function($http){

    let urlBase = 'http://localhost:9090/api/note';

    function findByCard(idCard){
      return $http.get(urlBase + '/findByCard/' + idCard);
    }

    function saveNote(note){
      return $http.post(urlBase, note);
    }

    function deleteNote(idNote){
      return $http.delete(urlBase + '/' + idNote);
    }

    return{
      findByCard : findByCard,
      saveNote : saveNote,
      deleteNote : deleteNote
    };

  });
}());