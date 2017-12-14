 (function(){
   'use strict';
  angular
  .module('stoneBoard')
  .factory('personService', function($http){

  	let urlBase = 'http://localhost:9090/api/person';

  	function listMyBoards(){
  		return $http.get( urlBase + "/listMyBoards");
  	}

  	function listConnectBoards(){
  		return $http.get(urlBase + '/listConnectBoards');
  	}
    function findByEmail(email){
      return $http.get(urlBase + '/' + email);
    }

  	return {
  		listMyBoards : listMyBoards,
  		listConnectBoards : listConnectBoards,
      findByEmail : findByEmail
  	};
  })
}());
