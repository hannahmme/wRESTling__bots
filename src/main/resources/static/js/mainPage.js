/*
//?
document.getElementById("joinChatroom").onclick = function () {
    location.href = "../../../../../../Kopi av wrestling bots/wRESTling_bots/chatroom";
};



// skjønner ikke helt hvordan jeg skal fikse dropdownmeny basert på listen i java (over chatrooms som er laget)
$(function(){

    // the link "getChatrooms" returning a list of available chatrooms
    $.get("/getChatrooms", function(allAvailableRooms){


        //writing htmlcode to be implemented later, where all chatrooms will be added as options
        let drop_chatrooms = "<Select id='chatrooms' class='form-control'>";

        // TODO: (foreach) every line in "data" is iterated?
        $.each(allAvailableRooms, function( i, room) {
            drop_chatrooms += "<option>"+room.roomName+"</option>";
        });
        drop_chatrooms += "</Select>";

        // sending the list into the htmlpage
        $("#select").html(drop_chatrooms);
    });
});

 */