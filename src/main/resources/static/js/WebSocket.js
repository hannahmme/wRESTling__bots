// JS code for websockets




function openConnection(){
    const webSocket = new WebSocket("ws://localhost:8080");

    // telling server that we are online
    webSocket.addEventListener('open', function (event) {
        webSocket.send("hello server");
    });

    // getting feedback
    webSocket.addEventListener('message', function(event) {
        console.log("Message: ", event.data);
    });



    console.log("Hei");

    webSocket.onopen = function(message) {
        processOpen(message)
    };


    webSocket.onmessage = onSocketMessage;

}

let notifications;

function processOpen(message){
    notifications.add(message);
}


function onSocketMessage(event) {
    if (event.data) {
        console.log("HEIII");
        document.getElementById("notifications").
        innerHTML = ("Received message: " + JSON.stringify(event.data));
    }
}