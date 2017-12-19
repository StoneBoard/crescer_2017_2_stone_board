angular
	.module('stoneBoard')
	.controller('controllerCommentsModal', function($scope, close, postIt, boardAtivo, websocketService, authService) {
  	
  	$scope.close = close;
		$scope.comment = {};
  	$scope.postIt = postIt;
    $scope.boardAtivo = boardAtivo;

    $scope.usuario = authService.getUsuario();

  	$scope.submitForm = function(comment) {
      comment.id_card = postIt.id;
			websocketService.sendNewComment(comment, postIt.id, updateComment);
			delete $scope.comment.text;
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
