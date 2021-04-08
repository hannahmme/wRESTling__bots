// TODO: skrive ut alle eksisterende meldinger når chatrommet åpner

$(window).on('load', function(){
    let urlObject = new URL(window.location.href);
    let roomID = urlObject.searchParams.get('chatroomID');

    let user = getUser();
    let data = {
        roomID : roomID,
        userID : user.userID
    };

    $.post("/addParticipant", data)
        .done(function () { // JavaScript promise: funksjonen kalles når post-kallet er ferdig.
        getParticipants();  // (sørger for at getParticipants() blir kallt etter vi har lagt til bruker)
    });

    function getParticipants(){
        $.get("/getAllParticipants", {roomID:roomID}, function(chatroomParticipants){
            let output =
                "<table class='table table-striped table-bordered'>" +
                    "<tr>" +
                        "<th>Username</th>" +
                    "</tr>";

            for (const participant of chatroomParticipants){
                output +=
                    "<tr>" +
                        "<td>" + participant.username + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#allUsers").empty().html(output);
        });
    }

    $("#createMessage").click(function() {
        let msg = $("#message").val();
        let msgData = {
            roomID : roomID,
            userID : user.userID,
            msg : msg
        }

        $.post("/addMessage", msgData)
            .done(function () { // JavaScript promise: funksjonen kalles når post-kallet er ferdig.
                getMessages();  // (sørger for at getMessages() blir kallt etter vi har lagt til bruker)
            });

        function getMessages() {
            $.get("/getMessages", {roomID: roomID}, function (chatroomMessages) {
                let output =
                    "<table class='table table-striped table-bordered'>" +
                    "<tr>" +
                    "<th>Username</th>" + "<th>Timestamp</th>" + "<th>Message</th>" +
                    "</tr>";

                // TODO: sørge for at username blir printet ut i stedet for userID
                for (const msg of chatroomMessages.reverse()) {
                    output +=
                        "<tr>" +
                        "<td>" + msg.userID + "</td>" + "<td>" + msg.timestamp + "</td>" + "<td>" + msg.message + "</td>"
                    "</tr>";
                }
                output += "</table>";
                $("#allMsgs").empty().html(output);
            });
        }
    });
});




