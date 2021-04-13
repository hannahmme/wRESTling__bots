package API;

import Objects.User;
import Socket.PushNotificationSocket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WSController {

    /**
     *
     * @param aUser: taking in which user wants to enable push notifications
     */
    @PostMapping("/activatePush")
    public void activePush(User aUser) {
        PushNotificationSocket.userID = aUser.getUserID();
    }
}
