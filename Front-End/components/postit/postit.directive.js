(function(){
  'use strict';

  angular.module('stoneBoard')
  .directive('postIt', function () {

    return {

      restrict: 'E',

      scope: {
        sendMessage : '=send',
        postIt : '=info',
        boardStatus : '=boardStatus',
        cardMessages : '=cardMessages'
      },

      templateUrl: 'components/postit/postit.html',

      controller: function ($scope, authService, postitService, toastr,ModalService, voteService, utils) {

        console.log($scope.postIt)
        $scope.color = utils.colorPallet[$scope.postIt.color];

        let usuario = authService.getUsuario();

        $scope.vPositivo = $scope.postIt.votes.filter(v => v.positive);
        $scope.vNegativo = $scope.postIt.votes.filter(v => !v.positive);

        $scope.votoPositivo = $scope.vPositivo.filter(v => v.id_person == usuario.id).length > 0 ? 'icon-selected' : '';
        $scope.votoNegativo = $scope.vNegativo.filter(v => v.id_person == usuario.id).length > 0 ? 'icon-selected' : '';

        $scope.isWriter = usuario.id === $scope.postIt.id_writer;
        $scope.postItaddingCard = $scope.isWriter ? {} : {'padding-top': '20px'};

        $scope.editMode = false;
        let oldText = "";

        $scope.changeMode = function(editing) {
          if (editing) oldText = JSON.parse(JSON.stringify($scope.postIt.text));
          $scope.editMode = editing;
        }

        $scope.deleteCard = function(){
          $scope.cardMessages.deleteCard($scope.postIt);
        }

        $scope.update = function() {
          postitService.editCard($scope.postIt).then();
          $scope.editMode = false;
        }
        $scope.vote = function(_positive) {
          let newVote = {positive: _positive, id_person: usuario.id,  id_card: $scope.postIt.id,}
          $scope.cardMessages.vote(newVote);
        }

        $scope.undo = function() {
          $scope.postIt.text = JSON.parse(JSON.stringify(oldText));
          $scope.changeMode(false);
        }
        ///////////////// Modal///////////////
        $scope.showCustom = showCustom;

        function showCustom() {
          ModalService.showModal({
            templateUrl: "components/resultGroupModal/resultGroupModal.html",
            controller: "controllerModalNote",
            bodyClass: "custom-modal-open",
            inputs: {
              postIt : $scope.postIt.id
            }
          });
        };

        $scope.keyPress = function(value) {
          if (value.keyCode == 42) {
            ModalService.closeModals(null, 500);
          }
        };
      }

    }

  });

}());
