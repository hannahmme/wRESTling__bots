package Objects;

import java.util.ArrayList;
import java.util.UUID;

public class Chatroom {
    private String creator;
    private String roomName;
    private String roomID;

    public Chatroom(String roomName) {
        this.roomName = roomName;
        this.roomID = UUID.randomUUID().toString();
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
