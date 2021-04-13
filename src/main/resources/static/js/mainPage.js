$(function(){
    getAllChatrooms();
    getAllUsers();
    $("#chatroomErrorMessage").hide();
    let userLoggedIn = document.getElementById("userLoggedIn");
    let user = getUser();
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
                        "<a id='deleteChatroom' class='btn btn-danger'>Delete chatroom</a>" +
                    "</div>" +
                "</div>";

            chatroomCardElement.innerHTML += cardContent;

        });
    });
}
    $("#deleteChatroom").click(function(){
        var cardId = $(".card").attr('id');
        const url = "/deleteChatroom?roomID="+cardId;
        $.post(url, function(){
            window.location.reload();
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



});