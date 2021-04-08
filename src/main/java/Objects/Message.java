package Objects;

public class Message {
    private String userID;
    private String message;
    private String timestamp;
    private String roomID;

    public Message(String userID, String message, String timestamp, String roomID){
        this.userID = userID;
        this.message = message;
        this.timestamp = timestamp;
        this.roomID = roomID;
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
