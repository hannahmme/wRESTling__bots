package Objects;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.UUID;

public class Chatroom {
    private User creator;
    private String roomName;
    private String roomID;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;

    public Chatroom(String roomName, User creator) {
        this.creator = creator;
        this.roomName = roomName;
        this.roomID = UUID.randomUUID().toString();
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public User getParticipantById(String userID){
        if(userID.isEmpty() || userID.isBlank() || userID == null){
            return null;
        }
        for(User participant : getParticipants() ){
            String userIDstring = participant.getUserID();

            if(userIDstring.equals(userID)){
                return participant;
            }
        }
        return null;
    }

    public void addParticipant(String userID){
        if(userID.isEmpty() || userID.isBlank() || userID == null){
            return;
        }
        if(getParticipantById(userID) == null){
            User participant = Users.getUser(userID);
            this.participants.add(participant);
        }

    }

    // deleting participant from one room
    public void deleteParticipant(String userID){
        participants.removeIf(u -> u.getUserID().equals(userID));
    }

    public ArrayList<User> getParticipants(){
        return participants;
    }

    public void addMessage(Message aMsg) {
        this.messages.add(aMsg);
    }

    public ArrayList<Message> getMessages(){
        return messages;
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
