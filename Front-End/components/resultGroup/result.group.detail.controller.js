(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerResultGroupDetail', function ($scope, resultGroupService, $routeParams, toastr) {

  	  $scope.isAlterar = !!$routeParams.idResultGroup;
  	  $scope.idBoard = $routeParams.idBoard;


  	  setup();
  	  function setup() {
        if (!$scope.isAlterar) {
            return;
        }
        resultGroupService.findById($routeParams.idResultGroup).then(function (response) {
            $scope.resultGroup = response.data;
            console.log(response);
        });
      }

	  $scope.submitForm = function(resultGroup){

	  	resultGroup.id_board = $routeParams.idBoard;
	  	let promise;

	  	if($scope.isAlterar){
	  		 promise = resultGroupService.updateResultGroup(resultGroup).then(
	          function(){
	            toastr.success('Grupo de resultado alterado com sucesso!');
	          }, function(response){
	            if (typeof(response.data.errors) === 'undefined')
	              toastr.error(response.data.message)
	            else 
	            response.data.errors.forEach(error =>{
	              toastr.error(error.defaultMessage)
	            });
	      	})
	  	}else{
	       promise = resultGroupService.saveResultGroup(resultGroup).then(
	          function(){
	            toastr.success('Grupo de resultado cadastrado com sucesso!');
	          }, function(response){
	            if (typeof(response.data.errors) === 'undefined')
	              toastr.error(response.data.message)
	            else 
	            response.data.errors.forEach(error =>{
	              toastr.error(error.defaultMessage)
	            });
	      	})
	     }
	  }

	   $scope.deleteResultGroup = function(id_result){
	   	console.log(id_result);
	   		let promise = resultGroupService.deleteResultGroup(id_result, $routeParams.idBoard).then(
	   			function () {
            		toastr.success('Grupo de resultado deletado com sucesso!')
       		 	}, function(response){
       		 		console.log(response);
		            if (typeof(response.data.errors) === 'undefined')
		              toastr.error(response.data.message)
		            else 
		            response.data.errors.forEach(error =>{
		              toastr.error(error.defaultMessage)
	            });
	      	});
	   }

  });
})();