package Objects;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String userID;
    private String username;
    public static ArrayList<Notification> notifications;

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

    public static ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public static void setNotifications(ArrayList<Notification> notifications) {
        User.notifications = notifications;
    }
}
