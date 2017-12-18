angular
.module('stoneBoard')
.controller('controllerModalNote', function($scope, $route, myResultGroup, resultGroupService, close, idPostIt, $routeParams, toastr) {
  $scope.close = close;
  $scope.myResultGroup = myResultGroup;

  $scope.myResultGroup = function(idResultGroup) {
  	
    resultGroupService.addCards(idResultGroup, idPostIt).then(
    	function(){
	      toastr.success('Grupo de resultado inserido com sucesso!');
        $route.reload();
      }, 
      function(response){
        if (typeof(response.data.errors) === 'undefined')
          toastr.error(response.data.message)
	      else{ 
	        response.data.errors.forEach(error =>{
	        toastr.error(error.defaultMessage)
	      });
      }
	  });
    close();
  }

  displayResultsGroups();
  function displayResultsGroups(){
    resultGroupService.findByBoard($routeParams.idBoard).then(function(response){
      $scope.resultGroups = response.data;        
    });
  }
});
