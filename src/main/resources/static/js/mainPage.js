$(function(){
    getAllChatrooms();
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

function getAllChatrooms(){
    $.get("/getAll", function(allAvailableRooms){
        let chatroomCardElement = document.getElementById("card-container");

        $.each(allAvailableRooms, function(counter, room){
            const card = document.createElement("div");
            card.classList.add('card-body');

            let cardContent =
                "<div class='card' style='width:18rem'>" +
                    "<div class='card-body'>" +
                        "<h5 class='card-title'>"+room.roomName+"</h5>" +
                        "<p class='card-text'>Some info here</p>" +
                        "<a id='goToChatRoom' href='chatroom.html?chatroomID="+room.roomID+"' class='btn btn-primary'>Go to chatroom</a>" +
                        "<a id='deleteChatRoom' class='btn btn-primary'>Delete chatroom</a>" +
                    "</div>" +
                "</div>";

            chatroomCardElement.innerHTML += cardContent;
        });
    });
}

// TODO: slette bruker fra alle lister brukeren er med i i java. må sende noe info i et get-kall?
//When a user logs out, the cookies containing their userID and username will be removed
    $("#logOut").click(function(){
        setCookie("username", null, 0);
        setCookie("userID", null, 0);
        $(location).attr('href', 'index.html');
    });
});

