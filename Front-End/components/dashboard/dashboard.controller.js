(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerDashboard', function ($scope, $filter,personService) {

  	listMyBoards();
    function listMyBoards(){
    	let promise = personService.listMyBoards();
        promise.then(function (response) {
            console.log(response);
            $scope.myBoards = response.data;
            $scope.myBoards.deadline = $scope.myBoards.map(x => x.deadline = new Date(x.deadline));
            console.log($scope.myBoards);
        });
    }

    listConnectBoards();
    function listConnectBoards(){
      let promise = personService.listConnectBoards();
        promise.then(function (response) {
            $scope.connectBoards = response.data;
            $scope.connectBoards.deadline = $scope.connectBoards.map(x => x.deadline = new Date(x.deadline));
            console.log($scope.connectBoards);
        });
    }

  });

})();
