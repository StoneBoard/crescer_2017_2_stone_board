angular
	.module('stoneBoard')
	.controller('controllerCommentsModal', function($scope, close, postIt, websocketService, authService) {
  	
  	$scope.close = close;

  	$scope.postIt = postIt;

    $scope.usuario = authService.getUsuario();

  	$scope.submitForm = function(comment) {
      comment.id_card = postIt.id;
  		websocketService.sendNewComment(comment, postIt.id, updateComment);
  	}

  	$scope.deleteComment = function(comment) {
  		websocketService.deleteComment(comment, postIt.id, updateComment);
  	}

  	function updateComment(_postIt){
  		$scope.$apply(function() {
  			$scope.postIt = angular.fromJson(_postIt.body).content;
  		});
  	}

});
