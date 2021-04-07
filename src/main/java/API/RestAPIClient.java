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

    //TODO: Testdata bør opprettes i klassene objektene hører til (Users, chatrooms).
/*
    // testdata initialized when running the application
    @BeforeClass
    public static void initialize() {
        User testuser1 = new User("Admin");
        activeUsers.add(testuser1);

        Chatroom testroom1 = new Chatroom( "Katter", testuser1);
        activeChatrooms.add(testroom1);

        Message testmsg1 = new Message(testuser1, "Testing",
                "2021-04-04 11:05:00 AM", 1);
        allMsg.add(testmsg1);
    }
*/


    //TODO: lge en ny arrayliste for hvert chatrom som inneholder alle meldinger?
    // at det da blir opprettet automatisk hver gang et nytt rom lages?

// for startpage
    @PostMapping("/registerUser")
    public User registerUser(String username){
        User newUser = new User(username);
        Users.addUser(newUser);
        return newUser;
    }

    //TODO: Flyttet liste over registrerte brukere til Users-klassen.
    //TODO: Lage metode i Users som returnerer alle online-brukere. Kan brukes til å skrive ut
    //      alle brukere på mainPage?
/*
    //method to print all registrered users, not users belonging to chatrooms
    @GetMapping("/printUsers")
    public ArrayList<User> printUsers(String roomID){
        return activeUsers;
    }*/


    //TODO: Flytte til ChatroomAPI og kalle på spesifikke chatrommet sin liste med roomID
  /*  @GetMapping("/printMsgs")
    public ArrayList<Message> printMsgs(int roomID){
        ArrayList<Message> ret = new ArrayList<>();
        for(Message msg : allMsg){
            if(msg.getRoomID()==roomID){
                ret.add(msg);
            }
        }
        return ret;
    }*/
}
