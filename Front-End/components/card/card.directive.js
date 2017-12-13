(function(){
  'use strict';

  angular.module('stoneBoard')
		.directive('cardDashBoard', function () {

			return {

      restrict: 'E',

      scope: {
        c : '=info'
      },

      templateUrl: 'components/card/card.html'
    }
	});
})();
