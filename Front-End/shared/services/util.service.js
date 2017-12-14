 (function(){
   'use strict'
  angular.module('stoneBoard').factory('utilsService', function($window){

  	function resizeBoardWidth(rowSessionElement, idealWidth, numSession) {

  		let newWidth;

  		rowSessionElement.css('width', '100%');

	  	if (idealWidth > rowSessionElement[0].clientWidth) {
	  		newWidth = $window.innerWidth * numSession + 'px';
	  	}
	  	else if ( (idealWidth * numSession) >= rowSessionElement[0].clientWidth ) {
	  		newWidth =	(idealWidth * numSession) + 'px';
	  	}
	  	else {
	  		newWidth = '100%';
	  	}

	  	rowSessionElement.css('width', newWidth);
  	}


  	return {
  		resizeBoardWidth : resizeBoardWidth
  	}

  });
})();