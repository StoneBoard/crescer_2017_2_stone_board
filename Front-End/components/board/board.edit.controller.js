(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerEditBoard', function($scope, $routeParams, boardService, personService){

		$scope.idBoard = $routeParams.idBoard

		displayMembers();
  		function displayMembers(){
      		let promisse =  boardService.findById(idBoard);
      		promisse.then(function (response) {

	         	$scope.board = response.data;
	         	$scope.members = $scope.board.members;
      		 });
      	}
      	$scope.addMembers = function(_id_person){
	  		let newMember = {
	  			id_board: $scope.id_board,
	  			id_person: _id_person
	  		}
	  		let promisse = boardService.addMembers(newMember).then();
	  	}

	  	$scope.findPersonByEmail = function(email){
	  		let promisse =  personService.findByEmail(email);
	      	promisse.then(function (response) {
	       	  $scope.persons  = response.data;
	        }); 	
  		}
  	
  	
    }

		
  });

})();