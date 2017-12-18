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
            $scope.myBoards.forEach(
              x =>
              { x.deadline = new Date(x.deadline.slice(0,3).join())
              });
        });
    }

    listConnectBoards();
    function listConnectBoards(){
      let promise = personService.listConnectBoards();
        promise.then(function (response) {
            $scope.connectBoards = response.data;
            $scope.connectBoards.forEach(
              x =>
              { x.deadline = new Date(x.deadline.slice(0,3).join())
              });
        });
    }

  });

})();
