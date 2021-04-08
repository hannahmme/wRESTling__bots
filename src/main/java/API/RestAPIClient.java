package API;

// koden for klienter som skal koble seg opp mot server

import Objects.Chatroom;
import Objects.Message;
import Objects.User;
import Objects.Users;
import org.junit.BeforeClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RestAPIClient {

    private static ArrayList<Message> allMsg = new ArrayList<>();

// for startpage
    @PostMapping("/registerUser")
    public User registerUser(String username){
        User newUser = new User(username);
        Users.addUser(newUser);
        return newUser;
    }




}
