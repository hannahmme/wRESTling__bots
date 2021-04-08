package API;

import Objects.User;
import Objects.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestAPIClient {


// for startpage. TODO: er det behov for å vise alle brukere på mainpage?
    @PostMapping("/registerUser")
    public User registerUser(String username){
        User newUser = new User(username);
        Users.addUser(newUser);
        return newUser;
    }




}
