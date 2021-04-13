$(function(){
    getAllChatrooms();
    getAllUsers();
    $("#chatroomErrorMessage").hide();
    let userLoggedIn = document.getElementById("userLoggedIn");
    let user = getUser();

    if (user.userID === null || user.userID === '') {
        window.alert("You need to be a registered user to be in this chatroom.");
        setTimeout(function () {
            window.location.href = 'http://localhost:8080/index.html';
        })
    }

    if(user.userID === null || user.userID === ''){}
    userLoggedIn.innerHTML = user.username;

    $("#createChatroom").click(function(){
        const roomName = $("#roomName").val();
        if(roomName.length === 0 || roomName === ' ' || roomName === null){
            $("#chatroomErrorMessage").show();
        }
        else{
            const newRoom = {
                roomName: roomName,
                creator : {
                    username : user.username,
                    userID : user.userID
                }
            };
            $("#chatroomErrorMessage").hide();
            $.post("/addOne", newRoom);
            location.reload();
        }
    });

    // function to get all users of chatrooms
    function getAllUsers(){
        $.get("/getAllUsers", function(users){
            let output =
                "<table class='table table-striped table-bordered'>" +
                "<tr>" +
                "<th>Username</th>" +
                "</tr>";

            for (const user of users){
                output +=
                    "<tr>" +
                    "<td>" + user.username + "</td>" +
                    "</tr>";
            }
            output += "</table>";
            $("#allUsers").empty().html(output);
        });
    }

    // function to get and display all chatrooms
    function getAllChatrooms(){
    $.get("/getAll", function(allAvailableRooms){
        let chatroomCardElement = document.getElementById("card-container");

        $.each(allAvailableRooms, function(counter, room){
            const card = document.createElement("div");
            card.classList.add('card-body');

            let cardContent =
                "<div class='card' id='"+room.roomID+"' style='width:30%; padding: 5px;'>" +
                    "<div class='card-body' style='padding: 10px;'" +
                        "<h5 class='card-title'>"+room.roomName+"</h5>" +
                        "<p class='card-text'>Some info here</p>" +
                        "<a id='goToChatRoom' href='chatroom.html?chatroomID="+room.roomID+"' class='btn btn-primary'>Go to chatroom</a>" +
                    "</div>" +
                "</div>";

            chatroomCardElement.innerHTML += cardContent;

        });
    });
}

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


    // user wants to receive push notifications
    $("#connectPush").click(function(){
        // post because it will not return anything, only start a session
        $.post("/activatePush", user);
        // reload location every 10secs?
        location.reload();
    });

});