(function(){
  'use strict'

  angular
  .module('stoneBoard')
  .controller('controllerDashboard', function ($scope, $filter,personService) {

  	listMyBoards();
    function listMyBoards(){
    	let promise = personService.listMyBoards();
        promise.then(function (response) {
            console.log(response);
            $scope.myBoards = response.data;
            $scope.myBoards.deadline = $scope.myBoards.map(x => x.deadline = new Date(x.deadline));
            console.log($scope.myBoards);
        });
    }

    listConnectBoards();
    function listConnectBoards(){
      let promise = personService.listConnectBoards();
        promise.then(function (response) {
            $scope.connectBoards = response.data;
            $scope.connectBoards.deadline = $scope.connectBoards.map(x => x.deadline = new Date(x.deadline));
            console.log($scope.connectBoards);
        });
    }


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

})
})();
