$(function() {
    aliceBot();
});

let urlObject = new URL(window.location.href);
let roomID = urlObject.searchParams.get('chatroomID');

//registers a new bot as user on server
function registerBot(username){
    return $.post("/registerUser", {username: username});
}


//adding new bot as participant in chatroom
function addBotAsParticipant(roomId, botID){
    $.post("/addParticipant", roomID, botID);
}

//generates a random message from list and sends answer to chatroom
function sendRandomMessage(roomId, userID, listOfAnswers){
    let randomAnswer = [Math.floor(Math.random() * listOfAnswers.length)];
    $.post("/addMessage", roomID, userID, randomAnswer);
}

//get all messages from chatroom
function getMessagesFromChatroom(roomID){
    return $.get("/getMessages", roomID);
}

function aliceBot() {
    const listOfAnswers = ["Hey, mip mip", "Ayooo, whats up?", "Hai there friend. How are you?"];

    return registerBot("Alice")
        .then(function(newUser){
            return addBotAsParticipant(newUser);})
        .then(function(newUser){
        const listOfMessages = getMessagesFromChatroom(roomID);
        if (listOfMessages !== 0) {
            sendRandomMessage(roomID, result.userID, listOfAnswers);
        }
    })
    //const listOfMessages = getMessagesFromChatroom(roomID);
    /*if (listOfMessages !== 0) {
        sendRandomMessage(roomID, alice.userID, listOfAnswers);
    }*/
}
