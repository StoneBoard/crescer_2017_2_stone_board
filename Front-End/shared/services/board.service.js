(function(){
  'use strict';
  angular.module('stoneBoard').factory('boardService', function($http){

  	let urlBase = 'http://localhost:9090/api/board';

  	function findById(id){
  		return $http.get( urlBase + "/findById/" + id);
  	}

  	return {
  		findById : findById

  	};
  })
}());
