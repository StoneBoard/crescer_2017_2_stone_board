(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerDashboard', function ($scope, personService) {

  	listMyBoards();

    function listMyBoards(){
    	let promise = personService.listMyBoards();
        promise.then(function (response) {
            $scope.myBoards = response.data;
        });
    }
    
  });

})();