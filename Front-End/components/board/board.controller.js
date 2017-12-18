(function () {
  'use strict'

  angular
    .module('stoneBoard')
    .controller('controllerBoard', function ($scope, $routeParams, $window, $http, utilsService, resultGroupService, authService, $location, boardService, postitService, personService, utils, $interval, websocketService) {

      $scope.colorPallet = utils.colorPallet;
      $scope.rowStyle = {};
      $scope.usuario = authService.getUsuario();
      $scope.card = {};

      personService.isAdmin($routeParams.idBoard).then(function (response) {
        $scope.isAdmin = response.data;
      });

      let groupResults = [];

      let idealWidth = 550; //px
      let numSession;
      let newWidth;
      let resized = false;
      let rowSession = angular.element(document.getElementById('row-sessions'));

      // verifica se houve alguma mudança na largura da tela e recalcula largura do board
      angular.element($window).bind('resize', resizeRow);

      function resizeRow() {
        utilsService.resizeBoardWidth(rowSession, idealWidth, numSession);
      }

      $scope.findIdResultGroup = function(idCard) {
        return groupResults.some(group => (group.cards.some(card => card.id === idCard)));
      }

      $scope.submitCardForm = function (card, session) {
        card.id_session = session.id;
        card.color = session.color;
        websocketService.sendNewCard(card);
        delete $scope.card.text;
      }

      function boardStatus(date) {
        $scope.boardStatus = date > new Date();
      }

      validateAuthorization();
      function validateAuthorization() {
        let promise = boardService.findById($routeParams.idBoard).then(
          function (response) {
            $scope.deadline = response.data.deadline;
            $scope.deadline = new Date($scope.deadline.slice(0, 3).join());
            boardStatus($scope.deadline);
          },
          function (response) {
            websocketService.cancel();
            $location.path('/dashboard');
          });
      }

      resultGroupService.findByBoard($routeParams.idBoard)
      .then(response => groupResults = response.data);

      /* websocketService */

      websocketService.connect(update, $routeParams.idBoard);

      function update(board) {
        let _board = angular.fromJson(board.body).content
        if (_board.id != $routeParams.idBoard) return;

        $scope.$apply(function () {
          if (!resized) {
            $scope.board = _board;
            $scope.sessions = $scope.board.sessions;
            numSession = $scope.sessions.length;
            resizeRow();
            resized = true;
          }else{
            $scope.board.sessions.forEach(function(session){
              session.cards = _board.sessions.find(s => s.id == session.id).cards;
            });
          }
        });
      }

    });

})();
