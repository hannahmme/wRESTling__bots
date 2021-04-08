"use strict";
$(function(){
    console.log("hei");

    $("#regUser").click(function() {
        const username = $("#username").val();

        // genererer først et USERID som returneres hit
        $.get("/generateUserID",function(retur) {
            const userID = retur;

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