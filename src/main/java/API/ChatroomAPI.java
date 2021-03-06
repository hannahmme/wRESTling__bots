package API;

import Objects.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatroomAPI {

    // add one room
    @PostMapping("/addOne")
    public void addOne(String roomName, User creator){
        Chatroom newChatroom = new Chatroom(roomName, creator);
        Chatrooms.addRoom(newChatroom);

        // add moderator to chatroom
        List<User> users = Users.getRegisteredUsers();
        for(User u : users){
            if(u.getUsername().equals("Moderator")){
                newChatroom.addParticipant(u.getUserID());
            }
        }
    }

    //add new participant of chatroom
    @PostMapping("/addParticipant")
    public String addParticipant(String roomID, String userID){
        if(userID.isEmpty() || userID.isBlank()){
            return "Invalid user-ID.";
        }
        if(roomID.isEmpty() || roomID.isBlank()){
            return "RoomID is not set.";
        }

        //If user trying to send message is not a registered user
        if(!Users.userIsRegistered(userID)){
            return "You need to be a registered user before you can have acceess";
        }

        Chatroom chatroom = Chatrooms.getChatroomById(roomID);
        if(chatroom == null){
            return "Chatroom not found";
        }

        if(chatroom.getParticipantById(userID) == null){
            chatroom.addParticipant(userID);
            return "User added as participant in chatroom";
        }
        return "User is already a participant.";
    }

    //get list of participants of specific chatroom
    @GetMapping("/getParticipants")
    public ArrayList<User> getParticipants(String roomID){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                return chatroom.getParticipants();
            }
        }
        return null;
    }

    //remove user as registered user (delete user from application)
    @PostMapping("/deleteUser")
    public void deleteUser(String userID){
        Users.deleteUser(userID);
    }

    //delete user from specific chatroom
    @PostMapping("/deleteUserFromRoom")
    public void deleteUserFromRoom(String roomID, String userID){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                chatroom.deleteParticipant(userID);
            }
        }
    }

    //get all chatrooms
    @GetMapping("/getAll")
    public ArrayList<Chatroom> getAll(){
        return Chatrooms.getChatrooms();
    }

    //add message to list of messages belonging specific chatroom
    @PostMapping("/addMessage")
    public String addMessage(String roomID, String userID, String msg) {
        Chatroom chatroom = Chatrooms.getChatroomById(roomID);
        if (chatroom == null) {
            return "Chatroom not found";
        }

        //if user is not a registered user
        if (!Users.userIsRegistered(userID)) {
            return "You need to be a registered user before you can send a message";
        }
        //if the user is a registered user, check if user i a participant of chatroom
        User user = chatroom.getParticipantById(userID);
        if (user == null) {
            return "User is not a participant in this room";
        }

        //if user is a participant of chatroom, add message
        Message message = new Message(userID, msg, roomID);
        chatroom.addMessage(message);

        // getting answer from modbot
        Message modAnswer = Chatbots.modResp(message);
        if (modAnswer != null) {
            chatroom.addMessage(modAnswer);
        }

        // getting answers from other bots if they are added to the room
        for (User u : chatroom.getParticipants()) {
            if (u.getUsername().equals("Hannah (bot)")) {
                Message answer = Chatbots.hannahResp(message);
                if (answer != null) {
                    chatroom.addMessage(answer);
                }
            }
            if (u.getUsername().equals("Caroline (bot)")) {
                Message answer = Chatbots.carolineResp(message);
                chatroom.addMessage(answer);
            }
            if (u.getUsername().equals("Amalie (bot)")) {
                Message answer = Chatbots.amalieResp(message);
                chatroom.addMessage(answer);
            }
        }
        return "OK, message sent";
    }


    //get messages from specific chatroom
    @GetMapping("/getMessages")
    public ArrayList<Message> getMessages(String roomID){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                return chatroom.getMessages();
            }
        }
        return null;
    }

    //register new user
    @PostMapping("/registerUser")
    public User registerUser(String username){
        User newUser = new User(username);
        Users.addUser(newUser);
        return newUser;
    }

    //get all registered users
    @GetMapping("/getAllUsers")
    public ArrayList<User> getAllUsers(){
        return Users.getRegisteredUsers();
    }


    @PostMapping("/addBotToRoom")
    public void addBotToRoom(String roomID, String username){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){

                for (User u : Users.getRegisteredUsers()) {
                    if (username.equals(u.getUsername())) {
                        chatroom.addParticipant(u.getUserID());
                    }
                }
            }
        }
    }

    //Get name of chat room
    @GetMapping("/getRoomname")
    public String getRoomname(String roomID){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom room : list){
            String roomIDstring= room.getRoomID();
            if(roomIDstring.equals(roomID)){
                return room.getRoomName();
            }
        }
        return null;
    }
}
