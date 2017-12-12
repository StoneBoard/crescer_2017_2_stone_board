(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoardRegister', function ($scope, $window, boardService) {

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
      let promise = boardService.saveBoard(board).then();
      console.log(promise);

    }

  });

})();
