(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoard', function ($scope,$routeParams, $window,authService,$location, boardService,postitService) {

  	$scope.colorPallet = ['orange', 'blue', 'pink', 'green'];
  	$scope.rowStyle = {};
    $scope.sendMessage = sendMessage;
    $scope.usuario = authService.getUsuario();
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

    $scope.submitCardForm = function(card, session_id){
      card.id_session = session_id;
			sendMessageCard(card);
		}

    validateAuthorization();
      function validateAuthorization(){
      let promise =  boardService.findById($routeParams.idBoard).then(
        function (response) {},
        function (response) {
          socket = null;
          $location.path('/dashboard');
        });
    }

    function connect() {
      socket = new SockJS('http://localhost:9090/api/websocket');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) { });
      stompClient.debug = null;
      socket.onopen = function () {
        stompClient.subscribe('/stoneboard/sendBoard', function (board) {
 				update(board);
        });
       sendMessage();
      };

      clearInterval(interval);

      socket.onclose = function () {
        socket = null;
        interval = setInterval(function () {
          connect();
        }, 2000);
      };

		}

		function sendMessage(){
			stompClient.send("/app/board/" + $routeParams.idBoard, {});
		}

    function sendMessageCard(card){
      stompClient.send("/app/card/new/" + $routeParams.idBoard + "/1", {}, JSON.stringify(card));
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

 
  });

})();
