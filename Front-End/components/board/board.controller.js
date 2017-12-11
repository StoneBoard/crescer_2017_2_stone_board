(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoard', function ($scope,$routeParams, $window, boardService,$stomp,$log) {

  	//console.log($window);
  	$scope.rowStyle = {};

  	let idealWidth = 550; //px
  	let numSession = 2;
  	let newWidth;

  	resizeRow();

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

    displayBoard();
    function displayBoard(){
      debugger;
      let promisse =  boardService.findById($routeParams.idBoard);
      promisse.then(function (response) {
        debugger;
            $scope.board = response.data;
            $scope.sessions = $scope.board.sessions;

        });
    }


/////////////////////////////////////////
    $stomp.setDebug(function (args) {
     $log.debug(args)
   })

   $stomp.connect('http://localhost:9090/api/app', connectHeaders)

     // frame = CONNECTED headers
     .then(function (frame) {
       var subscription = $stomp.subscribe('/loadBoardById' + $routeParams.idBoard, function (payload, headers, res) {
         $scope.payload = payload
       }, {
         'headers': ''
       })

       // Unsubscribe
       subscription.unsubscribe()

       // Send message
       $stomp.send('/loadBoardById' + $routeParams.idBoard, {
         message: 'body'
       }, {
         priority: 9,
         custom: 42 // Custom Headers
       })

       // Disconnect
       $stomp.disconnect().then(function () {
         $log.info('disconnected')
       })
     })

  });

})();
