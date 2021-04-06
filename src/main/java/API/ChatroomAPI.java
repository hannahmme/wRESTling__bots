package API;

import Objects.Chatrooms;
import Objects.Chatroom;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ChatroomAPI {

    @PostMapping("/addOne")
    public void addOne(String roomName){
        Chatroom newChatroom = new Chatroom(roomName);
        Chatrooms.addRoom(newChatroom);
    }

    @GetMapping("/getAll")
    public ArrayList<Chatroom> getAll(){
        return Chatrooms.getChatrooms();
    }

    @PostMapping("/deleteChatroom")
    public void deleteChatroom(){
    }







}
