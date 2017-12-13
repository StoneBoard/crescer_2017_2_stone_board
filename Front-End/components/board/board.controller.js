(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoard', function ($scope,$routeParams, $window, boardService,postitService) {

  	$scope.colorPallet = ['orange', 'blue', 'pink', 'green'];
  	$scope.rowStyle = {};
    $scope.sendMessage = sendMessage;

  	let idealWidth = 550; //px
  	let numSession;
  	let newWidth;

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
			let promise = postitService.saveCard(card).then();
			sendMessage();
		}

		var socket = null;
    var stompClient = null;
    var interval = null;

    function connect() {
      socket = new SockJS('http://localhost:9090/api/websocket');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) { });

      socket.onopen = function () {
        stompClient.subscribe('/stoneboard/sendBoard', function (board) {
          console.log('na resposta')
          console.log(board)
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
      console.log('sendMessage HERE')
			stompClient.send("/app/board/" + $routeParams.idBoard, {});
		}

    function update(board) {
      $scope.$apply(function () { 
        $scope.board = angular.fromJson(board.body).content;
        $scope.sessions = $scope.board.sessions;
        numSession = $scope.sessions.length;
        resizeRow();
      });
    }

    connect();

  });

})();
