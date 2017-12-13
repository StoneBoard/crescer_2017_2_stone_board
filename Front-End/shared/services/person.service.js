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
      console.log("entrou service")
      debugger;
      let result = $http.get(urlBase, email);
      debugger;
      return result;
    }

  	return {
  		listMyBoards : listMyBoards,
  		listConnectBoards : listConnectBoards,
      findByEmail : findByEmail
  	};
  })
}());
