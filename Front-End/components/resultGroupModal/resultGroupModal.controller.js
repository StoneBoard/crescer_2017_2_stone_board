angular
.module('stoneBoard')
.controller('controllerModalNote', function($scope,myResultGroup,resultGroupService, close,idPostIt) {
  $scope.close = close;
  $scope.myResultGroup = myResultGroup;

    $scope.myResultGroup = function(idPostIt, idResultGroup) {
      let resultGroupService.addCards(idPostIt, idResultGroup);

    }
  }

  }


});
