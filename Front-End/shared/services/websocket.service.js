(function () {
   'use strict'
   angular.module('stoneBoard').factory('websocketService', function (authService) {

     let socket = null;
     let stompClient = null;
     let interval = null;
     let idBoard

     function cancel() {
       socket = null
     }

     function connect(update, _idBoard) {
       var auth = window.atob(authService.webSocketAuth());
       console.log(auth);
       socket = new WebSocket(`ws://${auth}@localhost:9090/api/websocket`);

       stompClient = Stomp.over(socket);
       stompClient.connect({}, function (frame) {});
       stompClient.debug = null;
       socket.onopen = function () {
         stompClient.subscribe('/stoneboard/sendBoard', function (board) {
           update(board);
         });
         idBoard = _idBoard;
         startMessage();
       };

       clearInterval(interval);

       socket.onclose = function () {
         socket = null;
         interval = setInterval(function () {
           connect();
         }, 2000);
       };

     }

     /* envio de mensagens para a controller do websocket */

     function sendMessage(url, obj) {
       stompClient.send('/app' + url, {}, JSON.stringify(obj));
     }

     function startMessage() {
       sendMessage("/board/" + idBoard);
     }

     function sendNewCard(card) {
       sendMessage('/card/new/' + idBoard, card);
     }

     function updateCard(card) {
       debugger;
       sendMessage('/card/edit/' + idBoard, card);
     }

     function deleteCard(card) {
       sendMessage('/card/delete/' + idBoard, card);
     }

     function vote(vote) {
       sendMessage('/vote/' + idBoard, vote);
     }

     return {
       connect: connect,
       startMessage: startMessage,
       sendNewCard: sendNewCard,
       updateCard: updateCard,
       deleteCard: deleteCard,
       vote: vote
     }

   });

   })();
