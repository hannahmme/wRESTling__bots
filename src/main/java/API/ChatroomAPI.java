package API;

import Objects.Chatroom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatroomAPI {

    @GetMapping("/getAll")
    public String getAll(){
        return "Hello from getAllChatrooms";
    }

    @PostMapping("/deleteChatroom")
    public void deleteChatroom(){
    }







}
