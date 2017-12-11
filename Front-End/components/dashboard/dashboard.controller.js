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

<<<<<<< HEAD
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
=======
   //  $stomp.setDebug(function (args) {
   //   $log.debug(args)
   // })

  //  $stomp
  //    .connect('/endpoint', connectHeaders)

  //    // frame = CONNECTED headers
  //    .then(function (frame) {
  //      var subscription = $stomp.subscribe('/dest', function (payload, headers, res,$stomp,$log) {
  //        $scope.payload = payload
  //      }, {
  //        'headers': ''
  //      })

  //      // Unsubscribe
  //      subscription.unsubscribe()

  //      // Send message
  //      $stomp.send('http://localhost:9090/api/person/loadMyBoardByIdPerson' {

  //        message: 'body'
  //      }, {
  //        priority: 9,
  //        custom: 42 // Custom Headers
  //      })

  //      // Disconnect
  //      $stomp.disconnect().then(function () {
  //        $log.info('disconnected')
  //      })
  //    })

  // });
>>>>>>> 6ed1a76c254478cfae7bafebcee5445cbefea7b2

})
})();
