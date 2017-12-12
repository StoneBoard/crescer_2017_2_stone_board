(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoard', function ($scope,$routeParams, $window, boardService,postitService) {

  	$scope.colorPallet = ['orange', 'blue', 'pink', 'green'];
  	$scope.rowStyle = {};

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

    window.setInterval(displayBoard(), 3000);

    function displayBoard(){

      let promisse =  boardService.findById($routeParams.idBoard);
      promisse.then(function (response) {

            $scope.board = response.data;
            $scope.sessions = $scope.board.sessions;
            numSession = $scope.sessions.length;
            resizeRow();

        });
    }

    $scope.submitCardForm = function(card, session_id){
      card.id_session = session_id;
      let promise = postitService.saveCard(card).then();
    }

  });

})();
