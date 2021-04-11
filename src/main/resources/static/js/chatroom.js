$(window).on('load', function(){
    let urlObject = new URL(window.location.href);
    let roomID = urlObject.searchParams.get('chatroomID');
    getMessages();

    let user = getUser();
    let data = {
        roomID : roomID,
        userID : user.userID
    };

    let userLoggedIn = document.getElementById("userLoggedIn");
    userLoggedIn.innerHTML = user.username;

    function getMessages() {
        $.get("/getParticipants", {roomID:roomID}, function(chatroomParticipants){
            $.get("/getMessages", {roomID: roomID}, function (chatroomMessages) {
                let username = null;

                let output =
                    "<table class='table table-striped table-bordered'>" +
                        "<tr>" +
                            "<th>Username</th>" +
                            "<th>Timestamp</th>" +
                            "<th>Message</th>" +
                        "</tr>";

                for (const msg of chatroomMessages.reverse()) {
                    for (const participant of chatroomParticipants){
                        if (msg.userID === participant.userID){
                            username = participant.username;
                        }
                    }
                    output +=
                        "<tr>" +
                            "<td>" + username + "</td>" +
                            "<td>" + msg.timestamp + "</td>" +
                            "<td>" + msg.message + "</td>" +
                        "</tr>";
                }
                output += "</table>";
                if(username!=null) {
                    $("#allMsgs").empty().html(output);
                }
            });
        });

    }

    $.post("/addParticipant", data)
        .done(function () { // JavaScript promise: funksjonen kalles når post-kallet er ferdig.
        getParticipants();  // (sørger for at getParticipants() blir kallt etter vi har lagt til bruker)
    });

    function getParticipants(){
        $.get("/getParticipants", {roomID:roomID}, function(chatroomParticipants){
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
        };

        $.post("/addMessage", msgData)
            .done(function () { // JavaScript promise: funksjonen kalles når post-kallet er ferdig.
                getMessages();  // (sørger for at getMessages() blir kallt etter vi har lagt til bruker)
                $("#message").html('');
            });

        $.get("/addBotMessage")
    });


    //When a user logs out, the cookies containing their userID and username will be removed
    $("#logOut").click(function(){
        setCookie("username", null, 0);
        setCookie("userID", null, 0);
        $(location).attr('href', 'index.html');
    });

    //When a user logs out, the cookies containing their userID and username will be removed
    $("#goBack").click(function(){
        $(location).attr('href', 'mainPage.html');
    });

    $("#leaveRoom").click(function(){
        let data = {
            roomID : roomID,
            userID : user.userID
        };

        $.post("/deleteUserFromRoom", data)
            .done(function() {
                $(location).attr('href', 'mainpage.html');
            });
    });
});




