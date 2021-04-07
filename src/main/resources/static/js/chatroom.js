$(function(){
    let urlObject = new URL(window.location.href);
    const roomID = urlObject.searchParams.get('chatroomID');
    console.log(roomID, "chatroomID");

    //TODO: Lage et API-kall som registrerer deltakere i et bestemt chatrom.
    let user = getUser();
    $.post("/addParticipant", roomID, user);
    $.get("/getAllParticipants", roomID, function(chatroomParticipants){
        console.log(chatroomParticipants, "chatroom-Participants");
        let output = +
            "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                    "<th>Username</th>" +
                "</tr>";

        for (const participant of chatroomParticipants){
            console.log(participant, "participant");
            output +=
                "<tr>" +
                    "<td>" + participant + "</td>" +
                "</tr>";
        }
        let allUsersElement = document.getElementById("#allUsers");
        console.log(output);
        allUsersElement.html(output);
    });

   /* $.get("/printMsgs", function(allMsgs) {
        formatMsgTable(allMsgs);
    });*/

    // method to format
    function formatMsgTable(allmsg) {
        let out =
            "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                    "<th>Sent</th>" +
                    "<th>User</th>" +
                    "<th>Messsage</th>" +
                "</tr>";
        for (const msg of allmsg) {
            out +=
                "<tr>" +
                    "<td>" + msg.timestamp + "</td>" +
                    "<td>" + msg.user + "</td>" +
                    "<td>" + msg.message + "</td>" +
                "</tr>";
        }
        $("#allmsg").html(out);
    }

/*// function to print out a table of online users in the chatroom
    $.get("/printUsers", function(users) {
        formatTable(users);
    });*/
});




