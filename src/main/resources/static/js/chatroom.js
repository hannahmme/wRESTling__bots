
print();

// post-metode for å sende roomID til javacontroller?

$.get("/printMsgs", function(allMsgs) {
    formatMsgTable(allMsgs);
});

// method to format
function formatMsgTable(allmsg) {
    let out = "<table class='table table-striped table-bordered'>" + "<tr>" +
        "<th>Sent</th> <th>User</th> <th>Messsage</th> + "</tr>";
    for (const msg of allmsg) {
        out += "<tr><td>" + msg.timestamp + "</td><td>" + "</td><td>" + msg.user +
            msg.message + "</td></tr>";
    }
    $("#allmsg").html(out);
}




function formatTable(users) {
    let output = "<table class='table table-striped table-bordered'>" + "<tr>" +
        "<th>Username</th> + " < /tr>";
    for (const user of users) {
        output += "<tr><td>" + user.username + "</td><td>";
    }
    $("#onlineUsers").html(output);

}


// function to print out a table of online users in the chatroom
function printUsers(){
    $.get("/printUsers", function(users) {
        formatTable(users);
    });
}