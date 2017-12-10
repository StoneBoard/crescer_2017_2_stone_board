(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerDashboard', function ($scope, $filter,personService) {

  	listMyBoards();

    function listMyBoards(){
    	let promise = personService.listMyBoards();
        promise.then(function (response) {
            $scope.myBoards = response.data;
            $scope.myBoards.deadline = $scope.myBoards.map(x => x.deadline = new Date(x.deadline));
            console.log($scope.myBoards);
        });
    }

  });

})();
