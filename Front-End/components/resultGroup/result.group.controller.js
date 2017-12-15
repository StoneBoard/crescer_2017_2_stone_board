(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerResultGroup', function ($scope, $window, utilsService, postitService, resultGroupService, $routeParams) {

    $scope.idBoard = $routeParams.idBoard

  	let idealWidth = 550; //px
  	let numSession;
  	let rowSession = angular.element(document.getElementById('row-sessions'));

    // verifica se houve alguma mudança na largura da tela e recalcula largura do board
    angular.element($window).bind('resize', resizeRow);

    resizeRow();

    function resizeRow(){
      utilsService.resizeBoardWidth(rowSession, idealWidth, numSession);
    }

    (function displayResultsGroups(){
      resultGroupService.findByBoard($routeParams.idBoard)
      .then(
        function(response){
          $scope.resultGroups = response.data;
          numSession = $scope.resultGroups.length + 1;
          resizeRow();
          console.log($scope.resultGroups);
        }
      );
    })();

    (function displayCardsOutsideResultGroup (){
      postitService.getCardsOutsideResultGroup($routeParams.idBoard)
        .then(response => $scope.cardsOutside = response.data);
    })();

  });

})();