package Objects;

public class Message {
    private User user;
    private String message;
    private String timestamp;
    private int roomID;

    public Message(User user, String message, String timestamp, int roomID){
        this.user = user;
        this.message = message;
        this.timestamp = message;
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
