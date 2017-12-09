(function(){
  'use strict';

  angular.module('stoneBoard')
		.directive('cardDashBoard', function () {

			return {

      restrict: 'E',

      scope: {
        c : '=cardAttr'
      },

      templateUrl: 'components/card/card.html',

      controller: function ($scope) {




      }

    }

	});

})();