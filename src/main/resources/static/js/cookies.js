/*https://www.w3schools.com/js/js_cookies.asp - A function to set a Cookie*/
function setCookie(name, value, validDays){
    let date = new Date();
    date.setTime(date.getTime() + (validDays*24*60*60*1000));
    let expires = "Expires= " + date.toUTCString();
    document.cookie = name + "= " + value + "; " + expires + ";path=/";
}
/*https://www.w3schools.com/js/js_cookies.asp - A function to get a Cookie*/
function getCookie(cookieName){
    let name = cookieName + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let splitArray = decodedCookie.split(';');
    for(let counter = 0; counter < splitArray.length; counter++){
        let cookie = splitArray[counter];
        while(cookie.charAt(0) === ' '){
            cookie = cookie.substring(1);
        }
        if(cookie.indexOf(name) === 0){
            return cookie.substring(name.length, cookie.length);
        }
    }
    return "";
   /* $.each(splitArray, function(counter, cookieName){
        let cookie = cookieName;
        while(cookie.charAt(0) == ' '){
            cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) == 0){
            return cookie.substring(name.length, cookie.length);
        }
    });
    return "";*/
}