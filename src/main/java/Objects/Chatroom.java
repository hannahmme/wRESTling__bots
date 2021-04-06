package Objects;

import java.util.ArrayList;
import java.util.UUID;

public class Chatroom {
    private String roomID;
    private String roomName;

    public Chatroom(String roomName) {
        this.roomID = UUID.randomUUID().toString();
        this.roomName = roomName;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
