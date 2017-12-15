angular
.module('stoneBoard')
.controller('controllerCommentsModal', function($scope, close) {
  $scope.close = close;

    $scope.myResultGroup = function(idPostIt, idResultGroup) {
      resultGroupService.addCards(idPostIt, idResultGroup);

    }
});
