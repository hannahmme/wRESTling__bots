$(function(){

    $("#regUser").click(function() {

        // genererer først et USERID som returneres hit
        const url = "/RestAPIClient/generateUserID"
        $.post(url, function(retur) {
            const userID = retur;

            // registrerer så en bruker med generert userID og skrevet inn username
            const url2 = "/RestAPIClient/registerUser";
            const user = {
                userID      : userID,
                username    : $("#username").val()
            }

            // sending the object user into the controller-function "saveUser"
            $.post(url2, user, function() {
                $("#username").val("");

                // user goes to mainPage after logging in and getting an ID
                $(location).attr('href', 'mainPage.html');
            });
        });
    });
});