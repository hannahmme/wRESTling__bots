package API;

import Objects.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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


    // TODO: ikke helt ferdig (A)
    @PostMapping("/addMessage")
    public void addMessage(String roomID, String userID, String msg){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            String chatroomID = chatroom.getRoomID();
            if(chatroomID.equals(roomID)){
                Message aMsg = new Message(userID,msg, LocalDateTime.now().toString(),roomID);
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
