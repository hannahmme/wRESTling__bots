package Objects;

import java.util.ArrayList;
import java.util.UUID;

public class Chatroom {
    private User creator;
    private String roomName;
    private String roomID;
    private ArrayList<User> participants;

    public Chatroom(String roomName, User creator) {
        this.creator = creator;
        this.roomName = roomName;
        this.roomID = UUID.randomUUID().toString();
    }

    public ArrayList<User> getParticipants(){
        return this.participants;
    }

    public User getCreator(){
        return this.creator;
    }

    public String getRoomID() {
        return this.roomID;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
