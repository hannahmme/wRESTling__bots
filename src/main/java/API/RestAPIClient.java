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

    private static ArrayList<User> activeUsers = new ArrayList<>();
    private static ArrayList<Chatroom> activeChatrooms = new ArrayList<>();
    private static ArrayList<Message> allMsg = new ArrayList<>();

    // OK: method to generate the next available userID
    @GetMapping("/generateUserID")
    public int nextUserID(){
        int listsize = activeUsers.size();
        int nextUserID;

        if(listsize>0) {
            User lastUser = activeUsers.get(listsize - 1);
            int lastUserID = lastUser.getUserID();
            nextUserID = lastUserID + 1;
        }
        else {
            nextUserID = 1;
        }
        return nextUserID;
    }



    //TODO: lage en ny arrayliste for hvert chatrom som inneholder alle meldinger? Eller ha en lang arrayliste?

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
