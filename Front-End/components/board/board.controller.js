(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoard', function ($scope,$routeParams, $window,authService,$location, boardService,postitService, personService) {

  	$scope.colorPallet = ['orange', 'blue', 'pink', 'green'];
  	$scope.rowStyle = {};
    $scope.usuario = authService.getUsuario();

   personService.isAdmin($routeParams.idBoard).then(function(response){
        $scope.isAdmin = response.data;
        console.log($scope.isAdmin);
    });

  	let idealWidth = 550; //px
  	let numSession;
    let newWidth;
    let resized = false;
    let socket = null;
    let stompClient = null;
    let interval = null;


  	// verifica se houve alguma mudança na largura da tela e recalcula largura do board
		angular.element($window).bind('resize', resizeRow);

  	function resizeRow() {

  		let rowSession = angular.element(document.getElementById('row-sessions'));
  		rowSession.css('width', '100%');

	  	if (idealWidth > rowSession[0].clientWidth) {
	  		newWidth = $window.innerWidth * numSession + 'px';
	  	}
	  	else if ( (idealWidth * numSession) >= rowSession[0].clientWidth ) {
	  		newWidth =	(idealWidth * numSession) + 'px';
	  	}
	  	else {
	  		newWidth = '100%';
	  	}

	  	rowSession.css('width', newWidth);
  	}

    $scope.submitCardForm = function(card, session){
      card.id_session = session.id;
      card.color = session.color;
			sendNewCard(card);
		}

    function boardStatus(date) {
      $scope.boardStatus = date > new Date();
    }

    validateAuthorization();
      function validateAuthorization(){
      let promise =  boardService.findById($routeParams.idBoard).then(
        function (response) {
          $scope.deadline = response.data.deadline;
          $scope.deadline = new Date($scope.deadline.slice(0,3).join());
          boardStatus($scope.deadline);
        },
        function (response) {
          socket = null;
          $location.path('/dashboard');
        });
    }

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

    connect();

		/* envio de mensagens para a controller do websocket */

    function sendMessage(url, obj){
      stompClient.send('/app' + url, {}, JSON.stringify(obj));
    }

    function startMessage(){
      sendMessage("/board/" + $routeParams.idBoard);
    }

    function sendNewCard(card){
      sendMessage('/card/new/' + $routeParams.idBoard + '/' + $scope.usuario.id, card);
    }

    function updateCard(card){
      sendMessage('/card/edit/' + $routeParams.idBoard, card);
    }

    function deleteCard(card){
      sendMessage('/card/delete/' + $routeParams.idBoard, card);
    }

    function vote(vote) {
      sendMessage('/vote/' + $routeParams.idBoard, vote);
    }

    $scope.cardMessages = {
      updateCard : updateCard,
      deleteCard : deleteCard,
      vote : vote
    }

  });

})();
