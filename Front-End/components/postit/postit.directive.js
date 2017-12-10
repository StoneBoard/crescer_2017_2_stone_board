(function(){
  'use strict';

  angular.module('stoneBoard')
		.directive('postIt', function () {

			return {

      restrict: 'E',

      scope: { 
        c : '=info'
      },

      templateUrl: 'components/postit/postit.html',

      controller: function ($scope) {

      }

    }

	});

}());
