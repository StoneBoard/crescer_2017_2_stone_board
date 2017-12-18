(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoardRegister', function ($scope, $location, $window, boardService, toastr, utils) {

  	$scope.board = { sessions: [] }

  	$scope.colorPallet = utils.colorPallet;

    $scope.today = new Date();

  	$scope.submitSessionForm = function(session) {
      if ( typeof $scope.session === 'undefined'
        || typeof $scope.session.color === 'undefined' 
        || typeof $scope.session.title === 'undefined'
        || $scope.session.title.trim() === '' ) {
        toastr.error("Informe título e cor para a sessão");
        return;
    }
      
  		$scope.board.sessions.push(JSON.parse(JSON.stringify(session)));
  		delete $scope.session;
  	}
    $scope.submitBoardForm = function(board){
      let promise = boardService.saveBoard(board).then(
        function(){
          toastr.success('Board cadastrado com sucesso!');
          $location.path('/dashboard');
        }, 
        function(response){
          if (typeof(response.data.errors) === 'undefined')
            toastr.error(response.data.message)
          else 
          response.data.errors.forEach(error =>{
            toastr.error(error.defaultMessage)
          });
        }
      );
    }

  });

})();
