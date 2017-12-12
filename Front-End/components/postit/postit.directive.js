(function(){
  'use strict';

  angular.module('stoneBoard')
    .directive('postIt', function () {

      return {

      restrict: 'E',

      scope: { 
        sendMessage : '=send',
        p : '=info',
        color : '=color'
        
      },

      templateUrl: 'components/postit/postit.html',

      controller: function ($scope, authService,postitService, voteService) {
        let usuario = authService.getUsuario();

        $scope.vPositivo = $scope.p.votes.filter(v => v.positive);
        $scope.vNegativo = $scope.p.votes.filter(v => !v.positive);

        $scope.votoPositivo = $scope.vPositivo.filter(v => v.id_person == usuario.id).length > 0 ? 'icon-selected' : '';
        $scope.votoNegativo = $scope.vNegativo.filter(v => v.id_person == usuario.id).length > 0 ? 'icon-selected' : '';

        $scope.isWriter = usuario.id === $scope.p.id_writer;
        $scope.paddingCard = $scope.isWriter ? {} : {'padding-top': '20px'};
        
        $scope.editMode = false;
        let oldText = "";

        $scope.changeMode = function(editing) {
          if (editing) oldText = JSON.parse(JSON.stringify($scope.p.text));
          $scope.editMode = editing;
        }

        $scope.deleteCard = function(){
          let promise =  postitService.deleteCard($scope.p.id).then();
          $scope.sendMessage();
        }

        $scope.update = function() {
          
          let promise =  postitService.editCard($scope.p).then();
          $scope.changeMode(false);
          $scope.sendMessage();
        }
        $scope.vote = function(_positive) {
          let newVote = {positive: _positive, id_person: usuario.id,  id_card: $scope.p.id,}
          let promise =  voteService.saveVote(newVote).then();
          console.log('votando')
          console.log($scope.sendMessage)
          $scope.sendMessage();
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
