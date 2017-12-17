angular
	.module('stoneBoard')
	.controller('controllerCommentsModal', function($scope, close, postIt, websocketService) {
  	
  	$scope.close = close;

  	$scope.postIt = postIt;

  	$scope.submitForm = function(comment) {
      console.log('submitForm')
      console.log(comment)
      comment.id_card = postIt.id;
  		websocketService.sendNewComment(comment, postIt.id, updateComment);
  	}

  	function deleteComment(comment) {
  		websocketService.deleteComment(comment.id, postIt.id, updateComment);
  	}

  	function updateComment(_postIt){
      console.log('no update do comentario')
  		$scope.$apply(function() {
  			$scope.postIt = angular.fromJson(_postIt.body).content;
        console.log($scope.postIt)
  		});
  	}

});
