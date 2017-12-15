(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerResultGroup', function ($scope, $window, utilsService, personService, postitService, resultGroupService, $routeParams) {

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

    $scope.refresh = findResultGroups;
    findResultGroups();

    function findResultGroups(){
      resultGroupService.findByBoard($routeParams.idBoard)
      .then(
        function(response){
          $scope.resultGroups = response.data;
          numSession = $scope.resultGroups.length + 1;
          resizeRow();
        }
      );
    };

    (function displayCardsOutsideResultGroup (){
      postitService.getCardsOutsideResultGroup($routeParams.idBoard)
        .then(response => $scope.cardsOutside = response.data);
    })();

    personService.isAdmin($routeParams.idBoard).then(function(response){
        $scope.isAdmin = response.data;
    });

  });

})();