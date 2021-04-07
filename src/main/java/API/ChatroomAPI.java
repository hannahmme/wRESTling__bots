package API;

import Objects.Chatrooms;
import Objects.Chatroom;
import Objects.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ChatroomAPI {

    @PostMapping("/addOne")
    public void addOne(String roomName, User creator){
        Chatroom newChatroom = new Chatroom(roomName, creator);
        Chatrooms.addRoom(newChatroom);
    }

    @PostMapping("/addParticipant")
    public void addParticipant(String roomID, User user){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            if(chatroom.getRoomID().equals(roomID)){
                chatroom.addParticipant(user);
                System.out.println(chatroom.getParticipants().toString());
            }
        }
    }

    @GetMapping("/getAllParticipants")
    public ArrayList<User> getAllParticipants(String roomID){
        ArrayList<Chatroom> list = Chatrooms.getChatrooms();
        for(Chatroom chatroom : list){
            if(chatroom.getRoomID().equals(roomID)){
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







}
