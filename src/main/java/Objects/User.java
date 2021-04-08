package Objects;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private static ArrayList<User> allActiveUsers = new ArrayList<>();

    private String userID;
    private String username;

    public User(String username) {
        this.username = username;
        this.userID = UUID.randomUUID().toString();
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //method to print all registrered users on the main page
    @GetMapping("/printallUsers")
    public ArrayList<User> printallUsers(){
        return allActiveUsers;
    }

    // TODO: print all users in a specific room?
/*    @GetMapping("/printUsersInRoom")
    public ArrayList<User> printUsersInRoom(String roomID){
        ArrayList<User> roomSpecificactiveUsers = new ArrayList<>();

        for(User u : allActiveUsers){

        }
        return roomSpecificactiveUsers;
    }
*/

}
