
/*
//?
document.getElementById("joinChatroom").onclick = function () {
    location.href = "../../../../../../Kopi av wrestling bots/wRESTling_bots/chatroom";
};
*/

$(function(){
    getAllChatrooms();
    let userLoggedIn = document.getElementById("userLoggedIn");
    let user = getUser();
    userLoggedIn.innerHTML = user.username;

    $("#createChatroom").click(function(){
        const roomName = $("#roomName").val();
        const newRoom = {
            roomName: roomName,
            creator : {
                username : user.username,
                userID : user.userID
            }
        };
        $.post("/addOne", newRoom);
        getAllChatrooms();

    });

function getAllChatrooms(){
    $.get("/getAll", function(allAvailableRooms){
        let chatroomsElement = document.getElementById("chatrooms");
        $("#chatrooms").empty();
     /*   chatroomsElement.empty();*/

        $.each(allAvailableRooms, function(i, room) {
            let div = document.createElement("div");
            div.innerHTML = room.roomName;
            div.id = room.roomID;
            div.classList.add("chatroom");
            chatroomsElement.appendChild(div);
        });
    });
}

//When a user logs out, the cookies containing their userID and username will be removed
    $("#logOut").click(function(){
        setCookie("username", null, 0);
        setCookie("userID", null, 0);
        $(location).attr('href', 'index.html');
    });















});

