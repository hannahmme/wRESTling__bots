
var socket = new SockJS('/pushnotification')


function initialize(){

    let socket = new WebSocket("ws://localhost:8080/mainPage.html/pushnotification");
    socket.onmessage = onSocketMessage;

}

function onSocketMessage(event) {
    if (event.data) {
        document.getElementById("notifications").
        innerHTML = ("Received message: " + JSON.stringify(event.data));
    }
}