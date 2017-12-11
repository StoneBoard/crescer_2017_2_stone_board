(function(){
  'use strict';

  angular.module('stoneBoard')
		.directive('postIt', function () {

			return {

      restrict: 'E',

      scope: { 
        p : '=info',
        color : '=color'
      },

      templateUrl: 'components/postit/postit.html',

      controller: function ($scope, authService) {

        let usuario = authService.getUsuario();
        $scope.isWriter = usuario.id === $scope.p.id_writer;
        $scope.paddingCard = $scope.isWriter ? {} : {'padding-top': '20px'};
        
        $scope.editMode = false;
        let oldText = "";

        $scope.changeMode = function(editing) {
          if (editing) oldText = JSON.parse(JSON.stringify($scope.p.text));
          $scope.editMode = editing;
        }

        $scope.delete = function(){
          console.log('delete');
        }

        $scope.update = function() {
          console.log('update');
          $scope.changeMode(false);
        }

        $scope.undo = function() {
          console.log('undo');
          $scope.p.text = JSON.parse(JSON.stringify(oldText));
          $scope.changeMode(false);
        }

      }

    }

	});

}());
