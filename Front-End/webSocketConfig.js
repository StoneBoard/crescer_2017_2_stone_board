(function(){
  'use strict';

angular.module('stoneBoard')
        .config(function(ngstompProvider){
          //let credentials = $localStorage.headerAuth;

            ngstompProvider
                .url('http://localhost:9090/api/websocket')
                .credential("Basic dGVzdGVAdGVzdGU6VEVTVEU=")
                .class(SockJS);
        });

        })();
