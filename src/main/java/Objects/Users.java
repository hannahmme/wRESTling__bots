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

    public static void deleteUser(User user){
        for(User aUser : registeredUsers){
            String UID = aUser.getUserID();
            System.out.println("UserID i listen: " + UID);
            if(UID.equals(user.getUserID())){
                registeredUsers.remove(user);
            }
        }
    }

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
