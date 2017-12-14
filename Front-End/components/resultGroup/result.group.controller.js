(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerResultGroup', function ($scope, $window) {

  	let idealWidth = 550; //px
  	let numSession = 2;
    let newWidth;
    let resized = false;

    $scope.cardTest = 
      { id: 1, 
        text: "sdfasfasfsa", 
        id_writer: 1, 
        creationDate: [2017, 12, 14, 14, 18, 31, 940000000], 
        color: 3, 
        votes: [
          { positive: false, 
            id_person: 1, 
            id_card: null
          }
        ]
      }

  	// verifica se houve alguma mudança na largura da tela e recalcula largura do board
		angular.element($window).bind('resize', resizeRow);

		resizeRow();

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

  });

})();