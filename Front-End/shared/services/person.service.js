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

  	return {
  		listMyBoards : listMyBoards,
  		listConnectBoards : listConnectBoards
  	};
  })
}());
