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

    //Removes object from all registered users and all chatrooms
    public static void deleteUser(String userID) {
        ArrayList<Chatroom> chatrooms = Chatrooms.getChatrooms();
        for(Chatroom chatroom : chatrooms){
            chatroom.deleteParticipant(userID);
        }
        User user = Users.getUser(userID);
        registeredUsers.remove(user);
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
