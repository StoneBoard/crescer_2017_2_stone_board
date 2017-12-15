 (function(){
   'use strict';
  angular
  .module('stoneBoard')
  .factory('personService', function($http){

  	let urlBase = 'http://localhost:9090/api/person';

    function savePerson(person){
      return $http.post(urlBase +'/addPerson' , person);
    }

  	function listMyBoards(){
  		return $http.get( urlBase + "/listMyBoards");
  	}

  	function listConnectBoards(){
  		return $http.get(urlBase + '/listConnectBoards');
  	}

    function findByEmail(email){
      return $http.get(urlBase + '/' + email);
    }

    function isAdmin(id){
      return $http.get(urlBase + '/isAdmin/' + id);
    }

  	return {
      savePerson : savePerson,
  		listMyBoards : listMyBoards,
  		listConnectBoards : listConnectBoards,
      findByEmail : findByEmail,
      isAdmin : isAdmin
  	};
  })
}());
