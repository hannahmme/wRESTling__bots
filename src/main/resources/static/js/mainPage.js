
/*
//?
document.getElementById("joinChatroom").onclick = function () {
    location.href = "../../../../../../Kopi av wrestling bots/wRESTling_bots/chatroom";
};
*/


$(function(){


    $.get("/getAll", function(allAvailableRooms){
        let chatroomsElement = document.getElementById("chatrooms");

        $.each(allAvailableRooms, function(i, room) {
            let div = document.createElement("div");
            div.innerHTML = room.roomName;
            div.id = room.roomID;
            div.classList.add("chatroom");
            chatroomsElement.appendChild(div);
        });
    });








});

