(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoardMembers', function($scope, $routeParams){

  	$scope.idBoard = $routeParams.idBoard

  });

})();