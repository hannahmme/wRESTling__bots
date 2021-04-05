"use strict";
$(function(){
    console.log("hei");

    $("#regUser").click(function() {
        const username = $("#username").val();
        console.log(username);

        // genererer først et USERID som returneres hit
        $.get("/generateUserID",function(retur) {   // TODO: noe er feil - får 404?
            const userID = retur;
            console.log(userID);

            // registrerer så en bruker med generert userID og skrevet inn username
            const user = {
                userID      : userID,
                username    : username
            }

            // sending the object user into the controller-function "saveUser"
            $.post("/registerUser", user, function() {
                $("#username").val("");

                // user goes to mainPage after logging in and getting an ID
                $(location).attr('href', 'mainPage.html');
            });
        });
    });
});