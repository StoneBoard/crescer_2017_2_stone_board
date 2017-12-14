(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerResultGroup', function ($scope, $window, utilsService, resultGroupService, $routeParams) {

  	let idealWidth = 550; //px
  	let numSession = 2;
    let newWidth;
    let resized = false;
  	let rowSession = angular.element(document.getElementById('row-sessions'));


    // verifica se houve alguma mudança na largura da tela e recalcula largura do board
    angular.element($window).bind('resize', resizeRow);

    resizeRow();

    function resizeRow(){
      utilsService.resizeBoardWidth(rowSession, idealWidth, numSession);
    }


    displayResultsGroups();
    function displayResultsGroups(){
       resultGroupService.findBoard($routeParams.idBoard).then(function(response){
            $scope.resultGroups = response.data;
            console.log($scope.resultGroups);
       });
      console.log($scope.resultsGroups);
    }

  });

})();