package API;

import Objects.*;
import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
public class ChatroomAPI {

    @PostMapping("/addOne")
    public void addOne(String roomName, User creator){
        Chatroom newChatroom = new Chatroom(roomName, creator);
        Chatrooms.addRoom(newChatroom);
    }

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

    @GetMapping("/getOneParticipant")
    public String getOneParticipant(String userID){
        User aUser = Users.getUser(userID);
        if (aUser != null) {
            System.out.println(aUser.getUsername());
            return aUser.getUsername();
        }
        else{return null;}
    }

    @GetMapping("/getAllParticipants")
    public ArrayList<User> getAllParticipants(String roomID){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                return chatroom.getParticipants();
            }
        }
        return null;
    }

    @GetMapping("/getAll")
    public ArrayList<Chatroom> getAll(){
        return Chatrooms.getChatrooms();
    }

    @PostMapping("/deleteChatroom")
    public void deleteChatroom(){
    }

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
            }
        }
    }

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


}
