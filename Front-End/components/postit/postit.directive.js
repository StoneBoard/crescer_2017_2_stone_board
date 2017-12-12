(function(){
  'use strict';

  angular.module('stoneBoard')
		.directive('postIt', function () {

			return {

      restrict: 'E',

      scope: { 
        sendMessage: '&send',
        p : '=info',
        color : '=color'
      },

      templateUrl: 'components/postit/postit.html',

      controller: function ($scope, authService,postitService, voteService) {

        $scope.votoPositivo = p.vote.positive ? 'icon-selected' : '';
        $scope.votoNegativo = !p.vote.positive ? 'icon-selected' : '';

        $scope.vPositivo = p.vote.positive.filter(true);
        $scope.vNegativo = p.vote.positive.filter(false);

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
          let promise =  postitService.deleteCard($scope.p.id).then();
          sendMessage();          
        }

        $scope.update = function() {
          let promise =  postitService.editCard($scope.p).then();
          $scope.changeMode(false);
          sendMessage();
        }
        $scope.vote = function() {
          p.vote.id_card = p.id;
          let promise =  voteService.saveVote(p.vote).then();
          sendMessage();
        }

        $scope.undo = function() {
          console.log('undo');
          $scope.p.text = JSON.parse(JSON.stringify(oldText));
          $scope.changeMode(false);
          sendMessage();
        }

      }

    }

	});

}());
