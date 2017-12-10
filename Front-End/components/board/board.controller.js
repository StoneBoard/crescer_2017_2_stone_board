(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerBoard', function ($scope, $window, boardService, $routeParams) {

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

    function displayBoard(){

      boardService.findById($routeParams.idBoard).then(function (response) {
            $scope.board = response.data;
        });
    }




  });

})();
