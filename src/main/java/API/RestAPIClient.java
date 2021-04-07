package API;

// koden for klienter som skal koble seg opp mot server

import Objects.Chatroom;
import Objects.Message;
import Objects.User;
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


    //TODO: lge en ny arrayliste for hvert chatrom som inneholder alle meldinger?
    // at det da blir opprettet automatisk hver gang et nytt rom lages?

// for startpage
    @PostMapping("/registerUser")
    public void registerUser(User aUser){
        System.out.println(aUser.getUsername());
        activeUsers.add(aUser);

    }




// for chatroom
    // method to print all users active in htat chatroom?
    @GetMapping("/printUsers")
    public ArrayList<User> printUsers(int roomID){
        return activeUsers;
    }

    // getting and returning a list with all messages for room with roomID
    @GetMapping("/printMsgs")
    public ArrayList<Message> printMsgs(int roomID){
        ArrayList<Message> ret = new ArrayList<>();
        for(Message msg : allMsg){
            if(msg.getRoomID()==roomID){
                ret.add(msg);
            }
        }
        return ret;
    }


// for mainpage
    // method to get and return all chatrooms available in an arraylist
    @GetMapping("/getChatrooms")
    public ArrayList<Chatroom> getAvailableChatrooms(){
        return activeChatrooms;
    }


    // method to save new chatrooms to arraylist
    @GetMapping("/newChatroom")
    public void makeChatroom(Chatroom aChatroom){
        activeChatrooms.add(aChatroom);
    }


}
