"use strict";
$(function(){
    console.log("hei");

    $("#regUser").click(function() {
        const username = $("#username").val();
        setCookie( "userCookie", username, 1);
        console.log(username);

        // registrerer bruker med username
        const user = {
            username : username
        };

        // sending the object user into the controller-function "saveUser"
        $.post("/registerUser", user, function() {
            $("#username").val("");

            // user goes to mainPage after logging in and getting an ID
            $(location).attr('href', 'mainPage.html');
        });
    });
});