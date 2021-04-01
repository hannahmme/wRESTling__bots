package Objects;

public class Message {
    private User user;
    private String message;
    private String timestamp;

    public Message(User user, String message, String timestamp){
        this.user = user;
        this.message = message;
        this.timestamp = message;
    }


    public User getPerson() {
        return user;
    }

    public void setPerson(User user) {
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
