package API;

import com.oslomet.chatroom.Objects.User;
import com.oslomet.chatroom.dal.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//restcontroller blir vel riktig?
@RestController
public class RestAPIServer {

    @Autowired
    private UserRepo rep;

    @PostMapping("registerUser")
    public void registerUser(User aUser){
        rep.saveUser(aUser);
    }

    @GetMapping("/print")
    public List<User> print(){
        return rep.getUsersInRoom();
    }

}
