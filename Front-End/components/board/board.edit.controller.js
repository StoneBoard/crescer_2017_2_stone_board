(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerEditBoard', function($scope,toastr, $routeParams, boardService, personService){

		$scope.idBoard = $routeParams.idBoard

		$scope.today = new Date();

		displayBoard();
  		function displayBoard(){

      		let promisse =  boardService.findById($scope.idBoard);
      		promisse.then(function (response) {
	         	$scope.board = response.data;
	         	$scope.board.deadline = new Date($scope.board.deadline.slice(0,3).join());
	         	$scope.checked = $scope.board.deadline > new Date();
	         	$scope.members = $scope.board.members;
	         	$scope.active = $scope.board.deadline > new Date();
      		 });
      	}

      	$scope.addMembers = function(_id_person){
	  		let newMember = {
	  			id_board: $scope.idBoard,
	  			id_person: _id_person
	  		}
	  		boardService.addMembers(newMember).then(
	  			function(){
	            	toastr.success('Usuário adicionado com sucesso!')
	  				displayBoard();
	          	}, response => messageError(response.data)
	  		);
	  	}

	  	$scope.findPersonByEmail = function(email){
	  		let promisse =  personService.findByEmail(email);
	      	promisse.then(function (response) {
	       	  $scope.persons  = response.data.filter(p => $scope.members.filter(m => m.id === p.id).length === 0);
	        });
  		}

      $scope.changeStatus = function () {
          $scope.board.deadline = $scope.active ? null : new Date();
      }

  		$scope.submitBoardForm = function(board){
  			console.log("Entrou update");
	  		boardService.update(board).then(
	  			function(){
	            	toastr.success('Board editado com sucesso!')
	          	}, response => messageError(response.data)
	        );
  		}

  		function messageError(data){
  			data.errors.forEach(error =>{
	        	toastr.error(error.defaultMessage)
	        });
  		}

    });

})();
