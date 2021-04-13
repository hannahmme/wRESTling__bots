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

        System.out.println("Chatroom opprettet med navn: " + newChatroom.getRoomName() + " og id: " + newChatroom.getRoomID());
    }

    //add new participant of chatroom
    @PostMapping("/addParticipant")
    public void addParticipant(String roomID, String userID){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                chatroom.addParticipant(userID);
                System.out.println(chatroom.getRoomName() + " har antall deltakere: " + chatroom.getParticipants().size());
            }
        }
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

    // TODO: slette bruker om hun trykker på logg ut knappen
    //remove user as registered user (delete user from application)
    @PostMapping("/deleteUser")
    public void deleteUser(String userID){
        ArrayList<User> list = Users.getRegisteredUsers();
        for(User user : list){
            String UID = user.getUserID();
            if(UID.equals(userID)){
                Users.deleteUser(user);
            }
        }
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
    public void addMessage(String roomID, String userID, String msg){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                // formatting the time so it is readable
                LocalDateTime time = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedTime = time.format(formatter);
                Message aMsg = new Message(userID, msg, formattedTime,roomID);
                chatroom.addMessage(aMsg);

                Message botAnswer = Chatbots.respond(aMsg);
                if(botAnswer!=null) {
                    chatroom.addMessage(botAnswer);
                }
            }
        }
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


}
