(function(){
  'use strict'
  angular.module('app').factory('personService', function($http){
  	
  	let urlBase = 'http://localhost:9090/api/person';

  	function listMyBoards(){
  		let response = $http.get(urlBase + '/listMyBoards');
  		return response;
  	}

  	function listConnectBoards(){
  		let response = $http.get(urlBase + '/listConnectBoards');
  		return response;
  	}

  	return {
  		listMyBoards : listMyBoards,
  		listConnectBoards : listConnectBoards
  	};
  })
})