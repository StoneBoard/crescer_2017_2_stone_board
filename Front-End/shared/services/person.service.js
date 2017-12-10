// (function(){
//   'use strict'
  angular.module('stoneBoard').factory('personService', function($http){

  	let urlBase = 'http://localhost:9090/api/person';

  	function listMyBoards(){
  		return response = $http.get( urlBase + "/listMyBoards");
  	}

  	function listConnectBoards(){
  		return response = $http.get(urlBase + '/listConnectBoards');
  	}

  	return {
  		listMyBoards : listMyBoards,
  		listConnectBoards : listConnectBoards
  	};
  })
//})
