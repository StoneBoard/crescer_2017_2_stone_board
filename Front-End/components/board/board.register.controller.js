(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoardRegister', function ($scope, $window) {

  	$scope.board = { sessions: [] }

  	$scope.colorPallet = ['orange', 'blue', 'pink', 'green'];

  	$scope.submitSessionForm = function(session) {
  		console.log($scope.session);
  		console.log($scope.board.sessions);
  		$scope.board.sessions.push(JSON.parse(JSON.stringify(session)));
  		delete $scope.session;
  	}

  });

})();