// Websocket Server Endpoint

package Socket;

import java.io.IOException;
import java.util.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

// the WebSocket is listening to this URI. This is the Endpoint-code
@ServerEndpoint("/pushnotification")
public class PushNotificationSocket {

    // getting the userID to not send msgnotification to person who sent the message
    public static String userID;

    // a list over all connected clients/ sessions (all have pressed the button)
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    // code for broadcasting to all other members of rooms if not in room
    public void broadcast(String message) throws IOException {
        // a message is created and sent to all endpoints (everyone in that room)
        for (Session s : sessions){
            s.getBasicRemote().sendText(message);
        }
    }

    // happens when the connection opens
    @OnOpen
    public void onOpen(Session session) {
        // get session and websocket connection
        sessions.add(session);
    }

    //receives the information from the Websocket when a message is sent to Endpoint
    @OnMessage
    public void onMessage(String msg) throws IOException {
        // sending incoming message to all clients connected to room
        broadcast(msg);
    }

    @OnClose
    public void onClose(Session session) {
        // WebSocket connection closes. AKA when a user logs out
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable throwable) {
        // Do error handling here
        throwable.printStackTrace();
    }
}