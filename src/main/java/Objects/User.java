package Objects;

import java.util.UUID;

public class User {
    private String userID;
    private String username;

    public User(String username) {
        this.username = username;
        this.userID = UUID.randomUUID().toString();
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
