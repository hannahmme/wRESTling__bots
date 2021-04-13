package Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String userID;
    private String message;
    private String timestamp;
    private String roomID;

    public Message(String userID, String message, String roomID){
        this.userID = userID;
        this.message = message;

        // formatting the time so it is readable
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = time.format(formatter);

        this.timestamp = formattedTime;
        this.roomID = roomID;
    }

    public Message(){

    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
