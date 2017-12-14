(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerResultGroupDetail', function ($scope, resultGroupService, $routeParams, toastr) {

	  $scope.submitForm = function(resultGroup){

	  	resultGroup.id_board = $routeParams.idBoard;

	  		console.log(resultGroup);
	      let promise = resultGroupService.saveResultGroup(resultGroup).then(
	          function(){
	            toastr.success('Grupo de resultado cadastrado com sucesso!');
	          }, function(response){
	            if (typeof(response.data.errors) === 'undefined')
	              toastr.error(response.data.message)
	            else 
	            response.data.errors.forEach(error =>{
	              toastr.error(error.defaultMessage)
	            });
	          });
	      console.log("@@@");
	      console.log(promise);
	    }

  });
})();