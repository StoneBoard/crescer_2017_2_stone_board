(function () {
  'use strict'

  angular
    .module('stoneBoard')
    .controller('controllerBoard', function ($scope, $routeParams, $window, $http, utilsService, resultGroupService, authService, $location, boardService, postitService, personService, utils, $interval) {

      $scope.colorPallet = utils.colorPallet;
      $scope.rowStyle = {};
      $scope.usuario = authService.getUsuario();

      personService.isAdmin($routeParams.idBoard).then(function (response) {
        $scope.isAdmin = response.data;
      });

      let groupResults = [];

      let socket = null;
      let stompClient = null;
      let interval = null;

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
        sendNewCard(card);
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
            socket = null;
            $location.path('/dashboard');
          });
      }

      resultGroupService.findByBoard($routeParams.idBoard)
      .then(response => groupResults = response.data);

      /* websocket */

      function connect() {
        socket = new SockJS('http://localhost:9090/api/websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) { });
        stompClient.debug = null;
        socket.onopen = function () {
          stompClient.subscribe('/stoneboard/sendBoard', function (board) {
            update(board);
          });
          startMessage();
        };

        clearInterval(interval);

        socket.onclose = function () {
          socket = null;
          interval = setInterval(function () {
            connect();
          }, 2000);
        };

      }

      $http.get('http://localhost:9090/api/websocket')
        .then(function (response) {
          connect();
        });      

      function update(board) {
        $scope.$apply(function () {
          $scope.board = angular.fromJson(board.body).content;
          $scope.sessions = $scope.board.sessions;
          if (!resized) {
            numSession = $scope.sessions.length;
            resizeRow();
            resized = true;
          }
        });
      }

      /* envio de mensagens para a controller do websocket */

      function sendMessage(url, obj) {
        stompClient.send('/app' + url, {}, JSON.stringify(obj));
      }

      function startMessage() {
        sendMessage("/board/" + $routeParams.idBoard);
      }

      function sendNewCard(card) {
        sendMessage('/card/new/' + $routeParams.idBoard + '/' + $scope.usuario.id, card);
      }

      function updateCard(card) {
        debugger;
        sendMessage('/card/edit/' + $routeParams.idBoard, card);
      }

      function deleteCard(card) {
        sendMessage('/card/delete/' + $routeParams.idBoard, card);
      }

      function vote(vote) {
        sendMessage('/vote/' + $routeParams.idBoard, vote);
      }

      $scope.cardMessages = {
        updateCard: updateCard,
        deleteCard: deleteCard,
        vote: vote
      }

    });

})();
