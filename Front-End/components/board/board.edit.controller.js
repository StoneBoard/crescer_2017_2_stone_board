(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerEditBoard', function($scope, $routeParams){

		$scope.idBoard = $routeParams.idBoard

		
  });

})();