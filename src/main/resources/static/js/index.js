function index() {

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


    if (txtFailUsername === "" && txtFailPassword === "") {
        const user = {
            username: username,
            password: password,
        };

        // sending the object user into the controller-function "saveUser"
        $.post("/saveUser", user, function() {});

        // nullstiller tekstfeltene så nestemann kan registrere seg
        $("#username").val("");
        $("#password").val("");


        // user goes to mainPage after logging in and getting an ID
        $("#index").click(function(){
            $(location).attr('href', 'mainPage.html');
            });

    }
}