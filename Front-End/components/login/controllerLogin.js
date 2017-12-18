(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerLogin', function ($scope, authService, $location, toastr, personService) {

    $scope.loginClass = 'appear'
    $scope.registerClass = 'disappear'

    $scope.login = function (usuario) {

      authService.login(usuario)
      .then(
        function (response) {},

        function (response) {
          toastr.error('Usuario ou senha inválidos!');
        });
      };

      $scope.savePerson = function(person){
        let promise = personService.savePerson(person).then(
            function(){
              toastr.success('Usuário cadastrado com sucesso!');
            }, function(response){
              if (typeof(response.data.errors) === 'undefined')
                toastr.error(response.data.message)
              else 
              response.data.errors.forEach(error =>{
                toastr.error(error.defaultMessage)
            });
        })
      }

    });
  })();
