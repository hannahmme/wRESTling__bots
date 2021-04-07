
/*
//?
document.getElementById("joinChatroom").onclick = function () {
    location.href = "../../../../../../Kopi av wrestling bots/wRESTling_bots/chatroom";
};
*/

$(function(){
    getAllChatrooms();
    $("#chatroomErrorMessage").hide();
    let userLoggedIn = document.getElementById("userLoggedIn");
    let user = getUser();
    userLoggedIn.innerHTML = user.username;

    $("#createChatroom").click(function(){
        const roomName = $("#roomName").val();
        if(roomName.length === 0 || roomName === ' '){
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

       /* getAllChatrooms();*/

    });

function getAllChatrooms(){
    $.get("/getAll", function(allAvailableRooms){
        let chatroomCardElement = document.getElementById("card-body");

        $.each(allAvailableRooms, function(counter, room){
            const card = document.createElement("div");
            card.classList.add('card-body');

            let cardContent =
                "<div class='card' style='width:18rem'>" +
                    "<div class='card-body'>" +
                        "<h5 class='card-title'>"+room.roomName+"</h5>" +
                        "<p class='card-text'>Some info here</p>" +
                        "<a href='#' class='btn btn-primary'>Go to chatroom</a>" +
                    "</div>" +
                "</div>";

            chatroomCardElement.innerHTML += cardContent;
        });

        /*let chatroomsElement = document.getElementById("chatrooms");
        $("#chatrooms").empty();

        $.each(allAvailableRooms, function(i, room) {
            let div = document.createElement("div");
            div.innerHTML = room.roomName;
            div.id = room.roomID;
            div.classList.add("chatroom");
            chatroomsElement.appendChild(div);
        });*/
    });
}

//When a user logs out, the cookies containing their userID and username will be removed
    $("#logOut").click(function(){
        setCookie("username", null, 0);
        setCookie("userID", null, 0);
        $(location).attr('href', 'index.html');
    });















});

