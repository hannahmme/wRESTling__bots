function registerUser() {

    let txtFailUsername;
    let username = document.getElementById("username").value();
    if (username === "" || username === null) {
        txtFailUsername = "You must enter a value for username.";
    } else {
        txtFailUsername = "";
    }
    document.getElementById("txtFailUsername").innerHTML = txtFailUsername;

    let txtFailPassword;
    let password = document.getElementById("password").value();
    if (password === "" || password === null) {
        txtFailUsername = "You must enter a value for password.";
    } else {
        txtFailPassword = "";
    }
    document.getElementById("txtFailPassword").innerHTML = txtFailPassword;

    let txtFailSecurityQ;
    let securityQ = document.getElementById("securityQ").value();
    if (securityQ === "" || securityQ === null) {
        txtFailUsername = "You must enter a value for securityQ.";
    } else {
        txtFailSecurityQ = "";
    }
    document.getElementById("txtFailSecurityQ").innerHTML = txtFailSecurityQ;

    let txtFailSecurityA;
    let securityA = document.getElementById("securityA").value();
    if (securityA === "" || securityA === null) {
        txtFailUsername = "You must enter a value for securityA.";
    } else {
        txtFailSecurityA = "";
    }
    document.getElementById("txtFailSecurityA").innerHTML = txtFailSecurityA;


    if (txtFailUsername === "" && txtFailPassword === ""
        && txtFailSecurityQ === "" && txtFailSecurityA === "") {
        const user = {
            username: username,
            password: password,
            securityQ: securityQ,
            securityA: securityA,
        };

        $.post("/saveUser", user, function() {
            print()
        });

        // nullstiller tekstfeltene så nestemann kan registrere seg
        $("#username").val("");
        $("#password").val("");
        $("#securityQ").val("");
        $("#securityA").val("");

    }

}

// function to print out a table of online users in the chatroom
function print(){
    $.get("/print", function(users) {
        formatTable(users);
    })
}

// function to format table pretty
function formatTable(users){
    let output = "<table class='table table-striped table-bordered'>" + "<tr>" +
        "<th>Username</th> + "</tr>";
    for (const user of users) {
        output += "<tr><td>" + user.username + "</td><td>";
    }
    $("#onlineUsers").html(output);

}