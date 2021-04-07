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
});




