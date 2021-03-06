// JS code for websockets (Websocket Client Endpoint)
$("#connectPush").click(function(){

    const webSocket = new WebSocket("ws://localhost:8080/wRESTling__bots/pushnotifications");

    // telling server that we are online
    webSocket.addEventListener('open', function (event) {
        webSocket.send('mainPage.js'.userID);
    });

    // getting feedback
    webSocket.addEventListener('message', function(event) {
        console.log("Message: ", event.data);
    });



    // handle socket opening e.g. client connecting to push-socket
    webSocket.onopen = function(message) {
        console.log(message)
    };


    // handle incoming message
    webSocket.onmessage = function(event) {
        let incomingMessage = event.data;
        showMessage(incomingMessage);
    };
});

// show message in div #notifications
function showMessage(message) {
    document.getElementById('notifications').prepend(message);
}


