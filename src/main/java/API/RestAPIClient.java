package API;

// koden for klienter som skal koble seg opp mot server

import Objects.Message;
import Objects.User;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestAPIClient {

    private ArrayList<User> activeUsers = new ArrayList<>();

    //TODO: lge en ny arrayliste for hvert chatrom som inneholder alle meldinger?
    // at det da blir opprettet automatisk hver gang et nytt rom lages?

    @GetMapping("/registerUser")
    public void registerUser(User aUser){
        activeUsers.add(aUser);
    }

    @GetMapping("/printUsers")
    public ArrayList<User> printUsers(){
        return activeUsers;
    }

    // getting and returning a liste with all messages for room with roomID
    @GetMapping("/printMsgs")
        public ArrayList<Message> printMsgs(String roomID){

            return null;
        }

}
