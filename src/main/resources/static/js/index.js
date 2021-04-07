"use strict";
$(function(){
    $("#registerErrorMessage").hide();

    $("#regUser").click(function() {
        const username = $("#username").val();
        if(username.length === 0 || username === ' ' || username === null){
            $("#registerErrorMessage").show();
        }
        else{
            $("#registerErrorMessage").hide();
            const newUser = {
                username : username
            };
            // sending the object user into the controller-function "saveUser"
            $.post("/registerUser", newUser, function(user) {
                $("#username").val("");
                setCookie("username", username, 1);
                setCookie("userID", user.userID, 1);

                // user goes to mainPage after logging in and getting an ID
                $(location).attr('href', 'mainPage.html');
            });
        }
    });
});