angular
 .module('stoneBoard')
 .controller('controllerModalNote', function($scope, close,postIt) {
  console.log(postIt);
$scope.close = close;
   });
