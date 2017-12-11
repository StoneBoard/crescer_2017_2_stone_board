(function () {
  'use strict'

  angular
    .module('stoneBoard')
    .controller('controllerDashboard', dashboardController);

  function dashboardController($scope) {

    var socket = null;
    var stompClient = null;
    var interval = null;

    function connect() {
      socket = new SockJS('http://localhost:9090/api/websocket');
      stompClient = Stomp.over(socket);

      stompClient.connect({}, function (frame) { });

      socket.onopen = function () {
        stompClient.subscribe('/stoneboard/sendPerson', function (person) {
          update(person);
        });
        stompClient.send("/app/person", {});
      };

      clearInterval(interval);

      socket.onclose = function () {
        socket = null;
        interval = setInterval(function () {
          connect();
        }, 2000);
      };

    }

    function update(person) {
      $scope.$apply(function () {
        $scope.person = angular.fromJson(person.body).content;
        console.log($scope.person);
      });
    }

    connect();

  };

})();
