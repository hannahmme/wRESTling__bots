package Objects;

import java.util.ArrayList;

public class Chatrooms {

    private static ArrayList<Chatroom> chatrooms = new ArrayList<>();

    public static Chatroom getChatroomById(String roomID){
        for(Chatroom chatroom : chatrooms){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                return chatroom;
            }
        }
        return null;
    }

    public static ArrayList<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public static void addRoom(Chatroom room){
        chatrooms.add(room);
    }
}
