(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerEditBoard', function($scope, toastr, $routeParams, resultGroupService, authService, boardService, personService){

		$scope.idBoard = $routeParams.idBoard
		$scope.userLoged = authService.getUsuario();

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

	      }, 
	      response => messageError(response.data)
	  	);
	  }

	  $scope.findPersonByEmail = function(email){
	  	let promisse =  personService.findByEmail(email);
	    promisse.then(function (response) {
	    	$scope.persons  = response.data.filter(p => $scope.members.filter(m => m.id === p.id).length === 0);
	    	remove($scope.persons, $scope.persons.find(p => p.id === $scope.userLoged.id));
	    });
  	}

    $scope.changeStatus = function () {
      $scope.board.deadline = $scope.active ? null : new Date();
    }

  	$scope.submitBoardForm = function(board){
  		console.log(board.deadline)
  		if(typeof board.deadline !== 'undefined')
  			board.deadline = new Date(board.deadline.setHours(0,0,0,0));
	  	boardService.update(board).then(
	  		function(){
	        	toastr.success('Board editado com sucesso!')
	        }, response => messageError(response.data)
	      );
  	}

  	function remove(array, element) {
  		const index = array.indexOf(element);
   		if (index !== -1)
      	array.splice(index, 1);
		}

    findResultGroup($scope.idBoard);
    function findResultGroup(idBoard){
      let promise = resultGroupService.findByBoard(idBoard);
      promise.then(function (response) {
        $scope.myResultGroup = response.data;
      });
    }

  	function messageError(data){
  		if (typeof(data.errors) === 'undefined')
	      toastr.error(data.message);
  		else
	  		data.errors.forEach(error =>{
		     	toastr.error(error.defaultMessage)
		    });
  	}

  });

})();
