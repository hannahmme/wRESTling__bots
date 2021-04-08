package Objects;

import java.util.ArrayList;

public class Users {
    private static ArrayList<User> registeredUsers = new ArrayList<>();

    public void initialize(){
        registeredUsers = new ArrayList<>();
    }

    public static ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void addUser(User user){
        registeredUsers.add(user);
    }

// TODO: brukes hvis vi skal skrive ut alle brukere på mainpage
    public static User getUser(String userID){
        for(User user : getRegisteredUsers()){
            String userIDstring= user.getUserID();
            if(userIDstring.equals(userID)){
                return user;
            }
        }
        return null;
    }
}
