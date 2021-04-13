$(window).on('load', function(){
    let urlObject = new URL(window.location.href);
    let roomID = urlObject.searchParams.get('chatroomID');

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

                if(chatroomMessages === null || chatroomMessages.length === 0){
                    console.error("Meldinger er tomme he ihå");
                    return;
                }

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

    if(data.userID !== null || data.userID !== ''){
        $.post("/addParticipant", data)
            .done(function () { // JavaScript promise: funksjonen kalles når post-kallet er ferdig.
                getParticipants();  // (sørger for at getParticipants() blir kallt etter vi har lagt til bruker)
            });
    }


    function getParticipants(){
        $.get("/getParticipants", {roomID:roomID}, function(chatroomParticipants){
            let output =
                "<table class='table table-striped table-bordered'>" +
                    "<tr>" +
                        "<th>Username</th>" +
                    "</tr>";

            try{
                console.log("Chatroomdeltakere: ", chatroomParticipants);
                for (const participant of chatroomParticipants){
                    if(participant !== null){
                        output +=
                            "<tr>" +
                            "<td>" + participant.username + "</td>" +
                            "</tr>";
                    }
                }
                output += "</table>";
                $("#allUsers").empty().html(output);
            }catch(err){
                console.log("Errormessage: ", err.message);
                $("#notLoggedInErrorMessage").innerHTML = "Not logged in";
            }

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
    });

    //When a user logs out, the cookies containing their userID and username will be removed, the user will be
    // removed from the chatrooms he is participating in and removed from the list of users
    $("#logOut").click(function(){
        let userLoggedIn = {
            userID : user.userID
        };

        $.get("/getAll", function(allAvailableRooms){
            $.each(allAvailableRooms, function(counter, room){
                $.get("/getParticipants", {roomID:room.roomID}, function(chatroomParticipants){
                    for(const p of chatroomParticipants){
                        let participant = {
                            roomID : room.roomID,
                            userID : p.userID
                        };

                        if(participant.userID === userLoggedIn.userID){
                            $.post("/deleteUserFromRoom", participant).done(function(){
                            });
                        }
                    }
                });
            });
        });
        $.post("/deleteUser", userLoggedIn);
        $(location).attr('href', 'index.html');
        setCookie("username", null, 0);
        setCookie("userID", null, 0);
    });

    // button to go back to the mainpage
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


// code for adding three bots by pressing a button
    $("#addHannah").click(function() {
        let data = {
            roomID : roomID,
            username : "Hannah (bot)"
        };


        $.post("/addBotToRoom", data).done(function () {
            location.reload();
        });
    });

    $("#addCaroline").click(function() {
        let data = {
            roomID : roomID,
            username : "Caroline (bot)"
        };


        $.post("/addBotToRoom", data).done(function () {
            location.reload();
        });
    });

    $("#addAmalie").click(function() {
        let data = {
            roomID : roomID,
            username : "Amalie (bot)"
        };


        $.post("/addBotToRoom", data).done(function () {
            location.reload();
        });
    });


});