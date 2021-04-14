$(window).on('load', function(){
    let urlObject = new URL(window.location.href);
    let roomID = urlObject.searchParams.get('chatroomID');

    let user = getUser();

    let data = {
        roomID : roomID,
        userID : user.userID
    };

    if(user.userID === null || user.userID === ''){
        window.alert("You need to be a registered user to be in this chatroom.");
        setTimeout(function(){
            window.location.href = 'http://localhost:8080/index.html';
        })
    }else{
        getMessages();
    }

    let userLoggedIn = document.getElementById("userLoggedIn");
    userLoggedIn.innerHTML = user.username;

    $.get("/getRoomname", {roomID:roomID}, function(chatroomName){
        let roomName = document.getElementById("roomName");
        roomName.innerHTML = chatroomName;
    });

    function getMessages() {
        $.get("/getParticipants", {roomID:roomID})
            .done(function(chatroomParticipants){
                $.get("/getMessages", {roomID: roomID})
                    .done(function(chatroomMessages){
                        let output =
                            "<table class='table table-striped table-bordered'>" +
                            "<tr>" +
                            "<th>Username</th>" +
                            "<th>Timestamp</th>" +
                            "<th>Message</th>" +
                            "</tr>";

                        if(chatroomMessages === null || chatroomMessages.length === 0){
                            $("#allMsgs").empty().html("No messages yet. Be the first to say something! :D");
                            return;
                        }

                        for (const msg of chatroomMessages.reverse()) {
                            let username = null;
                            for (const participant of chatroomParticipants){
                                if (msg.userID === participant.userID) {
                                    username = participant.username;
                                    break;
                                }
                            }
                            if(username === null){
                                username = "Deleted user";
                            }

                            output +=
                                "<tr>" +
                                "<td>" + username + "</td>" +
                                "<td>" + msg.timestamp + "</td>" +
                                "<td>" + msg.message + "</td>" +
                                "</tr>";
                        }
                        output += "</table>";
                        $("#allMsgs").empty().html(output);
                    });
        });
    }

    if(data.userID !== null || data.userID !== ''){
        $.post("/addParticipant", data)
            .done(function () { // JavaScript promise: funksjonen kalles når post-kallet er ferdig.
                getParticipants()
                    .done(function(){
                        getMessages();
                    })// (sørger for at getParticipants() blir kallt etter vi har lagt til bruker)
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
                console.error("Errormessage: ", err.message);
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
                $("#message").val("");
            });
    });

    //When a user logs out, the cookies containing their userID and username will be removed, the user will be
    // removed from the chatrooms he is participating in and removed from the list of users
    $("#logOut").click(function(){
        let userLoggedIn = {
            userID : user.userID
        };

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