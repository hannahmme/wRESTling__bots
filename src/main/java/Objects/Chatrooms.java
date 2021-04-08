package Objects;


import java.util.ArrayList;

public class Chatrooms {

    public static ArrayList<Chatroom> chatrooms = new ArrayList<>();

    public void initialize() {
        chatrooms = new ArrayList<>();
    }

    public static ArrayList<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public static void addRoom(Chatroom room){
        chatrooms.add(room);
    }


}
