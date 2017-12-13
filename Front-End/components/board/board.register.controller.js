(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoardRegister', function ($scope, $location, $window, boardService, toastr) {

  	$scope.board = { sessions: [] }

  	$scope.colorPallet = ['orange', 'blue', 'pink', 'green'];

  	$scope.submitSessionForm = function(session) {
  		console.log($scope.session);
  		console.log($scope.board.sessions);
  		$scope.board.sessions.push(JSON.parse(JSON.stringify(session)));
  		delete $scope.session;
  	}
    $scope.submitBoardForm = function(board){
      console.log(board);
      let promise = boardService.saveBoard(board).then(
          function(){
            toastr.success('Board cadastrado com sucesso!');
            $location.path('/dashboard');
          }, function(response){
            response.data.errors.forEach(error =>{
              toastr.error(error.defaultMessage)
            });
              console.log(response);
          });
      console.log(promise);
    }

  });

})();
