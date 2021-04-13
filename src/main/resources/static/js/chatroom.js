$(window).on('load', function(){
    let urlObject = new URL(window.location.href);
    let roomID = urlObject.searchParams.get('chatroomID');
    getMessages();
    $("#notLoggedInErrorMessage").hide();

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

                let reversedMessages = chatroomMessages.reverse();
                for (const msg of reversedMessages) {
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

                try{
                    for (const participant of chatroomParticipants) {
                        console.log("deltaker i chatrommet: ", participant);
                        output +=
                            "<tr>" +
                            "<td>" + participant.username + "</td>" +
                            "</tr>";
                    }
                output += "</table>";
                $("#allUsers").empty().html(output);
            }catch(err){
                    console.log("Exception:", err);
                    $("#usernameNullErrorMessage").innerHTML = err.message;
                }

        });
    }

    $("#createMessage").click(function() {
        let msg = $("#message").val();
        console.log("Melding i chatroom: ", msg);
        let msgData = {
            roomID : roomID,
            userID : user.userID,
            msg : msg
        };

        $.post("/addMessage", msgData, )

        $.get("/getParticipants", function(listOfParticipants){
            for(let participant of listOfParticipants){
                console.log("Inne i for-løkken i listOfParticipants");
                if(user.userID === participant.userID){
                    $.post("/addMessage", msgData)
                        .done(function () { // JavaScript promise: funksjonen kalles når post-kallet er ferdig.
                            getMessages();  // (sørger for at getMessages() blir kalt etter vi har lagt til bruker)
                            $("#message").html('');
                            $("#notLoggedInErrorMessage").hide();
                        });
                }else{
                    $("#notLoggedInErrorMessage").show();
                }
            }
        });
    });


    //When a user logs out, the cookies containing their userID and username will be removed
    $("#logOut").click(function(){
        setCookie("username", null, 0);
        setCookie("userID", null, 0);

        let data1 = {
            userID : user.userID
        };

        $.post("/deleteUser", data1).done(function(){
            $(location).attr('href', 'index.html');
        });
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
});




